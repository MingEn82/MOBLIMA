package Controller;

public class MovieGoerController {
    private MovieController movieGoerMovieController = new MovieGoerMovieController();
    private BookingController bookingController = new BookingController();


    public void processMovieGoerChoice(int choice)
    {
        //System.out.println("OK movie goer choice works, choice is "+ choice);
        switch(choice){
            case 1:
            movieGoerMovieController.findMovies();
            break;

            case 2:
            bookingController.viewBookingHistory();
            break;

            case 3:
            movieGoerMovieController.viewTopMovies();
            break;

            default:
            System.out.println("Please enter a valid choice");
            break;

        }
    }

    

    
}
