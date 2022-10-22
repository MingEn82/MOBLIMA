package Entities;

import java.util.ArrayList;

public class PlatinumMovieSuitesCinema extends Cinema{
    public PlatinumMovieSuitesCinema(ArrayList<Showing> showings, String cinemaName, String cinemaNumber) {
        super(CinemaDetails.PLATINUM_MOVIE_SUITE, showings, cinemaName, cinemaNumber);
    }
}
