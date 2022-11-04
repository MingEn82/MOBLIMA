package Entities;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is a concrete class that extends the abstract Cinema class to implement it's own methods.
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-02
 */
public class IMAXCinema extends Cinema {

    /**
     * Screen layout
     */
    private String screenLayout = """
              |               IMAX Screen               |
              |_________________________________________|
            """;

    /**
     * Seat Arrangement
     */
    private String seatArrangement = 
        "A, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, " + 
        "B, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, " + 
        "C, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, " + 
        "D, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, " + 
        "E, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, " + 
        "F, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, " + 
        "G, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, " + 
        "H, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, " +
        "I, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14";

    /**
     * Seat Numbers
     */
    private String seatNumbers = "\n   1  2  3  4   5  6  7  8   9  10 11 12 13 14";

    /**
     * String for rows with wide seats
     */
    private String[] wideSeatRows = { "H", "I" };

    /**
     * Entrance Layout
     */
    private String entranceLayout = """
                          _________________
                          |   Entrance    |
            """;

    /**
     * Aisles 
     */
    private ArrayList<Integer> aisles = new ArrayList<Integer>(Arrays.asList(4, 8));

    /**
     * Constructor For Cinema
     * @param showings
     * @param cinemaName
     * @param cinemaNumber
     */
    public IMAXCinema(ArrayList<Showing> showings, String cinemaName, String cinemaNumber) {
        super(CinemaType.IMAX, showings, cinemaName, cinemaNumber);
    }

    /**
     * This is a getter function for retrieving Screen Layout.
     */
    public String getScreenLayout() {
        return this.screenLayout;
    }

    /**
     * This is a setter function for setting a new Screen Layout.
     * @param screenLayout
     */
    public void setScreenLayout(String screenLayout) {
        this.screenLayout = screenLayout;
    }

    /**
     * This is a function for printing the Screen Layout.
     */
    public void printScreenLayout() {
        System.out.println(screenLayout);
    }

    /**
     * This is a getter function for retrieving the Seat Arrangement.
     * @return Seat Arrangement
     */
    public String getSeatArrangement() {
        return this.seatArrangement;
    }

    /**
     * This is a setter function for setting the Seat Arrangement.
     * @param seatArrangement
     */
    public void setSeatArrangement(String seatArrangement) {
        this.seatArrangement = seatArrangement;
    }

    /**
     * Setter function for SeatArrangement
     * @param seatArrangement
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
     * @param seatNumbers
     */
    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    /**
     * Print function for seatNumbers
     * @return Seat Numbers
     */
    public void printSeatNumbers() {
        System.out.println(this.seatNumbers);
    }


    /**
     * This is a getter function for getting the Entrance Layout.
     * @return Entrance Layout
     */
    public String getEntranceLayout() {
        return this.entranceLayout;
    }

    /**
     * This is a setter function for setting the Entrance Layout.
     * @param entranceLayout
     */
    public void setEntranceLayout(String entranceLayout) {
        this.entranceLayout = entranceLayout;
    }

    /**
     * This is a function to print the Entrance Layout.
     */
    public void printEntranceLayout() {
        System.out.println("\n" + entranceLayout);
    }

    /**
     * This is the getter function for retrieving Aisles.
     * @return ArrayList of Aisles
     */
    public ArrayList<Integer> getAisles() {
        return this.aisles;
    }

    /**
     * This is a setter function for setting Aisles.
     * @param aisles
     */
    public void setAisles(ArrayList<Integer> aisles) {
        this.aisles = aisles;
    }

    /**
     * This function is used to print the cinema details and showing details.
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
