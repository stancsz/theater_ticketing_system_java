package bookingSystem.models;

import java.util.ArrayList;

/**
 * Class representing a Movie object.
 * @author Tahsin Chowdhury
 */
public class Movie {

    private String name; // movie name
    private ArrayList<Theater> theaters; // list of theaters showing the movie

    /**
     * Constructs a Movie object using the specified movie name and
     * initializes the array of theaters.
     * @param name value of type String representing the name of the movie
     */
    public Movie(String name) {
        this.name = name;
        theaters = new ArrayList<Theater>();
    }

    /**
     * Returns the name of the movie.
     * @return value of type String representing the name of the movie
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name of the movie to the specified value.
     * @param name value of type String representing the name of the movie
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a list of theater showing this movie.
     * @return An ArrayList of Theater objects
     */
    public ArrayList getTheaters() {
        return theaters;
    }

    /**
     * Sets the ArrayList of theater to the specified ArrayList
     * @param theaters An ArrayList of Theater objects
     */
    public void setTheaters(ArrayList theaters) {
        this.theaters = theaters;
    }

    /**
     * Adds a theater to the ArrayList of Theaters
     * @param t An object of type Theater
     */
    public void addTheater(Theater t){
        theaters.add(t);
    }

    @Override
    public String toString(){
        return name;
    }
}
