package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entities.Cinema;
import Entities.Cineplex;
import Entities.Movie;
import Entities.Review;
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
            String movieTitle = movieChoice.getMovieTitle();

            do {
                System.out.println("===================== Movie Options =====================");
                System.out.println("1. Display all showings");
                System.out.println("2. Book a ticket");
                System.out.println("3. View reviews");
                System.out.println("4. Leave a review");
                System.out.println("5. View my reviews");
                System.out.println("6. Back to Customer Main Menu");
                System.out.println("=========================================================");
                System.out.println("");

                choice = sc.nextInt();
                int phoneNumber;
                float rating;
                String reviewText;
                Review newReview;

                switch (choice) {
                    case 1:
                        // Display All Showings
                        filteredShowings = showingsDC.filterShowings(movieTitle);
                        System.out.println(movieTitle + " is showing at these locations!");
                        printShowings(filteredShowings);
                        break;

                    case 2:
                        // Book a ticket
                        sc.nextLine();
                        System.out.println("Enter Cineplex Name:");
                        String cineplexName = sc.nextLine();
                        System.out.println("Enter Cinema Name");
                        String cinemaName = sc.nextLine();
                        filteredShowings = showingsDC.filterShowings(cineplexName, cinemaName, movieTitle);
                        if (filteredShowings.size() == 0) {
                            System.out.println("Error! No showings found for " + cineplexName + " and " + cinemaName);
                        } else {
                            System.out.println("Available showings for " + movieTitle + " at " + cineplexName + " and " + cinemaName);
                            printShowings(filteredShowings);
                            while (true) {
                                System.out.println("Enter which showing to book for");
                                int showingIdx = sc.nextInt();
                                if (showingIdx >= 0 && showingIdx <= filteredShowings.size()) {
                                    String[] showingChoice = filteredShowings.get(showingIdx-1);
                                    bookTicket(cineplexDC.getCineplexes(), cineplexName, cinemaName, movieTitle, showingChoice[3]);
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
                        movieDC.printReviews(movieTitle);
                        break;

                    case 4:
                        // Leave a review
                        sc.nextLine();
                        System.out.println("Enter phone number: ");
                        phoneNumber = sc.nextInt();
                        while (phoneNumber < 9999999 || phoneNumber > 100000000) {
                            System.out.println("Invalid phone number");
                            System.out.println("Enter phone number: ");
                            phoneNumber = sc.nextInt();
                        }

                        if (movieDC.hasReview(movieTitle, phoneNumber)) {
                            System.out.println("You have already left a review for this movie");
                            System.out.println("Do you want to change your review instead?");

                            do {
                                System.out.println("1. Change review");
                                System.out.println("2. Go back");
                                choice = sc.nextInt();
                                switch (choice) {
                                    case 1:
                                    System.out.println("Enter rating (0 - 5)");
                                    rating = sc.nextFloat();
                                    while (rating < 0 || rating > 5) {
                                        System.out.println("Invalid rating");
                                        System.out.println("Enter rating (0 - 5): ");
                                        rating = sc.nextFloat();
                                    }
                                    rating = Math.round(rating * 100) / 100;
                                    System.out.println("Enter comments (0 to not leave a comment): ");
                                    sc.nextLine();
                                    reviewText = sc.nextLine();
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
                        rating = sc.nextFloat();
                        while (rating < 0 || rating > 5) {
                            System.out.println("Invalid rating");
                            System.out.println("Enter rating (0 - 5): ");
                            rating = sc.nextFloat();
                        }
                        rating = Math.round(rating * 100) / 100;
                        System.out.println("Enter comments (0 to not leave a comment): ");
                        sc.nextLine();
                        reviewText = sc.nextLine();
                        if (reviewText.equals("0")) {
                            newReview = new Review(rating, phoneNumber);
                        } else {
                            newReview = new Review(rating, phoneNumber, reviewText);
                        }
                        movieDC.addReview(movieTitle, newReview);
                        break;

                    case 5:
                        sc.nextLine();
                        System.out.println("Enter phone number: ");
                        phoneNumber = sc.nextInt();
                        while (phoneNumber < 9999999 || phoneNumber > 100000000) {
                            System.out.println("Invalid phone number");
                            System.out.println("Enter phone number: ");
                            phoneNumber = sc.nextInt();
                        }
                        movieDC.printReviews(movieTitle, phoneNumber);
                        break;

                    case 6:
                        // Go back
                        break;
                    default:
                        System.out.println("Invalid choice! Try again");
                }
            } while (choice != 6);
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
            System.out.println(i + ") Location: " + cineplexName + ", " + cinemaName + " (" + s[4] + ")");
            System.out.println("   When: " + dp.formatDate(showingTime, "dd/MM/YYYY HH:mm"));
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
     * @return              true if booking is successful, false otherwise
     */
    private boolean bookTicket(ArrayList<Cineplex> cineplexes, String cineplexName, String cinemaName, String movieTitle, String startDate) {
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
                    if (showing.getStartDate().compareTo(dp.parseDate(startDate)) == 0 && showing.getMovieTitle().equals(movieTitle)) {
                        // Add Movie Type
                        String movieType = showing.getMovieType();
                        // Print seats
                        showing.printShowingDetails();
                        cinema.printScreenLayout();
                        showing.printSeats(cinema.getAisles());
                        cinema.printEntranceLayout();
                        // Get Movie Duration
                        int movieDuration = movieDC.getMovieDuration(movieTitle);

                        // Get SeatID
                        System.out.println("Enter SeatID (e.g. A09): ");
                        sc.nextLine();
                        String seatID = sc.nextLine();
                        if (!showing.isSeatBooked(seatID)) {
                            showingsDC.addBooking(movieTitle, cineplexName, cinemaName, startDate, seatID);
                            return bookingController.newBooking(TID, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, dp.parseDate(startDate), -1);
                        }
                    }
                }
            }
        }

        return false;
    }
}
