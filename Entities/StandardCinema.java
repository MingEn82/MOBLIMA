package Entities;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * This is the class for StandardCinema it extends the Cinema class
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public class StandardCinema extends Cinema{

    /**
     * String for screenlayout
     */
    private String screenLayout = """
                    |          Screen          |
                    |__________________________|
            """;
    /**
     * String for Seat Arrangement
     */
    private String seatArrangement = 
        "A, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, "+
        "B, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, "+
        "C, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, "+
        "D, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, "+
        "E, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, "+
        "F, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12";

    /**
     * String for seat Numbers
     */
    private String seatNumbers = "\n   1  2  3  4   5  6  7  8   9  10 11 12";

    /**
     * String for Entrance Layout
     */
    private String entranceLayout = """
                        _________________
                        |   Entrance    |
            """;

    /**
     * Array List of Aisles
     */
    private ArrayList<Integer> aisles = new ArrayList<Integer>(Arrays.asList(4, 8));

    /**
     * Constructor for Standard Cinema
     * @param showings
     * @param cinemaName
     * @param cinemaNumber
     */
    public StandardCinema(ArrayList<Showing> showings, String cinemaName, String cinemaNumber) {
        super(CinemaType.STANDARD, showings, cinemaName, cinemaNumber);
    }

    /**
     * Getter function for ScreenLayout
     * @return Screen Layout
     */
    public String getScreenLayout() {
        return this.screenLayout;
    }

    /**
     * Setter function for ScreenLayout
     * @param screenLayout
     */
    public void setScreenLayout(String screenLayout) {
        this.screenLayout = screenLayout;
    }

    /**
     * Print function for ScreenLayout
     */
    public void printScreenLayout() {
        System.out.println(screenLayout);
    }

    /**
     * Getter function for SeatArrangement
     * @return Seat Arrangement
     */
    public String getSeatArrangement() {
        return this.seatArrangement;
    }

    /**
     * Setter function for SeatArrangement
     * @param seatArrangement
     */
    public void setSeatArrangement(String seatArrangement) {
        this.seatArrangement = seatArrangement;
    }

    /**
     * Getter function for seatNumbers
     * @return Seat Numbers
     */
    public String getSeatNumbers() {
        return this.seatNumbers;
    }

    /**
     * Setter function for seatNumbers
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
     * Getter function for EntranceLayout
     * @return Entrance Layout
     */
    public String getEntranceLayout() {
        return this.entranceLayout;
    }


    /**
     * Setter function for EntranceLayout
     * @param entranceLayout
     */
    public void setEntranceLayout(String entranceLayout) {
        this.entranceLayout = entranceLayout;
    }

    /**
     * Print function for EntranceLayout
     */
    public void printEntranceLayout() {
        System.out.println("\n" + entranceLayout);
    }

    /**
     * Getter function for Aisles
     * @return Aisles
     */
    public ArrayList<Integer> getAisles() {
        return this.aisles;
    }

    /**
     * Setter function for Aisles
     * @param aisles
     */
    public void setAisles(ArrayList<Integer> aisles) {
        this.aisles = aisles;
    }

}
