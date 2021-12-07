package bookingSystem;

import bookingSystem.models.*;
import userSystem.models.User;

import java.sql.*;
import java.util.ArrayList;

public class BookingController {

    private ArrayList<Ticket> tickets;
    private ArrayList<Movie> movies;

    public BookingController() {
        tickets = new ArrayList<Ticket>();
        movies = new ArrayList<Movie>();
        loadMovieAndTheater();
        loadShowtimes();
        loadSeats();
        createTickets();

    }

    private void loadSeats() {
        String query;

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        for (Movie m : movies) {
            ArrayList<Theater> theaters = m.getTheaters();
            for (Theater t : theaters) {
                ArrayList<Showtime> showtimes = t.getShowtimes();
                for (Showtime s : showtimes){
                    query = "SELECT DISTINCT seat FROM ticket WHERE movieName = '" + m.getName() + "'";
                    query += "AND theater = '" + t.getLocation() + "'";
                    query += "AND showtime = '" + s.getStartTime() + "'";

                    try{
                        Connection conn = c;
                        Statement stmt = null;
                        stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        while (rs.next()){
                            int seatNumber = rs.getInt("seat");
                            System.out.println(seatNumber);
                            Seat seat = new Seat(seatNumber);
                            s.addSeat(seat);
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void loadShowtimes(){
        String query;

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        for (Movie m : movies){
            ArrayList<Theater> theaters = m.getTheaters();
            for (Theater t : theaters){
                query = "SELECT DISTINCT showtime FROM ticket WHERE movieName = '" + m.getName() + "'";
                query += "AND theater = '" + t.getLocation() + "'";
                try{
                    Connection conn = c;
                    Statement stmt = null;
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()){
                        String theShowtime = rs.getString("showtime");
                        Showtime showtime = new Showtime(theShowtime, 36);
                        t.addShowtime(showtime);
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadMovieAndTheater(){

        String query;

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        query = "SELECT DISTINCT movieName FROM ticket";
        try{
            Connection conn = c;
            Statement stmt = null;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                String movieName = rs.getString("movieName");
                Movie movie = new Movie(movieName);
                movies.add(movie);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        for (Movie m : movies){
            query = "SELECT DISTINCT theater FROM ticket WHERE movieName = '" + m.getName() + "'";
            try{
                Connection conn = c;
                Statement stmt = null;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()){
                    String theTheater = rs.getString("theater");
                    System.out.println(theTheater);
                    Theater theater = new Theater(theTheater);
                    m.addTheater(theater);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void createTickets(){
        String query;

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        for (Movie m : movies) {
            ArrayList<Theater> theaters = m.getTheaters();
            for (Theater t : theaters) {
                ArrayList<Showtime> showtimes = t.getShowtimes();
                for (Showtime s : showtimes){
                    ArrayList<Seat> seats = s.getSeats();
                    for (Seat st : seats){
                        query = "SELECT DISTINCT TicketID FROM ticket WHERE movieName = '" + m.getName() + "'";
                        query += "AND theater = '" + t.getLocation() + "'";
                        query += "AND showtime = '" + s.getStartTime() + "'";
                        query += "AND seat = '" + st.getSeatNumber() + "'";

                        try{
                            Connection conn = c;
                            Statement stmt = null;
                            stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(query);

                            while (rs.next()){
                                int ticketID = rs.getInt("TicketID");
                                Ticket ticket = new Ticket(ticketID, st, m, t, s);
                                tickets.add(ticket);
                            }
                        } catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public Ticket findTicket(int ticketNumber){
         for(Ticket t: tickets){
             if (t.getTicketNumber() == ticketNumber){
                 return t;
             }
         }
         return null;
    }

    public void bookTicket(Ticket t, User u){
        t.getSeat().setAvailable(false);
        t.setUserId(u.getUserId());
    }

    public Ticket findTicket(Movie m, Theater t, Showtime s, Seat st){
        for(Ticket ticket: tickets){
            Movie ticketMovie = ticket.getMovie();
            Showtime ticketShowtime = ticket.getShowtime();
            Seat ticketSeat = ticket.getSeat();

            if (ticketMovie.getName() == m.getName() &&
                    ticketShowtime.getStartTime() == s.getStartTime() &&
                    ticketSeat.getSeatNumber() == st.getSeatNumber()){
                return ticket;
            }
        }
        return null;
    }

    public ArrayList<Movie> getAllMovies(){
        return movies;
    }

    public ArrayList<Theater> getAllTheaters(Movie m){
        return m.getTheaters();
    }

    public ArrayList<Showtime> getAllShowtimes(Movie m, Theater t){
        ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
        for (Movie mv : movies){
            if (mv.getName().equals(m.getName())){
                ArrayList<Theater> theaters = new ArrayList<Theater>();
                theaters = mv.getTheaters();
                for (Theater th: theaters){
                    if (th.getLocation().equals(t.getLocation())){
                        showtimes = th.getShowtimes();
                    }
                }
            }
        }
        return showtimes;
    }

    public ArrayList<Seat> getAllSeats(Movie m, Theater t, Showtime s){
        ArrayList<Seat> seats = new ArrayList<Seat>();
        ArrayList<Showtime> showtimes = getAllShowtimes(m, t);
        for (Showtime showtime : showtimes){
            if (showtime.getStartTime().equals(s.getStartTime())){
                seats = showtime.getSeats();
            }
        }
        return seats;
    }

    @Override
    public String toString() {
        return "BookingController{" +
                "tickets=" + tickets +
                ", movies=" + movies +
                '}';
    }
}
