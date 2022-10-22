package Entities;

import java.util.Date;

public class ChildBooking extends Booking{

    

    public ChildBooking(String TID, String phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer,
            String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration, Date startDate,
            double price) {
        super(TID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle,
                movieDuration, movieTitle, movieTitle, startDate, price);
    }
    
    //override super class method
    public void calBookingPrice(){


        //need to first check system settings for variables such as price for cinematype etc etc.
        //afterwards, use TID(for date), cinemaType, movieType + the discount for a child price.
        //then set the price as accordingly.
        this.setPrice(5);
    }
}
