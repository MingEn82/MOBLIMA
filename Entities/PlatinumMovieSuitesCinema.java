package Entities;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class extends the Cinema Class to overwites existing methods and define new methods.
 */
public class PlatinumMovieSuitesCinema extends Cinema{
    private String screenLayout = """
             |       Screen       |
             |____________________|
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

    /**
     * This is the getter function for retrieving Screen Layout.
     * @return
     */
    public String getScreenLayout() {
        return this.screenLayout;
    }

    /**
     * This is the setter function for setting Screen Layout.
     * @param screenLayout
     */
    public void setScreenLayout(String screenLayout) {
        this.screenLayout = screenLayout;
    }

    /**
     * This is function is used to print the screen layout.
     */
    public void printScreenLayout() {
        System.out.println(screenLayout);
    }

    /**
     * This is the getter function for getting seat arrangement.
     */
    public String getSeatArrangement() {
        return this.seatArrangement;
    }

    /**
     * This is the setter function for retrieving seat arrangement.
     * @param seatArrangement
     */
    public void setSeatArrangement(String seatArrangement) {
        this.seatArrangement = seatArrangement;
    }

    /**
     * This is the getter function for getting entrance layout.
     */
    public String getEntranceLayout() {
        return this.entranceLayout;
    }

    /**
     * This is the setter function for setting entrance layout.
     * @param entranceLayout
     */
    public void setEntranceLayout(String entranceLayout) {
        this.entranceLayout = entranceLayout;
    }

    /**
     * This is the function to print the entrance layout.
     */
    public void printEntranceLayout() {
        System.out.println(entranceLayout);
    }

    /**
     * This is the function to retrieve Aisles.
     */
    public ArrayList<Integer> getAisles() {
        return this.aisles;
    }

    /**
     * This is the setter function for setting aisles.
     * @param aisles
     */
    public void setAisles(ArrayList<Integer> aisles) {
        this.aisles = aisles;
    }

    /**
     * This is the print function for printing the cinema details and showings.
     */
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
