package Entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    private String TID;
    private String phoneNumberOfMovieGoer;
    private String nameOfMovieGoer;
    private String emailOfMovieGoer;
    private String cineplexName;
    private String cinemaName;
    private String seatID;
    private String movieTitle;
    private int movieDuration;
    private Date startDate;
    private double price;

    public Booking(String TID, String phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer, String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration, Date startDate, double price) {
        this.TID = TID;
        this.phoneNumberOfMovieGoer = phoneNumberOfMovieGoer;
        this.nameOfMovieGoer = nameOfMovieGoer;
        this.emailOfMovieGoer = emailOfMovieGoer;
        this.cineplexName = cineplexName;
        this.cinemaName = cinemaName;
        this.seatID = seatID;
        this.movieTitle = movieTitle;
        this.movieDuration = movieDuration;
        this.startDate = startDate;
        this.price = price;
    }


    public void printBooking() {
        System.out.println("TID: " + TID);
        System.out.println("Movie: " + movieTitle);
        System.out.println("Date: " + startDate);
        System.out.println("Movie Duration: " + movieDuration + " mins");
        System.out.println("Location: " + cineplexName + ", " + cinemaName);
        System.out.println("Seat: " + seatID);
        System.out.println("Name: " + nameOfMovieGoer);
        System.out.println("Phone Number: " + phoneNumberOfMovieGoer);
        System.out.println("Email Address: " + emailOfMovieGoer);
        System.out.println("Cost: " + price);
    }

    public String toString() {
        return TID + ", " + phoneNumberOfMovieGoer + ", " + nameOfMovieGoer + ", " + emailOfMovieGoer + ", " + cineplexName + ", " + cinemaName + ", " + seatID + ", " + movieTitle + ", " + movieDuration + ", " + dateToString(startDate) + ", " + price;
    }

    public void calculatePrice() {
        this.price = 0;
    }

    public static String dateToString(Date d) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        return dateFormat.format(d);
    }

}