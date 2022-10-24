package Entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Utils.DateParser;

/**
 * This class is the Booking class entity for defining the booking object attributes and methods.
 */
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

    /**
     * Print all the attributes of a booking.
     */
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


    /**
     * Convert the attributes in the object to a string.
     */
    public String toString() {
        DateParser dp = new DateParser("yyyyMMddHHmm");
        return TID + ", " + phoneNumberOfMovieGoer + ", " + nameOfMovieGoer + ", " + emailOfMovieGoer + ", " + cineplexName + ", " + cinemaName + ", " + seatID + ", " + movieTitle + ", " + movieDuration + ", " + movieType + ", " + cinemaType + ", " + dp.formatDate(startDate) + ", " + price;
    }

    /**
     * Basic calculate booking price method to be override by child classes.
     */
    public void calBookingPrice() {
        this.price = 0;
    }

    /**
     * Convert date format to string.
     * @param d
     * @return
     */
    public static String dateToString(Date d) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        return dateFormat.format(d);
    }

    /**
     * Calculates the day of the week of a showing and return the day of the week.
     * @param d
     * @return
     */
    public String getDayOfWeek(Date d)
    {
        
        DateFormat df = new SimpleDateFormat("E");
        return df.format(d);
    }

    /**
     * Getter function for TID.
     * @return
     */
    public String getTID() {
        return TID;
    }


    /**
     * Setterfunction for TID.
     * @param tID
     */
    public void setTID(String tID) {
        TID = tID;
    }

    /**
     * Getter function for Phone Number.
     * @return
     */
    public int getPhoneNumberOfMovieGoer() {
        return phoneNumberOfMovieGoer;
    }

    /**
     * Setter function for Phone number.
     * @param phoneNumberOfMovieGoer
     */
    public void setPhoneNumberOfMovieGoer(int phoneNumberOfMovieGoer) {
        this.phoneNumberOfMovieGoer = phoneNumberOfMovieGoer;
    }

    /**
     * Getter function for Name.
     * @return
     */
    public String getNameOfMovieGoer() {
        return nameOfMovieGoer;
    }

    /**
     * Setter function for Movie Goer Name.
     * @param nameOfMovieGoer
     */
    public void setNameOfMovieGoer(String nameOfMovieGoer) {
        this.nameOfMovieGoer = nameOfMovieGoer;
    }

    /**
     * Getter function for Email Address.
     * @return
     */
    public String getEmailOfMovieGoer() {
        return emailOfMovieGoer;
    }

    /**
     * Setter function for Email Address.
     * @param emailOfMovieGoer
     */
    public void setEmailOfMovieGoer(String emailOfMovieGoer) {
        this.emailOfMovieGoer = emailOfMovieGoer;
    }

    /**
     * Getter function for Cineplex Name.
     * @return
     */
    public String getCineplexName() {
        return cineplexName;
    }

    /**
     * Setter function for Cineplex Name.
     * @param cineplexName
     */
    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    /**
     * Getter function for Cinema Name.
     * @return
     */
    public String getCinemaName() {
        return cinemaName;
    }

    /**
     * Setter function for Cinema Name.
     * @param cinemaName
     */
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    /**
     * Getter function for Seat ID.
     * @return
     */
    public String getSeatID() {
        return seatID;
    }

    /**
     * Setter function for Seat ID.
     * @param seatID
     */
    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }


    /**
     * Getter function for Movie Title
     * @return
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Setter function for Movie Title
     * @param movieTitle
     */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }


    /**
     * Getter function for Movie Duration.
     * @return
     */
    public int getMovieDuration() {
        return movieDuration;
    }

    /**
     * Setter function for Movie Duration.
     * @param movieDuration
     */
    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    /**
     * Getter function for Movie Type.
     * @return 
     */
    public String getMovieType() {
        return movieType;
    }

    /**
     * Setter function for Movie Type.
     * @param movieType
     */
    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }


    /**
     * Getter function for Cinema Type.
     * @return 
     */
    public String getCinemaType() {
        return cinemaType;
    }

    /**
     * Setter function for Cinema Type.
     * @param cinemaType
     */
    public void setCinemaType(String cinemaType) {
        this.cinemaType = cinemaType;
    }

    /**
     * Getter function for Start Date.
     * @return 
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Setter function for start date.
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter function for Price.
     * @return 
     */
    public float getPrice() {
        return price;
    }

    /**
     * Setter function for price.
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
    }
}