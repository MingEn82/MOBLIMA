package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entities.Booking;
import Entities.StudentBooking;
import Utils.InputGetter;
import Entities.AdultBooking;
import Entities.SeniorBooking;

/**
 * BookingController is a controller class that holds the logic to view bookings and make new booking.
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public class BookingController {
    /**
     * Creates BookingsDatabaseController object
     */
    BookingsDatabaseController bookingsDatabaseController =  new BookingsDatabaseController();

    /**
     * Creates CineplexController object
     */
    CineplexController cineplexController = new CineplexController();

    /**
     * Creates bookings arraylist
     */
    ArrayList<Booking> bookings = new ArrayList<Booking>(); // Create an ArrayList object

    /**
     * Constructor for Booking Controller
     */
    BookingController() {
        //reads all the booking data from databasecontroller.
        bookings = bookingsDatabaseController.fetchBookings();
        //System.out.print(bookings.get(0)); 
    }

    /**
     * This function allows the user to view his/her booking history by typing in his email address.
     */
    public void viewBookingHistory() {
        // update bookings
        this.bookings = bookingsDatabaseController.fetchBookings();

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Please enter your email address: ");
        System.out.println();
        System.out.print("Email address: ");
        String emailAddress = scanner.nextLine();

        boolean hasBookings = false;
        for (Booking b : bookings) {
            if (b.getEmailOfMovieGoer().toLowerCase().trim().equals(emailAddress.toLowerCase().trim()))
            {
                hasBookings = true;
                break;
            }
        }
        if (!hasBookings) {
            System.out.println("No bookings found!\n");
            return;
        }

        System.out.println();
        System.out.println("---------------------------------------------------------");
        System.out.println("Your bookings are as follows: ");
        System.out.println("---------------------------------------------------------");
        for (Booking b : bookings) {
            if (b.getEmailOfMovieGoer().toLowerCase().trim().equals(emailAddress.toLowerCase().trim()))
            {
                b.printBooking();
                System.out.println("---------------------------------------------------------");
                System.out.println("");
            }
            
        }

    }


    /**
     * This function checks whether if a booking already exist for a particular showing.
     * Staff is not supposed to update an existing showing details such as showtime once booking has happened.
     * Returns false if no booking is found. Else, returns true.
     * @param cineplexName  name of cineplex
     * @param cinemaName    name of cinema
     * @param movieTitle    name of movie
     * @param startDate     start date of showing
     * @return              true if booking exists, else false
     */
    public boolean bookingExistForShowing(String cineplexName, String cinemaName, String movieTitle, Date startDate)
    {
        for (Booking b : bookings) {
            if (b.getCineplexName().toLowerCase().trim().equals(cineplexName.toLowerCase().trim()) && b.getCinemaName().toLowerCase().trim().equals(cinemaName.toLowerCase().trim()) && b.getMovieTitle().toLowerCase().trim().equals(movieTitle.toLowerCase().trim()) && b.getStartDate().compareTo(startDate) == 0)
            {
                return true;
            }
            
        }
        return false;
    }

    /**
     * This function prompts the user to select a movie, a cinema and choose one of the available showing.
     * He will also need to key in his/her name, email address and phone number.
     * Price will be calculated depending on the movie type, time/date of movie, cinema type and whether the type of ticket.
     * @param TID               booking id
     * @param cineplexName      name of cineplex
     * @param cinemaName        name of cinema
     * @param seatID            seat ID
     * @param movieTitle        name of movie
     * @param movieDuration     duration of movie in mins
     * @param movieType         type of movie (2D, 3D, Blockbuster)
     * @param cinemaType        type of cinema (Standard, IMAX, Platinum Movie Suite)
     * @param startDate         start date and time of showing
     * @param price             ticket price
     * @param isWideSeat        whether movie goer booked a wide seat
     * @return                  true if successful, false if unsuccessful
     */
    public boolean newBooking(String TID, String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration, String movieType, String cinemaType, Date startDate, float price, boolean isWideSeat)
    {
        int choice, tries;
        int phoneNumberOfMovieGoer = -1;
        String nameOfMovieGoer = "", emailOfMovieGoer = "";
        //System.out.println("Date now is" + startDate);
        Scanner scanner = new Scanner(System.in);

        // Get phone number of user
        tries = 3;
        while (tries > 0) {
            System.out.print("Enter your phone number: ");
            phoneNumberOfMovieGoer = scanner.nextInt();
            if (phoneNumberOfMovieGoer > 9999999 && phoneNumberOfMovieGoer < 100000000) {
                break;
            } else {
                tries--;
                System.out.println("Invalid phone number! Tries remaining: " + tries);
            }
        }
        if (tries == 0) {
            System.out.println("Maximum tries reached! Cancelling booking..");
            return false;
        }

        // Get email address of user
        scanner.nextLine();
        tries = 3;
        while (tries > 0) {
            System.out.print("Enter your email address: ");
            emailOfMovieGoer = scanner.nextLine();
            if (emailOfMovieGoer.contains("@")) {
                break;
            } else {
                System.out.println("Invalid email address! Tries remaining: " + tries);
                tries--;
            }
        }
        if (tries == 0) {
            System.out.println("Maximum tries reached! Cancelling booking..");
            return false;
        }

        // Get name of user
        System.out.print("Enter your name: ");
        nameOfMovieGoer = scanner.nextLine();

        do
        {
            System.out.println(""); // print empty line
            System.out.println("---------------------------------------------------------");
            System.out.println("Are you a student/adult/senior?");
            System.out.println("---------------------------------------------------------");
            System.out.println("1. Student");
            System.out.println("2. Adult");
            System.out.println("3. Senior Citizen");
            System.out.println("4. Exit Booking");
            System.out.println("---------------------------------------------------------");
            System.out.println(""); // print empty line

            choice = new InputGetter().getInt();

            Booking newBooking;
            switch(choice)
            {
                case 1:
                newBooking = new StudentBooking(TID, phoneNumberOfMovieGoer, nameOfMovieGoer,emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price, isWideSeat);
                newBooking.calBookingPrice();
                
                bookingsDatabaseController.addNewBooking(newBooking);
                cineplexController.bookSeat(cineplexName, cinemaName, movieTitle, startDate, seatID);
                System.out.println("");
                System.out.println("Booking is successful!\n");
                return true;
                
                case 2:
                newBooking = new AdultBooking(TID, phoneNumberOfMovieGoer, nameOfMovieGoer,emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price, isWideSeat);
                newBooking.calBookingPrice();
                //System.out.println("The new booking object is " +newBooking);
                bookingsDatabaseController.addNewBooking(newBooking);
                cineplexController.bookSeat(cineplexName, cinemaName, movieTitle, startDate, seatID);
                System.out.println("");
                System.out.println("Booking is successful!\n");
                return true;
                

                case 3:
                newBooking = new SeniorBooking(TID, phoneNumberOfMovieGoer, nameOfMovieGoer,emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price, isWideSeat);
                newBooking.calBookingPrice();
                bookingsDatabaseController.addNewBooking(newBooking);
                cineplexController.bookSeat(cineplexName, cinemaName, movieTitle, startDate, seatID);
                System.out.println("");
                System.out.println("Booking is successful!\n");
                return true;
                

                case 4:
                System.out.println("");
                System.out.println("Exiting booking....");
                System.out.println(""); // print empty line
                return false;
                
                default:
                System.out.println("");
                System.out.println("You've entered an invalid choice. Please enter a choice again.");
                System.out.println(""); // print empty line
                break;
            }
        }
        while (choice != 4);

        
        return false;
    }

    /**
     * This function will book multiple tickets based on inputted name, email address and phone number
     * Price will be calculated depending on the movie type, time/date of movie, cinema type and whether the type of ticket.
     * @param TID               booking id
     * @param cineplexName      cineplex name
     * @param cinemaName        cinema name
     * @param seatID            seat ID
     * @param movieTitle        name of movie
     * @param movieDuration     duration of movie in mins
     * @param movieType         type of movie (2D, 3D, Blockbuster)
     * @param cinemaType        type of cinema (Standard, IMAX, Platinum Movie Suite)
     * @param startDate         start date and time of showing
     * @param price             ticket price
     * @param isWideSeat        whether user booked a wide seat
     * @param name              name of booker
     * @param emailAddress      email address of booker
     * @param phoneNumber       phone number of booker
     * @return                  true if successful, false if unsuccessful
     */
    public boolean newBooking(String TID, String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration, String movieType, String cinemaType, Date startDate, float price, boolean isWideSeat, String name, String emailAddress, int phoneNumber)
    {
        int choice;
        do
        {
            System.out.println(""); // print empty line
            System.out.println("---------------------------------------------------------");
            System.out.println("Are you a student/adult/senior?");
            System.out.println("---------------------------------------------------------");
            System.out.println("1. Student");
            System.out.println("2. Adult");
            System.out.println("3. Senior Citizen");
            System.out.println("4. Exit Booking");
            System.out.println("---------------------------------------------------------");
            System.out.println(""); // print empty line

            choice = new InputGetter().getInt();

            Booking newBooking;
            switch(choice)
            {
                case 1:
                newBooking = new StudentBooking(TID, phoneNumber, name, emailAddress, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price, isWideSeat);
                newBooking.calBookingPrice();
                
                bookingsDatabaseController.addNewBooking(newBooking);
                cineplexController.bookSeat(cineplexName, cinemaName, movieTitle, startDate, seatID);
                System.out.println("");
                System.out.println("Booking is successful!\n");
                return true;
                
                case 2:
                newBooking = new AdultBooking(TID, phoneNumber, name, emailAddress, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price, isWideSeat);
                newBooking.calBookingPrice();
                //System.out.println("The new booking object is " +newBooking);
                bookingsDatabaseController.addNewBooking(newBooking);
                cineplexController.bookSeat(cineplexName, cinemaName, movieTitle, startDate, seatID);
                System.out.println("");
                System.out.println("Booking is successful!\n");
                return true;
                

                case 3:
                newBooking = new SeniorBooking(TID, phoneNumber, name, emailAddress, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price, isWideSeat);
                newBooking.calBookingPrice();
                bookingsDatabaseController.addNewBooking(newBooking);
                cineplexController.bookSeat(cineplexName, cinemaName, movieTitle, startDate, seatID);
                System.out.println("");
                System.out.println("Booking is successful!\n");
                return true;
                

                case 4:
                System.out.println("");
                System.out.println("Exiting booking....");
                System.out.println(""); // print empty line
                return false;
                
                default:
                System.out.println("");
                System.out.println("You've entered an invalid choice. Please enter a choice again.");
                System.out.println(""); // print empty line
                break;
            }
        }
        while (choice != 4);

        
        return false;
    }

    /**
     * Deletes all bookings of movie
     * @param movieTitle    name of movie
     */
    public void deleteBookings(String movieTitle) {
        bookingsDatabaseController.deleteBookings(movieTitle);
    }
}
