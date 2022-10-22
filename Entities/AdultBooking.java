package Entities;

import java.util.Date;

public class AdultBooking extends Booking{

    public AdultBooking(String TID, String phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer,
            String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration, Date startDate,
            double price) {
        super(TID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle,
                movieDuration, movieTitle, movieTitle, startDate, price);
    }
    
}
