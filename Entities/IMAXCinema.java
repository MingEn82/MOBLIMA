package Entities;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is a concrete class that extends the abstract Cinema class to implement it's own methods.
 */
public class IMAXCinema extends Cinema {
    private String screenLayout = """
            |               IMAX Screen               |
            |_________________________________________|
            """;
    private String seatArrangement = 
        "A, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, " + 
        "B, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, " + 
        "C, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, " + 
        "D, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, " + 
        "E, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, " + 
        "F, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, " + 
        "G, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, " + 
        "H, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, " +
        "I, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15";
    private String entranceLayout = """
                    _________________
                    |   Entrance    |
            """;
    private ArrayList<Integer> aisles = new ArrayList<Integer>(Arrays.asList(4, 8));

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
     * @return
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
     * This is a getter function for getting the Entrance Layout.
     * @return
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
        System.out.println(entranceLayout);
    }

    /**
     * This is the getter function for retrieving Aisles.
     * @return
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
