package Entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Utils.DateParser;

public class Booking {
    private String TID;
    private int phoneNumberOfMovieGoer;
    private String nameOfMovieGoer;
    private String emailOfMovieGoer;
    private String cineplexName;
    private String cinemaName;
    private String seatID;
    private String movieTitle;
    private int movieDuration;
    private String movieType;
    private String cinemaType;
    private Date startDate;
    private float price;
    
    public Booking(String TID, int phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer, String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration, String movieType, String cinemaType, Date startDate, float price) {
        this.TID = TID;
        this.phoneNumberOfMovieGoer = phoneNumberOfMovieGoer;
        this.nameOfMovieGoer = nameOfMovieGoer;
        this.emailOfMovieGoer = emailOfMovieGoer;
        this.cineplexName = cineplexName;
        this.cinemaName = cinemaName;
        this.seatID = seatID;
        this.movieTitle = movieTitle;
        this.movieDuration = movieDuration;
        this.movieType = movieType;
        this.cinemaType = cinemaType;
        this.startDate = startDate;
        this.price = price;
    }

    public Booking() {}

    public void printBooking() {
        System.out.println("TID: " + TID);
        System.out.println("Movie: " + movieTitle);
        System.out.println("Date: " + startDate);
        System.out.println("Movie Duration: " + movieDuration + " mins");
        System.out.println("Location: " + cineplexName + ", " + cinemaName);
        System.out.println("Seat: " + seatID);
        System.out.println("CinemaType: " + cinemaType);
        System.out.println("MovieType: " + movieType);
        System.out.println("Name: " + nameOfMovieGoer);
        System.out.println("Phone Number: " + phoneNumberOfMovieGoer);
        System.out.println("Email Address: " + emailOfMovieGoer);
        System.out.println("Cost: " + price);
    }


    public String toString() {
        DateParser dp = new DateParser("yyyyMMddHHmm");
        return TID + ", " + phoneNumberOfMovieGoer + ", " + nameOfMovieGoer + ", " + emailOfMovieGoer + ", " + cineplexName + ", " + cinemaName + ", " + seatID + ", " + movieTitle + ", " + movieDuration + ", " + movieType + ", " + cinemaType + ", " + dp.formatDate(startDate) + ", " + price;
    }

    public void calBookingPrice() {
        this.price = 0;
    }

    public static String dateToString(Date d) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        return dateFormat.format(d);
    }

    public String getDayOfWeek(Date d)
    {
        
        DateFormat df = new SimpleDateFormat("E");
        return df.format(d);
    }

    //GETTER AND SETTERS FUNCTIONS
    public String getTID() {
        return TID;
    }


    public void setTID(String tID) {
        TID = tID;
    }


    public int getPhoneNumberOfMovieGoer() {
        return phoneNumberOfMovieGoer;
    }


    public void setPhoneNumberOfMovieGoer(int phoneNumberOfMovieGoer) {
        this.phoneNumberOfMovieGoer = phoneNumberOfMovieGoer;
    }


    public String getNameOfMovieGoer() {
        return nameOfMovieGoer;
    }


    public void setNameOfMovieGoer(String nameOfMovieGoer) {
        this.nameOfMovieGoer = nameOfMovieGoer;
    }


    public String getEmailOfMovieGoer() {
        return emailOfMovieGoer;
    }


    public void setEmailOfMovieGoer(String emailOfMovieGoer) {
        this.emailOfMovieGoer = emailOfMovieGoer;
    }


    public String getCineplexName() {
        return cineplexName;
    }


    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }


    public String getCinemaName() {
        return cinemaName;
    }


    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }


    public String getSeatID() {
        return seatID;
    }


    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }


    public String getMovieTitle() {
        return movieTitle;
    }


    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }


    public int getMovieDuration() {
        return movieDuration;
    }


    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getMovieType() {
        return movieType;
    }


    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }


    public String getCinemaType() {
        return cinemaType;
    }


    public void setCinemaType(String cinemaType) {
        this.cinemaType = cinemaType;
    }

    public Date getStartDate() {
        return startDate;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public float getPrice() {
        return price;
    }


    public void setPrice(float price) {
        this.price = price;
    }
}