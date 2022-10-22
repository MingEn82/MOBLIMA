package Entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import Controller.SystemSettingController;

public class AdultBooking extends Booking{

    public AdultBooking(String tID, int phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer,
            String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration,
            String movieType, String cinemaType, Date startDate, float price) {

            super(tID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price);
    }

    //override super class method
    //need to first check system settings for variables such as price for cinematype etc etc.
    //afterwards, use TID(for date), cinemaType, movieType + the discount for a child price.
    //then set the price as accordingly.
    public void calBookingPrice(){
        SystemSettings currentSettings = new SystemSettingController().getSystemSetting();
        //System.out.println("currentSettings = " + currentSettings.getSeniorRegularTicketPrices());
        float price = 0;
        
        
        switch(this.getDayOfWeek(getStartDate())){
            case "Mon":
            
            break;

            case "Standard":
            break;

            case "Platinum Movie Suite":
            break;
            default:
            break;


        }




        
        this.setPrice(10);
    }
    
}
