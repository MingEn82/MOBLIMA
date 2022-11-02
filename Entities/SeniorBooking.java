package Entities;

import java.util.ArrayList;
import java.util.Date;

import Controller.SystemSettingController;

/**
 * This class extends the existing booking class and implement its own calculate price method.
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public class SeniorBooking extends Booking {

    /**
     * Constructor for the Senior Booking Object
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
    public SeniorBooking(String tID, int phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer,
            String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration,
            String movieType, String cinemaType, Date startDate, float price) {

        super(tID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID,
                movieTitle, movieDuration, movieType, cinemaType, startDate, price);
    }

    /**
     * Overwrites the existing Booking class calBookingPrice method to calculate its own price for an senior booking.
     */
    public void calBookingPrice() {
        SystemSettings currentSettings = new SystemSettingController().getSystemSetting();
        // System.out.println("currentSettings = " +
        // currentSettings.getSeniorRegularTicketPrices());
        float priceOfTicket;

        ArrayList<Date> publicHolidays = currentSettings.getPublicHolidays();
        if (publicHolidays.contains(this.getStartDate())) {
            switch (this.getDayOfWeek(getStartDate())) {
                case "Mon":
                case "Tue":
                case "Wed":
                case "Thu":
                    priceOfTicket = currentSettings.getweekdayPrices();
                    break;

                case "Fri":
                case "Sat":
                case "Sun":
                    priceOfTicket = currentSettings.getweekendPrices();
                    break;

                default:
                    // this case should not happen since there are only 7 days in a week.
                    priceOfTicket = 0;
                    break;
            }
        } else {
            priceOfTicket = currentSettings.getpHPrices();
        }

        switch (this.getCinemaType()) {
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

        switch (this.getMovieType()) {
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

        // since im an senior class booking class.
        priceOfTicket -= currentSettings.getseniorDiscount();
        this.setPrice(priceOfTicket);
    }

}
