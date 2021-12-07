package BookingSystemModel;

public class Ticket {

    private int ticketNumber;
    private Seat seat;
    private int userId;
    private Movie movie;
    private Theater theater;
    private Showtime showtime;

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

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

}
