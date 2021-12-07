package bookingSystem.models;

/**
 * Class representing a Ticket object.
 * @author Tahsin Chowdhury
 */
public class Ticket {

    private int ticketNumber;
    private Seat seat;
    private int userId;
    private Movie movie;
    private Theater theater;
    private Showtime showtime;

    /**
     * Constructs a Ticket object using the specified values.
     * @param ticketNumber a value of type int representing the ticket number
     * @param seat a Seat object
     * @param movie a Movie object
     * @param theater a Theater object
     * @param showtime a Showtime object
     */
    public Ticket(int ticketNumber, Seat seat, Movie movie, Theater theater, Showtime showtime) {
        this.ticketNumber = ticketNumber;
        this.seat = seat;
        this.movie = movie;
        this.theater = theater;
        this.showtime = showtime;
    }

    @Override
    public String toString(){
        String dispString = "Ticket Number: " + ticketNumber + "\n";
        dispString += "User ID: " + userId + "\n";
        dispString += "Movie: " + movie + "\n";
        dispString += "Theater: " + theater + "\n";
        dispString += "Showtime: " + showtime + "\n";
        dispString += "Seat: " + seat + "\n";

        return dispString;
    }

    /**
     * Returns the ticket number
     * @return a value of type int representing the ticket number
     */
    public int getTicketNumber() {
        return ticketNumber;
    }

    /**
     * Sets the ticket number to the specified value
     * @param ticketNumber a value of type int representing the ticket number
     */
    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    /**
     * Returns the seat
     * @return a Seat object
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Sets the seat to the specified value
     * @param seat a Seat object
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * Returns the userId of the user who owns the ticket.
     * @return a value of type int representing userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the userId to the specified value
     * @param userId a value of type int representing userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the movie.
     * @return a Movie Object
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets the movie to the specified value.
     * @param movie a Movie Object
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Returns the theater.
     * @return a Theater Object
     */
    public Theater getTheater() {
        return theater;
    }

    /**
     * Sets the theater to the specified value.
     * @param theater a Theater Object
     */
    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    /**
     * Returns the showtime.
     * @return a Showtime Object
     */
    public Showtime getShowtime() {
        return showtime;
    }

    /**
     * Sets the showtime to the specified value.
     * @param showtime a Showtime Object
     */
    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

}
