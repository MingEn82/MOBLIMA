package Controller;

/**
 * MovieGoerController class handles the choice of the movie goer
 * 
 * @author Soh Zu Wei
 * @version 1.0
 * @since 2022-11-02
 */
public class MovieGoerController {

    /**
     * Variable that holds the Movie Controller Object
     */
    private MovieController movieGoerMovieController = new MovieGoerMovieController();

    /**
     * Handles the choice of movie goer
     * 
     * @param choice    choice of movie goer
     */
    public void processMovieGoerChoice(int choice) {
        switch (choice) {
            case 1:
                movieGoerMovieController.displayMenu();
                break;

            case 2:
                new BookingController().viewBookingHistory();
                break;

            case 3:
                movieGoerMovieController.viewTopMoviesForMovieGoer();
                break;

            default:
                System.out.println("Please enter a valid choice");
                break;
        }
    }

}
