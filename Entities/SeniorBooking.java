package Entities;

import java.util.Date;

public class SeniorBooking extends Booking{

    public SeniorBooking(String tID, int phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer,
            String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration,
            String movieType, String cinemaType, Date startDate, float price) {

            super(tID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price);
    }

    //override super class method
    public void calBookingPrice(){


        //need to first check system settings for variables such as price for cinematype etc etc.
        //afterwards, use TID(for date), cinemaType, movieType + the discount for a child price.
        //then set the price as accordingly.
        this.setPrice(6);
    }
    
}
