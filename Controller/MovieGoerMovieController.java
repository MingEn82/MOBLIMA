package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entities.Movie;
import Utils.DateParser;

public class MovieGoerMovieController extends MovieController {
    private Scanner sc;
    private BookingController bookingController = new BookingController();
    private ShowingsDatabaseController showingsDC = new ShowingsDatabaseController();
    private CineplexDatabaseController cineplexDC = new CineplexDatabaseController();
    ArrayList<String[]> filteredShowings;


    public MovieGoerMovieController() {
        sc = new Scanner(System.in);
    }

    public void findMovies() {
        int choice;
        Movie movieChoice = null;

        do {
            System.out.println("================ Find Movies (Customer) ================");
            System.out.println("1. List all movies");
            System.out.println("2. Coming Soon");
            System.out.println("3. Preview");
            System.out.println("4. Now Showing");
            System.out.println("5. Search movie by title");
            System.out.println("6. Back to Customer Main Menu");
            System.out.println("=======================================================");
            System.out.println("");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    movieChoice = displayMovies(choice);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (choice < 1 || choice > 6);

        if (movieChoice != null) {
            do {
                System.out.println("===================== Movie Options =====================");
                System.out.println("1. Display all showings");
                System.out.println("2. Book a ticket");
                System.out.println("3. View reviews");
                System.out.println("4. Leave a review");
                System.out.println("5. Back to Customer Main Menu");
                System.out.println("=========================================================");
                System.out.println("");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        // Display All Showings
                        filteredShowings = showingsDC.filterShowings(movieChoice.getMovieTitle());
                        DateParser dp = new DateParser("YYYYMMddHHmm");
                        System.out.println(movieChoice.getMovieTitle() + " is showing at these locations!");
                        for (String[] s : filteredShowings) {
                            String cineplexName = s[1];
                            String cinemaName = s[2];
                            Date showingTime = dp.parseDate(s[3]);
                            System.out.println("Location: " + cineplexName + ", " + cinemaName);
                            System.out.println("When: " + dp.formatDate(showingTime, "dd/MM/YYYY HH:mm"));
                        }
                    case 2:
                        // Book a ticket
                        System.out.println("Enter Cineplex Name:");
                        String cineplexName = sc.nextLine();
                        System.out.println("Enter Cinema Name");
                        String cinemaName = sc.nextLine();
                        filteredShowings = showingsDC.filterShowings(cineplexName, cinemaName, movieChoice.getMovieTitle());
                        if (filteredShowings.size() == 0) {
                            System.out.println("Error! No showings found for " + cineplexName + " and " + cinemaName);
                        } else {
                            String TID = cineplexDC.getIDs(cineplexName, cinemaName);
                        }

                    case 3:
                        // View reviews
                    case 4:
                        // Leave a review
                    case 5:
                        // Go back
                    default:
                        System.out.println("Invalid choice! Try again");
                }
            } while (choice < 1 || choice > 5);
        }
    }
}
