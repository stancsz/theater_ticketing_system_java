package bookingSystem.models;

/**
 * Class representing a Movie object.
 * @author Tahsin Chowdhury
 */
public class Seat {

    private int seatNumber; // seat number
    private boolean isAvailable; // boolean value representing availability

    /**
     * Constructs a seat object using the specified seat number
     * and sets it's availability to true.
     * @param seatNumber a value of type int representing the seat number
     */
    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        isAvailable = true;
    }

    /**
     * Returns the seat number
     * @return a value of type int representing the seat number
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Sets the seat number to the specified value
     * @param seatNumber a value of type int representing the seat number
     */
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    /**
     * Returns the availability of the seat
     * @return a boolean value representing availability of the seat
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability of the seat to the specified value.
     * @param available a boolean value representing availability of the seat
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
