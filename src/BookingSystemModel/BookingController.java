package BookingSystemModel;

import java.util.ArrayList;

public class BookingController {

    private ArrayList<Ticket> tickets;
    private ArrayList<Movie> movies;

    public BookingController() {
        tickets = new ArrayList<Ticket>();
        movies = new ArrayList<Movie>();
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
}
