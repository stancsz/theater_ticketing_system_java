package bookingSystem.models;

import java.util.ArrayList;

/**
 * Class representing a Showtime object.
 * @author Tahsin Chowdhury
 */
public class Showtime {

    private String startTime; // start time
    private ArrayList<Seat> seats; // list of seats for this showtime
    private int roomNumber; // room number of the showtime

    /**
     * Constructs a Showtime object using the specified movie start time and
     * room number and initializes the array of theaters.
     * @param startTime
     * @param roomNumber
     */
    public Showtime(String startTime, int roomNumber) {
        this.startTime = startTime;
        this.roomNumber = roomNumber;
        seats = new ArrayList<Seat>();
    }

    /**
     * Returns the start time.
     * @return a value of type String representing the start time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time
     * @param startTime a value of type String representing the start time
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the list of seats.
     * @return an ArrayList of Seat objects
     */
    public ArrayList<Seat> getSeats() {
        return seats;
    }

    /**
     * Sets the ArrayList of showtime to the specified ArrayList
     * @param seats An ArrayList of Showtime objects
     */
    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    /**
     * Returns the room number
     * @return a value of type int representing the room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the room number
     * @param roomNumber a value of type int representing the room number
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Adds a Seat to the ArrayList of Seat objects
     * @param s a Seat object
     */
    public void addSeat(Seat s){
        seats.add(s);
    }

    @Override
    public String toString(){
        return startTime;
    }
}
