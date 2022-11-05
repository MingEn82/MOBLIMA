package Entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


import Controller.SystemSettingController;

/**
 * This class extends the existing booking class and implement its own calculate price method.
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public class AdultBooking extends Booking{

    /**
     * This is a constructor for the Adult Booking class.
     * @param tID
     * @param phoneNumberOfMovieGoer
     * @param nameOfMovieGoer
     * @param emailOfMovieGoer
     * @param cineplexName
     * @param cinemaName
     * @param seatID
     * @param movieTitle
     * @param movieDuration
     * @param movieType
     * @param cinemaType
     * @param startDate
     * @param price
     */
    public AdultBooking(String tID, int phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer,
            String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration,
            String movieType, String cinemaType, Date startDate, float price, boolean isWideSeat) {

            super(tID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price, isWideSeat);
    }

    /**
     * Overwrites the existing Booking class calBookingPrice method to calculate its own price for an adult booking.
     */
    public void calBookingPrice(){
        SystemSettings currentSettings = new SystemSettingController().getSystemSetting();
        //System.out.println("currentSettings = " + currentSettings.getSeniorRegularTicketPrices());
        float priceOfTicket;
        int loyaltyDiscount = 0;
        Scanner scanner = new Scanner(System.in);
        boolean tries = true;
        while (tries) {
            System.out.println("Do you have preferred credit or loyalty card?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Enter your option:");
            loyaltyDiscount = scanner.nextInt();
            if (loyaltyDiscount == 1) {
                tries = false;
                break;
            } else if (loyaltyDiscount == 2){
                tries = false;
                break;
            }
            else{
                System.out.println("");
                System.out.println("Please enter the correct option.");
                System.out.println("");
            }
        }


        
        ArrayList<Date> publicHolidays = currentSettings.getPublicHolidays();
        DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        boolean isPh = false;

        for(int i = 0; i <publicHolidays.size(); i++)
        {
            if (DATE_FORMAT.format(publicHolidays.get(i)).equals(DATE_FORMAT.format(this.getStartDate())))
            {
                isPh = true;
                break;
            }
        }
        int hourOfDay = this.getStartDate().getHours();

        if (isPh == false)
        {
            
            switch(this.getDayOfWeek(getStartDate())){
                case "Mon":
                case "Tue":
                case "Wed":
                case "Thu":
                priceOfTicket = currentSettings.getweekdayPrices();
                //System.out.println("Is weekday and weekday price is "+currentSettings.getweekdayPrices());
                break;
    
                //if friday, after 6pm, price of ticket is weekend price.
                //before 6pm, is weekday price.
                case "Fri":
                    if (hourOfDay >= 18){
                        priceOfTicket = currentSettings.getweekendPrices();
                    }
                    else{
                        priceOfTicket = currentSettings.getweekdayPrices();
                    }
                    break;

                case "Sat":
                case "Sun":
                priceOfTicket = currentSettings.getweekendPrices();
                //System.out.println("Is weekend and weekend price is "+currentSettings.getweekendPrices());
                break;
    
                default:
                //this case should not happen since there are only 7 days in a week.
                priceOfTicket = 0;
                break;
            }
        }
        else
        {
            priceOfTicket = currentSettings.getpHPrices();
            //System.out.println("Is public holiday and ph price is: "+currentSettings.getweekendPrices());
        }
        

        
        


        if(loyaltyDiscount == 1)
        {
            //System.out.println("Testing, loyalty discount is true");
            priceOfTicket -= currentSettings.getprefCreditLoyaltyDiscount();
        }
        

        switch(this.getCinemaType())
        {
            case "IMAX":
            priceOfTicket += currentSettings.getIMAXAddOn();
            break;

            case "Platinum Movie Suite":
            priceOfTicket += currentSettings.getplatinumAddOn();
            break;
            
            case "Standard":
            default:
            priceOfTicket += 0;
            break;
        }

        switch(this.getMovieType())
        {
            case "3D":
            priceOfTicket += currentSettings.getthreeDAddOn();
            break;

            case "Blockbuster":
            case "BlockBuster":
            priceOfTicket += currentSettings.getblockbusterAddOn();
            break;
            
            case "Standard":
            case "2D":
            default:
            priceOfTicket += 0;
            break;
        }

        //since im an adult booking class.
        //there will be no discount
        if (getIsWideSeat()) { priceOfTicket += new SystemSettingController().getSystemSetting().getWideSeatAddOn(); }
        this.setPrice(priceOfTicket);
        System.out.printf("\nThe price of booking is: $%.2f\n", priceOfTicket);
    }
    
}
