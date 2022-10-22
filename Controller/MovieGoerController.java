package Controller;



public class MovieGoerController {
    MovieController movieGoerMovieController = new MovieGoerMovieController();
    BookingController bookingController = new BookingController();


    public void processMovieGoerChoice(int choice)
    {
        System.out.println("OK movie goer choice works, choice is "+ choice);
        switch(choice){
            case 1:
            System.out.println("Executing movieGoerMovieController.findMovies()");
            //movieGoerMovieController.findMovies();
            break;

            case 2:
            System.out.println("Executing bookingController.newBooking");
            bookingController.newBooking();
            break;

            case 3:
            //System.out.println("Executing bookingController.viewBookingHistory()");
            bookingController.viewBookingHistory();
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
