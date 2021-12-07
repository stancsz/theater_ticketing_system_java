package bookingSystem.models;

import java.util.ArrayList;

public class Theater {

    private String location; // theater location
    private ArrayList<Showtime> showtimes; // list of showtimes in this theater

    /**
     * Constructs a Theater object using the specified location and
     * initializes the array of showtimes.
     * @param location
     */
    public Theater(String location) {
        this.location = location;
        showtimes = new ArrayList<Showtime>();
    }

    /**
     * Returns the location of the theater.
     * @return value of type String representing the location of the theater
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the theater to the specified value.
     * @param location value of type String representing the location of the theater
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the list of showtimes in this theater.
     * @return an ArrayList of Showtime objects
     */
    public ArrayList<Showtime> getShowtimes() {
        return showtimes;
    }

    /**
     * Sets the ArrayList of Showtimes to the specified ArrayList
     * @param showtimes an ArrayList of Showtime objects
     */
    public void setShowtimes(ArrayList<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    /**
     * Adds a Showtime object to the ArrayList of Showtime Objects
     * @param s a Showtime Object
     */
    public void addShowtime(Showtime s){
        showtimes.add(s);
    }

    @Override
    public String toString(){
        return location;
    }
}
