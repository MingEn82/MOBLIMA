package Entities;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * This is the class for StandardCinema it extends the Cinema class
 */
public class StandardCinema extends Cinema{
    private String screenLayout = """
                    |          Screen          |
                    |__________________________|
            """;
    private String seatArrangement = 
        "A, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, "+
        "B, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, "+
        "C, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, "+
        "D, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, "+
        "E, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, "+
        "F, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12";
    private String seatNumbers = "\n   1  2  3  4   5  6  7  8   9  10 11 12";
    private String entranceLayout = """
                        _________________
                        |   Entrance    |
            """;
    private ArrayList<Integer> aisles = new ArrayList<Integer>(Arrays.asList(4, 8));

    
    public StandardCinema(ArrayList<Showing> showings, String cinemaName, String cinemaNumber) {
        super(CinemaType.STANDARD, showings, cinemaName, cinemaNumber);
    }

    /**
     * Getter function for ScreenLayout
     * @return
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
     * @return
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
     * @return
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
     * @return
     */
    public void printSeatNumbers() {
        System.out.println(this.seatNumbers);
    }

    /**
     * Getter function for EntranceLayout
     * @return
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
     * @return
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
