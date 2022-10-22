package Controller;

import Controller.MovieGoerMovieController;
import Controller.BookingController;

public class MovieGoerController {
    MovieController movieGoerMovieController = new MovieGoerMovieController();


    public void processMovieGoerChoice(int choice)
    {
        System.out.println("OK movie goer choice works, choice is "+ choice);
        switch(choice){
            case 1:
            System.out.println("Executing movieGoerMovieController.findMovies()");
            //movieGoerMovieController.findMovies();
            break;

            case 2:
            System.out.println("Executing bookingController.bookMovie()");
            //bookingController.bookMovie();
            break;

            case 3:
            System.out.println("Executing bookingController.viewBookingHistory()");
            //bookingController.viewBookingHistory();
            break;

            case 4:
            System.out.println("Executing movieGoerMovieController.viewTopMovies()");
            //movieGoerMovieController.viewTopMovies();
            break;

            default:
            System.out.println("Please enter a valid choice");
            break;


        }
    }

    

    
}
