package Entities;

import java.util.ArrayList;

public enum CinemaDetails {
    STANDARD( 
        "Standard", 
        "A, 3, 4, 5, 6, 7, 8, B, 3, 4, 5, 6, 7, 8, C, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, D, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, E, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, F, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, G, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, H, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, I, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10"
    ),
    IMAX( 
        "IMAX",
        "A, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, B, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, C, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, D, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, E, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, F, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10"
    ),
    PLATINUM_MOVIE_SUITE( 
        "Platinum Movie Suite",
        "A, 1, 2, 3, 4, 5, 6, B, 1, 2, 3, 4, 5, 6, C, 1, 2, 3, 4, 5, 6, D, 1, 2, 3, 4, 5, 6, E, 1, 2, 3, 4, 5, 6, F, 1, 2, 3, 4, 5, 6"
    );

    private String type;
    private String seatArrangement;
    private ArrayList<Integer> aisles;

    CinemaDetails( String type, String seatArrangement ) {
        this.type = type;
        this.seatArrangement = seatArrangement;
        aisles = new ArrayList<Integer>();
        switch (this.type) {
            case "Standard":
                aisles.add(Integer.valueOf(5));
                break;
            case "IMAX":
                aisles.add(Integer.valueOf(3));
                aisles.add(Integer.valueOf(6));
                break;
            case "Platinum Movie Suite":
                aisles.add(Integer.valueOf(2));
                aisles.add(Integer.valueOf(4));
                break;
            default:
                aisles.add(Integer.valueOf(5));
        }
    }

    public String getType() {
        return this.type;
    }

    public String[] getSeatArrangement() {
        return this.seatArrangement.split(", ");
    }

    public ArrayList<Integer> getAisles() {
        return this.aisles;
    }
}
