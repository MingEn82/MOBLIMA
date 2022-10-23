package Entities;

import java.util.ArrayList;
import java.util.Date;

import Controller.SystemSettingController;

public class StudentBooking extends Booking {

    public StudentBooking(String tID, int phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer,
            String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration,
            String movieType, String cinemaType, Date startDate, float price) {

        super(tID, phoneNumberOfMovieGoer, nameOfMovieGoer, emailOfMovieGoer, cineplexName, cinemaName, seatID,
                movieTitle, movieDuration, movieType, cinemaType, startDate, price);
    }

    // override super class method
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
                priceOfTicket += currentSettings.getthreeeDAddOn();
                break;

            case "Blockbuster":
            case "BlockBuster":
                priceOfTicket += currentSettings.getblockbusterAddOn();
                break;

            case "Standard":
            default:
                priceOfTicket += 0;
                break;
        }

        // since im an student class booking class.
        priceOfTicket -= currentSettings.getstudentDiscount();
        this.setPrice(priceOfTicket);
    }
}
