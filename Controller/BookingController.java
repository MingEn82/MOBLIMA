package Controller;

import java.util.ArrayList; // import the ArrayList class
import Entities.Booking;
import java.util.Scanner;



public class BookingController {
    BookingsDatabaseController bookingsDatabaseController = new BookingsDatabaseController();

    ArrayList<Booking> bookings = new ArrayList<Booking>(); // Create an ArrayList object


    BookingController(){
        //reads all the booking data from databasecontroller.
        bookings = bookingsDatabaseController.fetchBookings();
        //System.out.print(bookings.get(0)); 
    }

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


    
}
