package Entities;

import java.util.Date;

import Utils.DateParser;

public class Booking {
    private String TID;
    private String phoneNumberOfMovieGoer;
    private String nameOfMovieGoer;
    private String emailOfMovieGoer;
    private String cineplexName;
    private String cinemaName;
    private String seatID;
    private String cinemaType;
    private String movieTitle;
    private int movieDuration;
    private Date startDate;
    private float price;

    public Booking(String TID, String phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer, String cineplexName, String cinemaName, String seatID, String cinemaType, String movieTitle, int movieDuration, Date startDate, float price) {
        this.TID = TID;
        this.phoneNumberOfMovieGoer = phoneNumberOfMovieGoer;
        this.nameOfMovieGoer = nameOfMovieGoer;
        this.emailOfMovieGoer = emailOfMovieGoer;
        this.cineplexName = cineplexName;
        this.cinemaName = cinemaName;
        this.cinemaType = cinemaType;
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
        System.out.println("CinemaType: " + cinemaType);
        System.out.println("Name: " + nameOfMovieGoer);
        System.out.println("Phone Number: " + phoneNumberOfMovieGoer);
        System.out.println("Email Address: " + emailOfMovieGoer);
        System.out.println("Cost: " + price);
    }

    public String toString() {
        DateParser dp = new DateParser("yyyyMMddHHmm");
        return TID + ", " + phoneNumberOfMovieGoer + ", " + nameOfMovieGoer + ", " + emailOfMovieGoer + ", " + cineplexName + ", " + cinemaName + ", " + seatID + ", " + cinemaType + ", " + movieTitle + ", " + movieDuration + ", " + dp.formatDate(startDate) + ", " + price;
    }

    public void calculatePrice() {
        this.price = 0;
    }

}