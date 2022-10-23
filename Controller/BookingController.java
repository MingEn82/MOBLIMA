package Controller;

import java.util.ArrayList; // import the ArrayList class
import java.util.Date;


import Entities.Booking;
import Entities.StudentBooking;
import Entities.AdultBooking;
import Entities.SeniorBooking;


import java.util.Scanner;




/**
 * BookingController is a controller class that holds the logic to view bookings and make new booking.
 */
public class BookingController {
    BookingsDatabaseController bookingsDatabaseController = new BookingsDatabaseController();
    ArrayList<Booking> bookings = new ArrayList<Booking>(); // Create an ArrayList object



    BookingController(){
        //reads all the booking data from databasecontroller.
        bookings = bookingsDatabaseController.fetchBookings();
        //System.out.print(bookings.get(0)); 
    }

    /**
     * This function allows the user to view his/her booking history by typing in his email address.
     */
    public void viewBookingHistory(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email address: ");
        System.out.println();
        System.out.print("Email address: ");

        String emailAddress = scanner.nextLine();
        System.out.println();
        System.out.println("---------------------------------------------------------");
        System.out.println("Your bookings are as follows: ");
        System.out.println("---------------------------------------------------------");
        for (Booking b : bookings) {
            if (b.getEmailOfMovieGoer().toLowerCase().trim().equals(emailAddress.toLowerCase().trim()))
            {
                b.printBooking();
                System.out.println("");
            }
            
        }
        System.out.println("---------------------------------------------------------");
        System.out.println();
    }


    /**
     * This function prompts the user to select a movie, a cinema and choose one of the available showing.
     * He will also need to key in his/her name, email address and phone number.
     * Price will be calculated depending on the movie type, time/date of movie, cinema type and whether the type of ticket.
     * @param TID
     * @param cineplexName
     * @param cinemaName
     * @param seatID
     * @param movieTitle
     * @param movieDuration
     * @param movieType
     * @param cinemaType
     * @param startDate
     * @param price
     * @return
     */
    public boolean newBooking(String TID, String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration, String movieType, String cinemaType, Date startDate, float price)
    {
        int choice, tries;
        int phoneNumberOfMovieGoer = -1;
        String nameOfMovieGoer = "", emailOfMovieGoer = "";
        //System.out.println("Date now is" + startDate);
        Scanner scanner = new Scanner(System.in);

        // Get phone number of user
        tries = 3;
        while (tries > 0) {
            System.out.println("Enter your phone number:");
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
        tries = 3;
        while (tries > 0) {
            System.out.println("Enter your email address:");
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
        System.out.println("Enter your name:");
        nameOfMovieGoer = scanner.nextLine();

        do
        {
            System.out.println(""); // print empty line
            System.out.println("---------------------------------------------------------");
            System.out.println("Are you a student/adult/senior?");
            System.out.println("1. Student");
            System.out.println("2. Adult");
            System.out.println("3. Senior Citizen");
            System.out.println("4. Exit Booking");
            System.out.println("---------------------------------------------------------");
            System.out.println(""); // print empty line

            choice = scanner.nextInt();

            Booking newBooking;
            switch(choice)
            {
                case 1:
                newBooking = new StudentBooking(TID, phoneNumberOfMovieGoer, nameOfMovieGoer,emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price);
                newBooking.calBookingPrice();
                
                bookingsDatabaseController.addNewBooking(newBooking);
                System.out.println("Booking is successful!");
                return true;
                
                
                case 2:
                newBooking = new AdultBooking(TID, phoneNumberOfMovieGoer, nameOfMovieGoer,emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price);
                newBooking.calBookingPrice();
                //System.out.println("The new booking object is " +newBooking);
                bookingsDatabaseController.addNewBooking(newBooking);
                System.out.println("Booking is successful!");
                return true;
                

                case 3:
                newBooking = new SeniorBooking(TID, phoneNumberOfMovieGoer, nameOfMovieGoer,emailOfMovieGoer, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, price);
                newBooking.calBookingPrice();
                bookingsDatabaseController.addNewBooking(newBooking);
                System.out.println("Booking is successful!");
                return true;
                

                case 4:
                System.out.println("Exiting booking....");
                System.out.println(""); // print empty line
                return false;
                
                default:
                System.out.println("You've entered an invalid choice. Please enter a choice again.");
                System.out.println(""); // print empty line
                break;
            }
        }
        while (choice != 4);

        
        return false;
    }




    
}
