package BookingSystemModel;

import java.util.ArrayList;

public class Movie {

    private String name;
    private ArrayList<Theater> theaters;

    public Movie(String name) {
        this.name = name;
        theaters = new ArrayList<Theater>();
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getTheaters() {
        return theaters;
    }

    public void setTheaters(ArrayList theaters) {
        this.theaters = theaters;
    }
}
