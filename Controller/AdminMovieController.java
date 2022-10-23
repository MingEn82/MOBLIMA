package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AdminMovieController extends MovieController{
    private ShowingsDatabaseController showingsDC;
    private MovieDatabaseController moviesDC;
    private BookingsDatabaseController bookingsDC;
    Scanner sc;

    
    public AdminMovieController() {
        showingsDC = new ShowingsDatabaseController();
        moviesDC = new MovieDatabaseController();
        bookingsDC = new BookingsDatabaseController();
        sc = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;

        do {
            System.out.println("============= Admin Movie Controller =============");
            System.out.println("1. Show all movies");
            System.out.println("2. Create New Movie");
            System.out.println("3. Update Movie Showing Status");
            System.out.println("4. Delete Movie");
            System.out.println("5. Return");
            System.out.println("==================================================");
            System.out.println("");
            System.out.println("Enter choice:");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    this.createMovie();
                    break;
                case 2:
                    this.updateMovie();
                    break;
                case 3:
                    this.deleteMovie();
                    break;
                case 4:
                    this.createMovie();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    public void createMovie() {
        sc.nextLine();
        System.out.println("Enter movie title: ");
        String movieTitle = sc.nextLine();
        System.out.println("Enter showing status: ");
        String showingStatus = sc.nextLine();
        System.out.println("Enter movie duration (in mins): ");
        int movieDuration = sc.nextInt();
        System.out.println("Enter movie synopsis: ");
        String synopsis = sc.nextLine();
        System.out.println("Enter movie Title: ");
        String director = sc.nextLine();
        ArrayList<String> casts = new ArrayList<String>();
        String cast;
        do {
            System.out.println("Enter cast (0 to stop): ");
            cast = sc.nextLine();
            if (!cast.equals("0"))
                casts.add(cast);
        } while (!cast.equals("0"));
        moviesDC.addNewMovie(movieTitle, showingStatus, synopsis, director, casts.toArray(new String[0]), movieDuration);
    }

    // Not sure if correct implementation
    public void updateMovie() {
        sc.nextLine();
        System.out.println("Enter movie title: ");
        String movieTitle = sc.nextLine();
        System.out.println("Enter new showing status (Coming soon, Preview, Now Showing, End of Showing): ");
        String newShowingStatus = sc.nextLine();

        if (moviesDC.changeShowingStatus(movieTitle, newShowingStatus)) {
            System.out.println(movieTitle + " successfully updated with " + newShowingStatus);

            if (newShowingStatus.equals("Coming Soon") || newShowingStatus.equals("End of Showing")) {
                showingsDC.deleteShowings(movieTitle);
                bookingsDC.deleteBookings(movieTitle);
            }
        } else {
            System.out.println("Aborting..");
            return;
        }
    }

    public void deleteMovie() {
        sc.nextLine();
        System.out.println("Enter movie title: ");
        String movieTitle = sc.nextLine();
        moviesDC.deleteMovie(movieTitle);
        showingsDC.deleteShowings(movieTitle);
        bookingsDC.deleteBookings(movieTitle);
    }
}
