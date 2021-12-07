package bookingSystem.models;

public class Seat {

    private int seatNumber;
    private boolean isAvailable;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        isAvailable = true;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
