package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Entities.Cinema;
import Entities.Movie;
import Entities.Review;
import Entities.Showing;
import Utils.DateParser;

public class MovieGoerMovieController extends MovieController {
    private Scanner sc;
    private BookingController bookingController = new BookingController();
    private CineplexController cineplexController = new CineplexController();
    private ShowingsDatabaseController showingsDC = new ShowingsDatabaseController();
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
            int subChoice;
            String[] showingChoice;

            do {
                System.out.println("===================== Movie Options =====================");
                System.out.println("1. Display all showings / Book a ticket");
                System.out.println("2. View reviews");
                System.out.println("3. Leave a review");
                System.out.println("4. View my reviews");
                System.out.println("=========================================================");
                System.out.println("              Enter 0 to return to main menu");
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
                    
                        
                        do {
                            System.out.println("Which showing do you want to book for ? (type 0 to quit)");
                            subChoice = sc.nextInt();
                            if (subChoice < 0 || subChoice > filteredShowings.size()) {
                                System.out.println("Invalid showing");
                            }
                        } while (subChoice < 0 || subChoice > filteredShowings.size());

                        if (subChoice == 0) {
                            System.out.println("No tickets booked");
                            break;
                        }

                        showingChoice = filteredShowings.get(subChoice-1);
                        bookTicket(showingChoice[1], showingChoice[2], movieTitle, showingChoice[3]);

                        break;

                        
                        case 2:
                        // View reviews
                        movieDC.printReviews(movieTitle);
                        break;
                        
                        case 3:
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

                        case 4:
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

                    case 0:
                        // Go back
                        break;
                    default:
                        System.out.println("Invalid choice! Try again");
                }
            } while (choice != 0);
        }

        return null;
    }

    private void printShowings(ArrayList<String[]> filteredShowings) {
        DateParser dp = new DateParser("yyyyMMddhhmm");
        
        int i = 1;
        for (String[] s : filteredShowings) {
            String cineplexName = s[1];
            String cinemaName = s[2];
            Date showingTime = dp.parseDate(s[3], "yyyyMMddhhmm");
            System.out.println(i + ") Location: " + cineplexName + ", " + cinemaName + " (" + cineplexController.getCinemaType(cineplexName, cinemaName) + ")");
            System.out.println("   Movie Type: " + s[4]);
            System.out.println("   When: " + dp.formatDate(showingTime, "dd/MM/yyyy HH:mm"));
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
                    addOneToTotalSales(movieTitle);
                    return bookingController.newBooking(UID, cineplexName, cinemaName, seatID, movieTitle, movieDuration, movieType, cinemaType, dp.parseDate(startDate), -1);
                }
            }
        }

        return false;
    }
}
