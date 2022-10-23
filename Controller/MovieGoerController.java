package Controller;

import java.util.Date;

public class MovieGoerController {
    private MovieController movieGoerMovieController = new MovieGoerMovieController();
    private BookingController bookingController = new BookingController();


    public void processMovieGoerChoice(int choice)
    {
        //System.out.println("OK movie goer choice works, choice is "+ choice);
        switch(choice){
            case 1:
            System.out.println("Executing movieGoerMovieController.findMovies()");
            movieGoerMovieController.findMovies();
            break;


            case 2:
            //System.out.println("Executing bookingController.viewBookingHistory()");
            bookingController.viewBookingHistory();
            break;

            case 3:
            System.out.println("Executing movieGoerMovieController.viewTopMovies()");
            movieGoerMovieController.viewTopMovies();
            break;

            default:
            System.out.println("Please enter a valid choice");
            break;

        }
    }

    

    
}
