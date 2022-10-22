package Controller;

import java.util.ArrayList; // import the ArrayList class
import Entities.Booking;
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
    public void newBooking()
    {
        Booking newBooking = new Booking();
        //need to first choose a movie.
        //need to choose an available showing.

        //set the attributes of newBooking such as movieName, seat, cinema etc.
        //call BookingsDatabaseController.addNewBooking(newBooking);
    }


    
}
