package Entities;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class extends the Cinema Class to overwites existing methods and define new methods.
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-02
 */
public class PlatinumMovieSuitesCinema extends Cinema{

    /**
     * Screen Layout of Platinum Cinema
     */
    private String screenLayout = """
             |       Screen       |
             |____________________|
            """;

    /**
     * Seat Arrangement of Platinum Cinema
     */
    private String seatArrangement = 
        "A, 1, 2, 3, 4, 5, 6, "+
        "B, 1, 2, 3, 4, 5, 6, "+
        "C, 1, 2, 3, 4, 5, 6, "+
        "D, 1, 2, 3, 4, 5, 6, "+
        "E, 1, 2, 3, 4, 5, 6, "+
        "F, 1, 2, 3, 4, 5, 6";

    /**
     * Seat Numbers of Platinum Cinema
     */
    private String seatNumbers = "\n   1  2  3   4  5  6";

    /**
     * String for rows with wide seats
     */
    private String[] wideSeatRows = { "C", "D", "E", "F" };

    /**
     * Entrance Layout of Platinum Cinema
     */
    private String entranceLayout = """
            _________________
            |   Entrance    |
            """;

    /**
     * Array List of Aisles
     */
    private ArrayList<Integer> aisles = new ArrayList<Integer>(Arrays.asList(3));

    /**
     * Constructor for Platinum Cinema
     * @param showings      all showings for this cinema
     * @param cinemaName    name of cinema
     * @param cinemaNumber  number of cinema
     */
    public PlatinumMovieSuitesCinema(ArrayList<Showing> showings, String cinemaName, String cinemaNumber) {
        super(CinemaType.PLATINUM_MOVIE_SUITE, showings, cinemaName, cinemaNumber);
    }

    /**
     * This is the getter function for retrieving Screen Layout.
     * @return ScreenLayout
     */
    public String getScreenLayout() {
        return this.screenLayout;
    }

    /**
     * This is the setter function for setting Screen Layout.
     * @param screenLayout  new screen layout
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
     * @param seatArrangement   new seat arrangement
     */
    public void setSeatArrangement(String seatArrangement) {
        this.seatArrangement = seatArrangement;
    }

    /**
     * Setter function for wideSeatRows
     * @param wideSeatRows  String array of seat rows with wide seats
     */
    public void setWideSeatRows(String[] wideSeatRows) {
        this.wideSeatRows = wideSeatRows;
    }

    /**
     * Getter function for wide seats rows
     * @return Seat Arrangement
     */
    public String[] getWideSeatRows() {
        return this.wideSeatRows;
    }

    /**
     * This is a getter function for retrieving the Seat Arrangement.
     * @return Seat Numbers
     */
    public String getSeatNumbers() {
        return this.seatNumbers;
    }

    /**
     * This is a setter function for setting the Seat Arrangement.
     * @param seatNumbers   new seat numbers
     */
    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    /**
     * Print function for seatNumbers
     */
    public void printSeatNumbers() {
        System.out.println(this.seatNumbers);
    }


    /**
     * This is the getter function for getting entrance layout.
     */
    public String getEntranceLayout() {
        return this.entranceLayout;
    }

    /**
     * This is the setter function for setting entrance layout.
     * @param entranceLayout    new entrance layout
     */
    public void setEntranceLayout(String entranceLayout) {
        this.entranceLayout = entranceLayout;
    }

    /**
     * This is the function to print the entrance layout.
     */
    public void printEntranceLayout() {
        System.out.println("\n" + entranceLayout);
    }

    /**
     * This is the function to retrieve Aisles.
     * @return ArrayList of Aisles
     */
    public ArrayList<Integer> getAisles() {
        return this.aisles;
    }

    /**
     * This is the setter function for setting aisles.
     * @param aisles    new indexes for aisles
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
