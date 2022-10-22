package Controller;

import java.util.Date;

public class MovieGoerController {
    MovieController movieGoerMovieController = new MovieGoerMovieController();
    BookingController bookingController = new BookingController();


    public void processMovieGoerChoice(int choice)
    {
        //System.out.println("OK movie goer choice works, choice is "+ choice);
        switch(choice){
            case 1:
            System.out.println("Executing movieGoerMovieController.findMovies()");
            movieGoerMovieController.findMovies();
            break;

            case 2:
            System.out.println("Executing bookingController.newBooking");
            //I think this part needs to be removed, only allow the user to book from option 1.
            
            Date date1 = new Date(202210221419L);
            boolean success = bookingController.newBooking("401202210262250", 91234567, "James", "James@gmail.com", "Cineplex 4", "Cinema 1", "C03", "Minions", 145, "2D", "IMAX", date1, (float)8.5);
            System.out.println("Booking is successful? "+ success);
            break;

            case 3:
            //System.out.println("Executing bookingController.viewBookingHistory()");
            bookingController.viewBookingHistory();
            break;

            case 4:
            System.out.println("Executing movieGoerMovieController.viewTopMovies()");
            movieGoerMovieController.viewTopMovies();
            break;

            default:
            System.out.println("Please enter a valid choice");
            break;


        }
    }

    

    
}
