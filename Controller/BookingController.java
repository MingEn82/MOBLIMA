package Controller;

import java.util.ArrayList; // import the ArrayList class
import Entities.Booking;
import java.util.Scanner;

import Boundary.MainMenuUI;


public class BookingController {
    BookingsDatabaseController bookingsDatabaseController = new BookingsDatabaseController();

    ArrayList<Booking> bookings = new ArrayList<Booking>(); // Create an ArrayList object


    BookingController(){
        //reads all the booking data from databasecontroller.
        bookings = bookingsDatabaseController.fetchBookings();
        //System.out.print(bookings.get(0)); 
    }

    public void viewBookingHistory(){
        Scanner scanner = MainMenuUI.scanner;
        System.out.println("Please enter your email address: ");

        String emailAddress = scanner.nextLine();
        scanner.close();
        for (Booking b : bookings) {
            if (b.getEmailAddressOfMovieGoer() == emailAddress)
            {
                b.printBooking();
                System.out.println("");
            }
            
        }

        
    }


    
}
