package Entities;

import java.util.ArrayList;

public class Cineplex {
    private ArrayList<Cinema> cinemas;
    private String cinemaName;

    public Cineplex(ArrayList<Cinema> cinemas, String cinemaName) {
        this.cinemas = cinemas;
        this.cinemaName = cinemaName;
    }

    public ArrayList<Cinema> getCinemas() {
        return this.cinemas;
    }

    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public String getCinemaName() {
        return this.cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }
    
}
