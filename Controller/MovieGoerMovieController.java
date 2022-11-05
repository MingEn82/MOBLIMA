package Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import Entities.Cinema;
import Entities.Movie;
import Entities.Review;
import Entities.Showing;
import Utils.DateParser;
import Utils.InputGetter;

/**
 * MovieGoerMovieController is a controller class that handles the movie goer
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-03
 */
public class MovieGoerMovieController extends MovieController {
    /**
     * Utility class to parse inputs
     */
    private InputGetter ip;

    /**
     * Create CineplexController instance
     */
    private CineplexController cineplexController = new CineplexController();

    /**
     * Create MovieDatabaseController instance
     */
    private MovieDatabaseController movieDC = new MovieDatabaseController();

    /**
     * Create Arraylist of showing data (in array string format)
     */
    ArrayList<String[]> filteredShowings;

    /**
     * Constructor for MovieGoerMovieController instance
     */
    public MovieGoerMovieController() {
        ip = new InputGetter();
    }

    /**
     * Function that handles booking tickets and reviews
     */
    public void displayMenu() {
        int choice;
        Movie movieChoice = super.findMovies();

        ShowingsDatabaseController showingsDC = new ShowingsDatabaseController();

        if (movieChoice != null) {
            String movieTitle = movieChoice.getMovieTitle();
            int subChoice;
            String[] showingChoice;

            do {
                System.out.println("+-------------------------------------------------------+");
                System.out.println("|                    Movie Options                      |");
                System.out.println("+-------------------------------------------------------+");
                System.out.println("| 1. Display all showings / Book a ticket               |");
                System.out.println("| 2. View reviews                                       |");
                System.out.println("| 3. Leave a review                                     |");
                System.out.println("| 4. View my reviews                                    |");
                System.out.println("+-------------------------------------------------------+");
                System.out.println("|             Enter 0 to return to main menu            |");
                System.out.println("+-------------------------------------------------------+");
                System.out.println("");

                choice = ip.getInt();
                int phoneNumber;
                float rating;
                String reviewText;
                Review newReview;

                switch (choice) {
                    case 1:
                        // Display All Showings
                        filteredShowings = showingsDC.filterShowings(movieTitle);

                        // Return if no showings are found
                        if (filteredShowings.size() == 0) {
                            System.out.println("No showings found for " + movieTitle + "\n");
                            break;
                        }
                        
                        // Sort by start date
                        Collections.sort(filteredShowings, Comparator.comparing(s -> s[3]));

                        System.out.println(movieTitle + " is showing at these locations");
                        printShowings(filteredShowings);

                        do {
                            System.out.println("Which showing do you want to book for ? (type 0 to quit)");
                            subChoice = ip.getInt();
                            if (subChoice < 0 || subChoice > filteredShowings.size()) {
                                System.out.println("Invalid showing");
                            }
                        } while (subChoice < 0 || subChoice > filteredShowings.size());

                        if (subChoice == 0) {
                            System.out.println("No tickets booked");
                            break;
                        }

                        showingChoice = filteredShowings.get(subChoice - 1);
                        bookTicket(showingChoice[1], showingChoice[2], movieTitle, showingChoice[3]);
                        break;

                    case 2:
                        // View reviews
                        movieDC.printReviews(movieTitle);
                        break;

                    case 3:
                        // Leave a review
                        System.out.println("Enter phone number: ");
                        phoneNumber = ip.getInt();
                        while (phoneNumber < 9999999 || phoneNumber > 100000000) {
                            System.out.println("Invalid phone number");
                            System.out.println("Enter phone number: ");
                            phoneNumber = ip.getInt();
                        }

                        if (movieDC.hasReview(movieTitle, phoneNumber)) {
                            System.out.println("You have already left a review for this movie");
                            System.out.println("Do you want to change your review instead?");

                            do {
                                System.out.println("1. Change review");
                                System.out.println("2. Go back");
                                choice = ip.getInt();
                                switch (choice) {
                                    case 1:
                                        System.out.println("Enter rating (1 - 5)");
                                        rating = ip.getFloat();
                                        while (rating < 1 || rating > 5) {
                                            System.out.println("Invalid rating");
                                            System.out.println("Enter rating (1 - 5): ");
                                            rating = ip.getFloat();
                                        }
                                        rating = Math.round(rating * 100) / 100;
                                        System.out.println("Enter comments (0 to not leave a comment): ");
                                        reviewText = ip.getString();
                                        if (reviewText.equals("0")) {
                                            newReview = new Review(rating, phoneNumber);
                                        } else {
                                            newReview = new Review(rating, phoneNumber, reviewText);
                                        }
                                        movieDC.updateReview(movieTitle, newReview);
                                        break;

                                    case 2:
                                        System.out.println("Review not changed. Goodbye!");
                                        break;

                                    default:
                                        System.out.println("Invalid choice. Try again!");
                                }
                            } while (choice < 1 || choice > 2);

                            break;
                        }

                        System.out.println("Enter rating (0 - 5)");
                        rating = ip.getFloat();
                        while (rating < 1 || rating > 5) {
                            System.out.println("Invalid rating");
                            System.out.println("Enter rating (1 - 5): ");
                            rating = ip.getFloat();
                        }
                        rating = Math.round(rating * 100) / 100;
                        System.out.println("Enter comments (0 to not leave a comment): ");
                        reviewText = ip.getString();
                        if (reviewText.equals("0")) {
                            newReview = new Review(rating, phoneNumber);
                        } else {
                            newReview = new Review(rating, phoneNumber, reviewText);
                        }
                        movieDC.addReview(movieTitle, newReview);
                        break;

                    case 4:
                        System.out.println("Enter phone number: ");
                        phoneNumber = ip.getInt();
                        while (phoneNumber < 9999999 || phoneNumber > 100000000) {
                            System.out.println("Invalid phone number");
                            System.out.println("Enter phone number: ");
                            phoneNumber = ip.getInt();
                        }
                        movieDC.printReviews(movieTitle, phoneNumber);
                        break;

                    case 0:
                        // Go back
                        break;

                    default:
                        System.out.println("Invalid choice! Try again");
                }
            } while (choice != 0);
        }
    }

    /**
     * Helper function to print all showings of a movie
     * @param filteredShowings
     */
    private void printShowings(ArrayList<String[]> filteredShowings) {
        DateParser dp = new DateParser("yyyyMMddhhmm");

        int i = 1;
        for (String[] s : filteredShowings) {
            String cineplexName = s[1];
            String cinemaName = s[2];

            Date showingTime = dp.parseDate(s[3], "yyyyMMddhhmm");
            Calendar cal = Calendar.getInstance();
            cal.setTime(showingTime);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            String day = "";
            switch (dayOfWeek) {
                case 2:
                case 3:
                case 4:
                case 5:
                    day = "Weekday";
                    break;
                case 1:
                case 6:
                case 7:
                    day = "Weekend";
                    break;
                default:
                    day = "";
            }

            if (new SystemSettingController().isPublicHoliday(showingTime)) { day = "Public Holiday"; }
            
            System.out.println(i + ") Location: " + cineplexName + ", " + cinemaName + " ("
                    + cineplexController.getCinemaType(cineplexName, cinemaName) + ")");
            System.out.println("   Movie Type: " + s[4]);
            System.out.println("   When: " + dp.formatDate(showingTime, "dd/MM/yyyy HH:mm") + " (" + day + ")");
            System.out.println("");
            i++;
        }
    }

    /**
     * Books a ticket
     * @param cineplexName
     * @param cinemaName
     * @param movieTitle
     * @param startDate
     * @return true if booking is successful, false otherwise
     */
    private boolean bookTicket(String cineplexName, String cinemaName, String movieTitle, String startDate) {
        DateParser dp = new DateParser("yyyyMMddHHmm");
        cineplexController.refreshData();

        Cinema cinema = cineplexController.findCinema(cineplexName, cinemaName);
        if (cinema == null) {
            System.out.println("Cinema not found. Something went wrong in MovieGoerMovieController.bookticket");
            return false;
        }

        // Get cinema type
        String cinemaType = cinema.getCinemaType();
        // Get UID
        String UID = cineplexController.generateUID(cineplexName, cinemaName);

        for (Showing showing : cinema.getShowings()) {
            if (showing.getStartDate().compareTo(dp.parseDate(startDate)) == 0
                    && showing.getMovieTitle().equals(movieTitle)) {
                // Add Movie Type
                String movieType = showing.getMovieType();
                // Get Movie Duration
                int movieDuration = movieDC.getMovieDuration(movieTitle);
                // Get SeatID
                int choice;
                do {
                    // Print seats
                    showing.printShowingDetails();
                    cinema.printScreenLayout();
                    showing.printSeats(cinema.getAisles());
                    cinema.printSeatNumbers();
                    cinema.printEntranceLayout();
                    System.out.println("[ ] - normal seats");
                    System.out.println("\\ / - wide seats (extra cost needed)\n");

                    System.out.println("Enter SeatID (e.g. A09): ");
                    String seatID = ip.getString();

                    if (!showing.isSeatBooked(seatID)) {
                        addOneToTotalSales(movieTitle);
                        if (new BookingController().newBooking(UID, cineplexName, cinemaName, seatID, movieTitle,
                            movieDuration, movieType, cinemaType, dp.parseDate(startDate), -1, showing.isWideSeat(seatID))) {
                            new ShowingsDatabaseController().addBooking(movieTitle, cineplexName, cinemaName, startDate, seatID);
                        }
                        break;
                    } else {
                        System.out.println("Invalid seat chosen!");
                        System.out.println("Type 1 to choose a different seat (0 to exit booking):");
                        choice = ip.getInt();
                        while (choice != 0 && choice != 1) {
                            System.out.println("Invalid choice! Try again");
                            choice = ip.getInt();
                        }
                        if (choice == 0) { break; }
                    }
                } while (true);
                
            }
        }

        return false;
    }
}
