package Entities;

import java.util.ArrayList;

public class Cinema {
    private CinemaType cinemaType;
    private ArrayList<Showing> showings;
    private int cinemaNumber;

    public Cinema(CinemaType cinemaType, ArrayList<Showing> showings, int cinemaNumber) {
        this.cinemaType = cinemaType;
        this.showings = showings;
        this.cinemaNumber = cinemaNumber;
    }

    public CinemaType getCinemaType() {
        return this.cinemaType;
    }

    public void setCinemaType(CinemaType cinemaType) {
        this.cinemaType = cinemaType;
    }

    public ArrayList<Showing> getShowings() {
        return this.showings;
    }

    public void setShowings(ArrayList<Showing> showings) {
        this.showings = showings;
    }

    public int getCinemaNumber() {
        return this.cinemaNumber;
    }

}
