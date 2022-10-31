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
        "A, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, "+
        "B, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, "+
        "C, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, "+
        "D, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, "+
        "E, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, "+
        "F, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10";
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
     */
    public String getScreenLayout() {
        return this.screenLayout;
    }

    /**
     * Setter function for ScreenLayout
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
     * Getter functino for SeatArrangement
     */
    public String getSeatArrangement() {
        return this.seatArrangement;
    }

    /**
     * Setter function for SeatArrangement
     */
    public void setSeatArrangement(String seatArrangement) {
        this.seatArrangement = seatArrangement;
    }

    /**
     * Getter function for EntranceLayout
     */
    public String getEntranceLayout() {
        return this.entranceLayout;
    }

    /**
     * Setter function for EntranceLayout
     */
    public void setEntranceLayout(String entranceLayout) {
        this.entranceLayout = entranceLayout;
    }

    /**
     * Print function for EntranceLayout
     */
    public void printEntranceLayout() {
        System.out.println(entranceLayout);
    }

    /**
     * Getter function for Aisles
     */
    public ArrayList<Integer> getAisles() {
        return this.aisles;
    }

    /**
     * Setter function for Aisles
     */
    public void setAisles(ArrayList<Integer> aisles) {
        this.aisles = aisles;
    }

}
