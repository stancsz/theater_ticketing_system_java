package BookingSystemModel;

import java.sql.*;
import java.util.ArrayList;

public class BookingController {

    private ArrayList<Ticket> tickets;
    private ArrayList<Movie> movies;

    public BookingController() {
        tickets = new ArrayList<Ticket>();
        movies = new ArrayList<Movie>();
        loadFromDatabase();
        for(Ticket t : tickets){
            if(!movies.contains(t.getMovie())){
                movies.add(t.getMovie());
            }
        }
    }

    private void loadFromDatabase(){

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        String query = "SELECT TicketID, movieName, showtime, theater, seat, availability FROM ticket";

        try{
            Connection conn = c;
            Statement stmt = null;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                int ticketID = rs.getInt("TicketID");
                String movieName = rs.getString("movieName");
                String theShowtime = rs.getString("showtime");
                String theTheater = rs.getString("theater");
                int seatNumber = rs.getInt("seat");


                Movie movie = new Movie(movieName);
                Theater theater = new Theater(theTheater);
                Showtime showtime = new Showtime(theShowtime, 36);
                Seat seat = new Seat(seatNumber);

                Ticket ticket = new Ticket(ticketID, seat, movie, theater, showtime);
                tickets.add(ticket);

            }
        } catch (SQLException e){
            e.printStackTrace();
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
        ArrayList<Theater> theaters = new ArrayList<Theater>();
        for (Ticket t : tickets){
            if (t.getMovie().equals(m)) {
                theaters.add(t.getTheater());
            }
        }

        return theaters;
    }

    public ArrayList<Showtime> getAllShowtimes(Movie m, Theater t){
        ArrayList<Showtime> showtimes = new ArrayList<Showtime>();
        for (Ticket ticket : tickets){
            if (ticket.getMovie().equals(m) && ticket.getTheater().equals(t)) {
                showtimes.add(ticket.getShowtime());
            }
        }
        return showtimes;
    }

    public ArrayList<Seat> getAllSeats(Movie m, Theater t, Showtime s){
        ArrayList<Seat> seats = new ArrayList<Seat>();

        for (Ticket ticket : tickets){
            if (ticket.getMovie().equals(m) && ticket.getTheater().equals(t) && ticket.getShowtime().equals(s)) {
                seats.add(ticket.getSeat());
            }
        }

        return seats;
    }
}
