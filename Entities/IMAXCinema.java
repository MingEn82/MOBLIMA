package Entities;

import java.util.ArrayList;

public class IMAXCinema extends Cinema {
    public IMAXCinema(ArrayList<Showing> showings, String cinemaName, String cinemaNumber) {
        super(CinemaDetails.IMAX, showings, cinemaName, cinemaNumber);
    }
}
