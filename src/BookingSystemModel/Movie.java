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

    public void addTheater(Theater t){
        theaters.add(t);
    }

    @Override
    public boolean equals(Object o){
        Movie m = (Movie) o;
        if (name.equals(m.getName())){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public String toString(){
        return name;
    }
}
