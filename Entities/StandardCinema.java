package Entities;

import java.util.ArrayList;
import java.util.Arrays;

public class StandardCinema extends Cinema{
    private String screenLayout = """
                |       Standard Screen       |
                |_____________________________|
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

}
