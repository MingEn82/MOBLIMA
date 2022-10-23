package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entities.Cinema;
import Entities.Cineplex;
import Entities.Movie;
import Entities.Showing;
import Utils.DateParser;

public class MovieGoerMovieController extends MovieController {
    private Scanner sc;
    private BookingController bookingController = new BookingController();
    private ShowingsDatabaseController showingsDC = new ShowingsDatabaseController();
    private CineplexDatabaseController cineplexDC = new CineplexDatabaseController();
    private MovieDatabaseController movieDC = new MovieDatabaseController();
    ArrayList<String[]> filteredShowings;


    public MovieGoerMovieController() {
        sc = new Scanner(System.in);
    }

    public Movie findMovies() {
        int choice;
        Movie movieChoice = super.findMovies();

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
                        System.out.println(movieChoice.getMovieTitle() + " is showing at these locations!");
                        printShowings(filteredShowings);
                        break;
                    case 2:
                        // Book a ticket
                        sc.nextLine();
                        System.out.println("Enter Cineplex Name:");
                        String cineplexName = sc.nextLine();
                        System.out.println("Enter Cinema Name");
                        String cinemaName = sc.nextLine();
                        filteredShowings = showingsDC.filterShowings(cineplexName, cinemaName, movieChoice.getMovieTitle());
                        if (filteredShowings.size() == 0) {
                            System.out.println("Error! No showings found for " + cineplexName + " and " + cinemaName);
                        } else {
                            System.out.println("Available showings for " + movieChoice.getMovieTitle() + " at " + cineplexName + " and " + cinemaName);
                            printShowings(filteredShowings);
                            while (true) {
                                System.out.println("Enter which showing to book for");
                                int showingIdx = sc.nextInt();
                                if (showingIdx >= 0 && showingIdx <= filteredShowings.size()) {
                                    String[] showingChoice = filteredShowings.get(showingIdx-1);
                                    DateParser dp = new DateParser("YYYYMMddHHmm");
                                    if (!bookTicket(cineplexDC.getCineplexes(), cineplexName, cinemaName, movieChoice.getMovieTitle(), dp.parseDate(showingChoice[3])))
                                        System.out.println("Error!");
                                    // bookingController.newBooking(info[1], cineplexName, cinemaName, seatID, movieChoice.getMovieTitle(), movieDuration, info[2], info[0], dp.parseDate(showingChoice[3]), price)
                                    break;
                                } else {
                                    System.out.println("Invalid choice. Please reenter");
                                }
                            }
                        }
                        break;

                    case 3:
                        // View reviews
                    case 4:
                        // Leave a review
                    case 5:
                        // Go back
                    default:
                        System.out.println("Invalid choice! Try again");
                }
            } while (choice != 5);
        }

        return null;
    }

    private void printShowings(ArrayList<String[]> filteredShowings) {
        DateParser dp = new DateParser("YYYYMMddhhmm");
        int i = 1;
        for (String[] s : filteredShowings) {
            String cineplexName = s[1];
            String cinemaName = s[2];
            Date showingTime = dp.parseDate(s[3], "YYYYMMddhhmm");
            System.out.println(i + ") Location: " + cineplexName + ", " + cinemaName);
            System.out.println("   When: " + dp.formatDate(showingTime, "dd/MM/YYYY HH:mm"));
            System.out.println("");
        }
    }

    /**
     * Returns showing information for booking tickets
     * @param cineplexName
     * @param cinemaName
     * @param movieTitle
     * @param startDate
     * @return              ArrayList of String containing CinemaType, UID and MovieType
     */
    private boolean bookTicket(ArrayList<Cineplex> cineplexes, String cineplexName, String cinemaName, String movieTitle, Date startDate) {
        DateParser dp = new DateParser("YYYYMMddHHmm");

        for (Cineplex cineplex : cineplexes) {
            if (!cineplex.getCineplexName().equals(cineplexName))
                continue;
            for (Cinema cinema : cineplex.getCinemas()) {
                if (!cinema.getCinemaName().equals(cinemaName))
                    continue;
                // Add Cinema Type
                String cinemaType = cinema.getCinemaType();
                // Add UID
                String TID = cineplex.getCineplexID() + cinema.getCinemaNumber() + dp.formatDate(new Date());
                for (Showing showing : cinema.getShowings()) {
                    if (showing.getStartDate().compareTo(startDate) == 0 && showing.getMovieTitle().equals(movieTitle)) {
                        // Add Movie Type
                        String movieType = showing.getMovieType();
                        // Print seats
                        showing.print(cinema.getAisleArray());
                        // Get Movie Duration
                        int movieDuration = movieDC.getMovieDuration(movieTitle);

                        // Get SeatID
                        System.out.println("Enter SeatID (e.g. A09): ");
                        sc.nextLine();
                        String seatID = sc.nextLine();
                        if (!showing.isSeatBooked(seatID)) {
                            return bookingController.newBooking(TID, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, startDate, -1);
                        }
                    }
                }
            }
        }

        return false;
    }
}
