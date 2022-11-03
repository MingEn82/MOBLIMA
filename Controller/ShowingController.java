package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entities.Cinema;
import Entities.Cineplex;
import Entities.Movie;
import Utils.DateParser;
import Utils.InputGetter;

/**
 * ShowingController is a controller class that handles showings
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-03
 */
public class ShowingController {
    /**
     * Create ShowingsDatabaseController instance
     */
    private ShowingsDatabaseController showingsDC;

    /**
     * Scanner to take in inputs
     */
    Scanner sc;

    /**
     * Utility class to parse dates
     */
    DateParser dp;

    /**
     * Utility class to parse inputs
     */
    InputGetter ip;

    /**
     * Constructor for ShowingController instance
     */
    public ShowingController() {
        showingsDC = new ShowingsDatabaseController();
        sc = new Scanner(System.in);
        dp = new DateParser("yyyyMMddHHmm");
        ip = new InputGetter();
    }

    /**
     * Function that serves the UI for the admin and handles choice
     */
    public void displayMenu() {
        int choice;

        do {
            System.out.println("============= Showing Controller =============");
            System.out.println("1. Create New Showing");
            System.out.println("2. Update Showing");
            System.out.println("3. Delete Showing");
            System.out.println("4. Return");
            System.out.println("==============================================");
            System.out.println("");
            System.out.println("Enter choice:");
            choice = ip.getInt();

            switch (choice) {
                case 1:
                    this.createShowing();
                    break;
                case 2:
                    this.updateShowing();
                    break;
                case 3:
                    this.deleteShowing();
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }
    
    /**
     * Creates new showing
     */
    public void createShowing() {
        ArrayList<Movie> movies = new AdminMovieController().filterCurrentMovies();
        int i = 0;
        int choice;
        String movieTitle = null;
        System.out.println("Select movie (type 0 to exit):");
        for (Movie m : movies) {
            System.out.println(++i + ") " + m.getMovieTitle());
        }
        do {
            choice = ip.getInt();
            if (choice == 0) {
                return;
            } else if (choice >= 1 && choice <= i) {
                movieTitle = movies.get(choice-1).getMovieTitle();
            } else {
                System.out.println("Invalid choice! Try Again");
            }
        } while (choice < 0 || choice > i);

        ArrayList<Cineplex> cineplexes = new CineplexDatabaseController().getCineplexes();
        i = 0;
        Cineplex chosenCineplex = null;
        Cinema chosenCinema = null;
        System.out.println("Select Cinema (type 0 to exist):");
        for (Cineplex cineplex : cineplexes) {
            for (Cinema cinema : cineplex.getCinemas()) {
                System.out.println(++i + ") " + cineplex.getCineplexName() + ", " + cinema.getCinemaName() + " (" + cinema.getCinemaType() + ")");
            }
        }
        do {
            choice = ip.getInt();
            if (choice == 0) {
                return;
            } else if (choice >= 1 && choice <= i) {
                int cineplexIndex, cinemaIndex;
                cineplexIndex = Math.floorDiv(choice-1, cineplexes.size());
                chosenCineplex = cineplexes.get(cineplexIndex);
                cinemaIndex = (choice-1) % chosenCineplex.getCinemas().size();
                chosenCinema = chosenCineplex.getCinemas().get(cinemaIndex);
            } else {
                System.out.println("Invalid choice! Try Again");
            }
        } while (choice < 0 || choice > i);

        System.out.println("Enter Date (yyyyMMddHHmm format): ");
        String date = ip.getString();

        i = 0;
        String movieType = null;
        System.out.println("Enter movie type (type 0 to exit): ");
        System.out.println("1. 2D");
        System.out.println("2. 3D");
        System.out.println("3. Blockbuster");
        do {
            choice = ip.getInt();
            switch (choice) {
                case 1:
                    movieType = "2D";
                    break;
                case 2:
                    movieType = "3D";
                    break;
                case 3:
                    movieType = "Blockbuster";
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice!");

            }
        } while (choice < 0 || choice > 3);

        System.out.println("Creating New Showing");
        // To do: Add time check
        if (showingsDC.addNewShowing(movieTitle, chosenCineplex.getCineplexName(), chosenCinema.getCinemaName(), date, movieType)) {
            System.out.println("Showing successfully created");
        } else {
            System.out.println("Error! Showing already exists");
        }
    }

    /**
     * Updates showing
     */
    public void updateShowing() {
        ArrayList<Movie> movies = new AdminMovieController().filterCurrentMovies();
        int i = 0;
        int choice;
        String oldMovieTitle = null;
        String[] oldShowing = null;
        System.out.println("\nSelect movie (type 0 to exit):");
        for (Movie m : movies) {
            System.out.println(++i + ") " + m.getMovieTitle());
        }
        do {
            choice = ip.getInt();
            if (choice == 0) {
                return;
            } else if (choice >= 1 && choice <= i) {
                oldMovieTitle = movies.get(choice-1).getMovieTitle();
            } else {
                System.out.println("Invalid choice! Try Again");
            }
        } while (choice < 0 || choice > i);

        ArrayList<String[]> showings = showingsDC.filterShowings(oldMovieTitle);
        if (showings.size() == 0) {
            System.out.println("No showings found for " + oldMovieTitle);
            System.out.println("Returning to menu...");
            return;
        }

        i = 0;
        String oldCineplexName = null, oldCinemaName = null, oldStartDate = null, oldMovieType = null;
        System.out.println("\nAll showings for " + oldMovieTitle);
        for (String[] s : showings) {
            System.out.println(++i + ") " + s[1] + ", " + s[2] + ", " + s[3]);
        }
        do {
            choice = ip.getInt();
            if (choice == 0) {
                return;
            } else if (choice >= 1 && choice <= i) {
                oldShowing = showings.get(choice - 1);
                oldCineplexName = oldShowing[1];
                oldCinemaName = oldShowing[2];
                oldStartDate = oldShowing[3];
                oldMovieType = oldShowing[4];
            } else {
                System.out.println("Invalid choice! Try Again");
            }
        } while (choice < 0 || choice > i);

        // Checks if there are existing bookings for showing
        if (new BookingController().bookingExistForShowing(oldCineplexName, oldCinemaName, oldMovieTitle, dp.parseDate(oldStartDate, "yyyyMMddHHmm"))) {
            System.out.println("Error! There exist bookings for this showing. Cancelling update...");
            return;
        }

        String newMovieTitle = oldMovieTitle, 
            newCineplexName = oldCineplexName, 
            newCinemaName = oldCinemaName, 
            newStartDate = oldStartDate, 
            newMovieType = oldMovieType;
        do {
            System.out.println("""
                +-----------------------------------------------------------+
                |                   Update Showing Menu                     |
                |-----------------------------------------------------------|
                | 1. Change Movie                                           |
                | 2. Change Movie Type                                      |
                | 3. Change Cineplex                                        |
                | 4. Change Cinema                                          |
                | 5. Change Start Date                                      |
                |-----------------------------------------------------------|
                |    Enter 0 to save changes and go back to Showing menu    |
                +-----------------------------------------------------------+
            """);
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter new movie title: ");
                    newMovieTitle = sc.nextLine();
                    // To do: check if movie is valid using movie controller
                    break;

                case 2:
                    do {
                        System.out.println("Enter new movie type (2D, 3D, Blockbuster): ");
                        newMovieType = sc.nextLine();
                        if (!newMovieType.equals("2D") && !newMovieType.equals("3D") && !newMovieType.equals("Blockbuster"))
                            System.out.println("Invalid movie type, try again");
                    } while (!newMovieType.equals("2D") && !newMovieType.equals("3D") && !newMovieType.equals("Blockbuster"));
                    break;

                case 3:
                    System.out.println("Enter new cineplex name: ");
                    newCineplexName = sc.nextLine();
                    // To do: check if cineplex is valid using cineplex controller
                    break;

                case 4:
                    System.out.println("Enter new cinema name: ");
                    newCinemaName = sc.nextLine();
                    // To do: check if cinema is valid using cineplex controller
                    break;
                
                case 5:
                    System.out.println("Enter new start date (yyyyMMddHHmm format): ");
                    newStartDate = sc.nextLine();
                    // To do: check if start date is valid using showing controller
                    break;
                
                case 0:
                    String[] newShowing = { newMovieTitle, newCineplexName, newCinemaName, newStartDate, newMovieType };
                    showingsDC.updateShowing(oldShowing, newShowing);
                    System.out.println("Showing updated");
                    break;
                
                default:
                    System.out.println("Invalid choice. Try Again");
                    
            }
        } while (choice != 0);
    }

    /**
     * Deletes a showing
     */
    public void deleteShowing() {
        sc.nextLine();
        System.out.println("Enter movie Title: ");
        String movieTitle = sc.nextLine();
        System.out.println("Enter Cineplex Name: ");
        String cineplexName = sc.nextLine();
        System.out.println("Enter Cinema Name: ");
        String cinemaName = sc.nextLine();
        System.out.println("Enter Date (yyyyMMddHHmm format): ");
        Date startDate = dp.parseDate(sc.nextLine(), "yyyyMMddHHmm");

        // Checks if there are existing bookings for showing
        if (new BookingController().bookingExistForShowing(cineplexName, cinemaName, movieTitle, startDate)) {
            System.out.println("Error! There exist bookings for this showing. Cancelling update...");
            return;
        }

        // Checks if showing exists
        String[] oldShowing = showingsDC.getShowing(cineplexName, cinemaName, movieTitle, dp.formatDate(startDate, "yyyyMMddHHmm"));
        if (oldShowing == null) {
            System.out.println("Error! Showing is not found");
        }

        showingsDC.deleteShowing(movieTitle, cineplexName, cinemaName, dp.formatDate(startDate, "yyyyMMddHHmm"));
        System.out.println("Successfully deleted showing");
    }
}
