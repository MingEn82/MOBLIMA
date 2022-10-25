package Entities;

import java.util.ArrayList;
import java.util.Arrays;

public class PlatinumMovieSuitesCinema extends Cinema{
    private String screenLayout = """
            |         Screen         |
            |________________________|
            """;
    private String seatArrangement = 
        "A, 1, 2, 3, 4, 5, 6, "+
        "B, 1, 2, 3, 4, 5, 6, "+
        "C, 1, 2, 3, 4, 5, 6, "+
        "D, 1, 2, 3, 4, 5, 6, "+
        "E, 1, 2, 3, 4, 5, 6, "+
        "F, 1, 2, 3, 4, 5, 6";
    private String entranceLayout = """
                    _________________
                    |   Entrance    |
            """;
    private ArrayList<Integer> aisles = new ArrayList<Integer>(Arrays.asList(3));

    public PlatinumMovieSuitesCinema(ArrayList<Showing> showings, String cinemaName, String cinemaNumber) {
        super(CinemaType.PLATINUM_MOVIE_SUITE, showings, cinemaName, cinemaNumber);
    }

    public String getScreenLayout() {
        return this.screenLayout;
    }

    public void setScreenLayout(String screenLayout) {
        this.screenLayout = screenLayout;
    }

    public void printScreenLayout() {
        System.out.println(screenLayout);
    }

    public String getSeatArrangement() {
        return this.seatArrangement;
    }

    public void setSeatArrangement(String seatArrangement) {
        this.seatArrangement = seatArrangement;
    }

    public String getEntranceLayout() {
        return this.entranceLayout;
    }

    public void setEntranceLayout(String entranceLayout) {
        this.entranceLayout = entranceLayout;
    }

    public void printEntranceLayout() {
        System.out.println(entranceLayout);
    }

    public ArrayList<Integer> getAisles() {
        return this.aisles;
    }

    public void setAisles(ArrayList<Integer> aisles) {
        this.aisles = aisles;
    }

    public void print() {
        System.out.println("--------- Cinema Details ---------");
        System.out.println("Cinema Type: " + cinemaType.toString());
        System.out.println("Cinema Name: " + cinemaName);
        System.out.println("Cinema Number: " + cinemaNumber);
        System.err.println("");
        System.out.println("--------- Showings Details ---------");
        if (showings.size() > 0) {
            for (Showing showing : showings) {
                showing.printSeats(aisles);
            }
        } else {
            System.out.println("No showings");
            System.out.println("");
        }
    }
}
