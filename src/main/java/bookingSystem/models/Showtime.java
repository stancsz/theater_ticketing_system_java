package bookingSystem.models;

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

    public void addSeat(Seat s){
        seats.add(s);
    }

//    @Override
//    public boolean equals(Object o){
//        Showtime s = (Showtime) o;
//        if (startTime.equals(s.getStartTime())){
//            return true;
//        } else{
//            return false;
//        }
//    }

    @Override
    public String toString(){
        return startTime;
    }
}