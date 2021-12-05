package BookingSystemModel;

import java.util.ArrayList;

public class Theater {

    private String location;
    private ArrayList<Showtime> showtimes;

    public Theater(String location) {
        this.location = location;
        showtimes = new ArrayList<Showtime>();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(ArrayList<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    public void addShowtime(Showtime s){
        showtimes.add(s);
    }

    @Override
    public boolean equals(Object o){
        Theater t = (Theater) o;
        if (location.equals(t.getLocation())){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public String toString(){
        return location;
    }
}
