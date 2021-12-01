package BookingSystemModel;

import java.util.ArrayList;

public class Showtime {

    private String startTime;
    private ArrayList<Seat> seats;
    private int roomNumber;

    public Showtime(String startTime, int roomNumber) {
        this.startTime = startTime;
        this.roomNumber = roomNumber;
        seats = new ArrayList<Seat>();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
