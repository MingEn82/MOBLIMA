package Entities;

import java.util.ArrayList;

public class StandardCinema extends Cinema{

    public StandardCinema(CinemaDetails cinemaType, ArrayList<Showing> showings, String cinemaName, String cinemaNumber) {
        super(CinemaDetails.STANDARD, showings, cinemaName, cinemaNumber);
    }
    
}
