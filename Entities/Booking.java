package Entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Utils.DateParser;

/**
 * This class is the Booking class entity for defining the booking object attributes and methods.
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public class Booking {
    /**
     * TID of booking
     */
    private String TID;

    /**
     * Phone Number of Movie Goer
     */
    private int phoneNumberOfMovieGoer;

    /**
     * Name of Movie Goer
     */
    private String nameOfMovieGoer;

    /**
     * Email of Movie Goer
     */
    private String emailOfMovieGoer;

    /**
     * Cineplex Name
     */
    private String cineplexName;

    /**
     * Cinema Name
     */
    private String cinemaName;

    /**
     * Seat ID
     */
    private String seatID;

    /**
     * Title of Movie
     */
    private String movieTitle;

    /**
     * Movie Duration
     */
    private int movieDuration;

    /**
     * Type of Movie
     */
    private String movieType;

    /**
     * Type of Cinema
     */
    private String cinemaType;

    /**
     * Start Date of the Booking
     */
    private Date startDate;

    /**
     * Price of the booking
     */
    private float price;
    
    /**
     * Constructor for the booking class.
     * @param TID
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

    /**
     * Constructor
     */
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
     * @param d date
     * @return
     */
    public static String dateToString(Date d) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        return dateFormat.format(d);
    }

    /**
     * Calculates the day of the week of a showing and return the day of the week.
     * @param d date
     * @return
     */
    public String getDayOfWeek(Date d)
    {
        
        DateFormat df = new SimpleDateFormat("E");
        return df.format(d);
    }

    /**
     * Getter function for TID.
     * @return  TID
     */
    public String getTID() {
        return TID;
    }


    /**
     * Setterfunction for TID.
     * @param tID TID
     */
    public void setTID(String tID) {
        TID = tID;
    }

    /**
     * Getter function for Phone Number.
     * @return Phone Number
     */
    public int getPhoneNumberOfMovieGoer() {
        return phoneNumberOfMovieGoer;
    }

    /**
     * Setter function for Phone number.
     * @param phoneNumberOfMovieGoer phone Number
     */
    public void setPhoneNumberOfMovieGoer(int phoneNumberOfMovieGoer) {
        this.phoneNumberOfMovieGoer = phoneNumberOfMovieGoer;
    }

    /**
     * Getter function for Name.
     * @return Name
     */
    public String getNameOfMovieGoer() {
        return nameOfMovieGoer;
    }

    /**
     * Setter function for Movie Goer Name.
     * @param nameOfMovieGoer Name Of Movie Goer
     */
    public void setNameOfMovieGoer(String nameOfMovieGoer) {
        this.nameOfMovieGoer = nameOfMovieGoer;
    }

    /**
     * Getter function for Email Address.
     * @return EmailAddress Of Movie Goer
     */
    public String getEmailOfMovieGoer() {
        return emailOfMovieGoer;
    }

    /**
     * Setter function for Email Address.
     * @param emailOfMovieGoer Email of Movie Goer
     */
    public void setEmailOfMovieGoer(String emailOfMovieGoer) {
        this.emailOfMovieGoer = emailOfMovieGoer;
    }

    /**
     * Getter function for Cineplex Name.
     * @return Cineplex Name
     */
    public String getCineplexName() {
        return cineplexName;
    }

    /**
     * Setter function for Cineplex Name.
     * @param cineplexName Cineplex Name
     */
    public void setCineplexName(String cineplexName) {
        this.cineplexName = cineplexName;
    }

    /**
     * Getter function for Cinema Name.
     * @return Cinema Name
     */
    public String getCinemaName() {
        return cinemaName;
    }

    /**
     * Setter function for Cinema Name.
     * @param cinemaName Cinema Name
     */
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    /**
     * Getter function for Seat ID.
     * @return Seat ID
     */
    public String getSeatID() {
        return seatID;
    }

    /**
     * Setter function for Seat ID.
     * @param seatID Seat ID
     */
    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }


    /**
     * Getter function for Movie Title
     * @return Movie Title
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
     * @return Movie Duration
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
     * @return Movie Type
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
     * @return Cinema Type
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
     * @return Start Date
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
     * @return Price
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