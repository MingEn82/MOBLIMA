package Controller;

import java.util.ArrayList; // import the ArrayList class
import java.util.Date;


import Entities.Booking;
import Entities.StudentBooking;
import Entities.AdultBooking;
import Entities.SeniorBooking;


import java.util.Scanner;
import Entities.SystemSettings;



/**
 * BookingController is a controller class that holds the logic to view bookings and make new booking.
 */
public class BookingController {
    BookingsDatabaseController bookingsDatabaseController = new BookingsDatabaseController();
    SystemSettingController systemSettingController = new SystemSettingController();

    ArrayList<Booking> bookings = new ArrayList<Booking>(); // Create an ArrayList object
    //SystemSettings currentSettings = systemSettingController.getSystemSetting();


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
            if (b.getEmailAddressOfMovieGoer().toLowerCase().trim().equals(emailAddress.toLowerCase().trim()))
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
     */
    public boolean newBooking(String TID, int phoneNumberOfMovieGoer, String nameOfMovieGoer, String emailOfMovieGoer, String cineplexName, String cinemaName, String seatID, String movieTitle, int movieDuration, String movieType, String cinemaType, Date startDate, float price)
    {
        int choice;
        //System.out.println("Date now is" + startDate);
        Scanner scanner = new Scanner(System.in);
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
