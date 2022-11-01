package Controller;

/**
 * MovieGoerController class handles the choice of the movie goer
 */
public class MovieGoerController {
    private MovieController movieGoerMovieController = new MovieGoerMovieController();

    /**
     * Handles the choice of movie goer
     * @param choice
     */
    public void processMovieGoerChoice(int choice)
    {
        switch(choice){
            case 1:
                movieGoerMovieController.displayMenu();
                break;

            case 2:
                new BookingController().viewBookingHistory();
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
