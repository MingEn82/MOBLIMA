package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entities.AgeRating;
import Entities.Movie;
import Utils.InputGetter;

/**
 * This class extends the MovieController class and implements additional functionalities for admin staff only
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-03
 */
public class AdminMovieController extends MovieController{
    /**
     * Create a movieDatabaseController instance
     */
    private MovieDatabaseController moviesDC;

    /**
     * Create scanner to System.in
     */
    Scanner sc;

    /*
     * Create utility input getter class to parse inputs
     */
    InputGetter ip;

    /**
     * Constructor for AdminMovieController class
     */
    public AdminMovieController() {
        moviesDC = new MovieDatabaseController();
        sc = new Scanner(System.in);
        ip = new InputGetter();
    }

    /**
     * Main menu UI for AdminMovieController
     */
    public void displayMenu() {
        int choice = -1;

        do {
            System.out.println("============= Admin Movie Controller =============");
            System.out.println("1. Show all movies");
            System.out.println("2. Create New Movie");
            System.out.println("3. Update Movie");
            System.out.println("4. Delete Movie");
            System.out.println("5. Return");
            System.out.println("==================================================");
            System.out.println("");
            System.out.println("Enter choice:");
            choice = ip.getInt();

            switch (choice) {
                case 1:
                    this.displayMovies(1);
                    break;
                case 2:
                    System.out.println("\n=============== Creating new movie ===============");
                    this.createMovie();
                    break;
                case 3:
                    System.out.println("\n=============== Updating movie ===============");
                    this.updateMovie();
                    break;
                case 4:
                    System.out.println("\n=============== Deleting movie ===============");
                    this.deleteMovie();
                    break;
                case 5:
                    System.out.println("\nReturning to main menu..");
                    break;
                default:
                    System.out.println("\nInvalid choice.");
            }
        } while (choice != 5);
    }

    /**
     * Creates a new movie
     */
    public void createMovie() {
        System.out.println("Enter movie title: ");
        String movieTitle = ip.getString();
        int choice;

        if (moviesDC.movieExists(movieTitle)) {
            System.out.println("Movie already exists!");
            return;
        }
        
        String showingStatus = null;
        do {
            System.out.println("\nSelect showing status");
            System.out.println("1. Coming Soon");
            System.out.println("2. Preview");
            System.out.println("3. Now Showing");
            System.out.println("4. End of Showing");
            System.out.println("0. Go back\n");
            
            choice = ip.getInt();

            switch (choice) {
                case 1:
                    showingStatus = "Coming Soon";
                    break;
                case 2:
                    showingStatus = "Preview";
                    break;
                case 3:
                    showingStatus = "Now Showing";
                    break;
                case 4:
                    showingStatus = "End of Showing";
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice");

            }
        } while (choice < 0 || choice > 4);
        
        int movieDuration = -1;
        do {
            System.out.println("\nEnter movie duration (in mins): ");
            movieDuration = ip.getInt();
            if (movieDuration == -1) {
                System.out.println("Invalid movie duration!");
            }
        } while (movieDuration == -1);
        
        AgeRating ageRating = null;
        do {
            System.out.println("\n1. G");
            System.out.println("2. PG");
            System.out.println("3. PG13");
            System.out.println("4. NC16");
            System.out.println("5. M18");
            System.out.println("6. R21");
            System.out.println("0. Go back");
            
            choice = ip.getInt();

            switch (choice) {
                case 1:
                    ageRating = AgeRating.G;
                    break;
                case 2:
                    ageRating = AgeRating.PG;
                    break;
                case 3:
                    ageRating = AgeRating.PG13;  
                    break;
                case 4:
                    ageRating = AgeRating.NC16;
                    break;
                case 5:
                    ageRating = AgeRating.M18;
                    break;
                case 6:
                    ageRating = AgeRating.R21;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice");

            }
        } while (choice < 0 || choice > 6);
        
        System.out.println("\nEnter movie synopsis: ");
        String synopsis = ip.getString();
        
        System.out.println("\nEnter director: ");
        String director = ip.getString();
        
        ArrayList<String> casts = new ArrayList<String>();
        String cast;
        do {
            System.out.println("\nEnter at least 2 cast members (0 to stop): ");
            cast = sc.nextLine();
            if (!cast.equals("0"))
                casts.add(cast);
            else if (cast.equals("0") && casts.size() < 2) {
                System.out.printf("You must enter at least %d more cast members\n", 2 - casts.size());
            }
        } while (casts.size() < 2 || !cast.equals("0"));
        moviesDC.addNewMovie(movieTitle, showingStatus, synopsis, ageRating, director, casts.toArray(new String[0]), movieDuration);
        System.out.println("Movie created successfully. Returning to menu...\n");
    }

    /**
     * Updates current movie
     */
    public void updateMovie() {   
        ArrayList<Movie> movies = moviesDC.getMovies();
        Movie oldMovie = null;
        int i = 1, size = movies.size(), choice;
        for (Movie m : movies) {
            System.out.println(i + ". " + m.getMovieTitle());
            i++;
        }
        do {
            System.out.println("\nEnter movie choice (0 to exit): ");
            choice = sc.nextInt();
            if (choice < 0 || choice > size)
                System.out.println("Invalid Choice");
        } while (choice < 0 || choice > size);

        if (choice == 0) {
            System.out.println("No changes made to movies. Returning to menu..");
        } else {
            oldMovie = movies.get(choice - 1);
        }

        String newMovieTitle = oldMovie.getMovieTitle(),
            newShowingStatus = oldMovie.getShowingStatus(),
            newSynopsis = oldMovie.getSynopsis(),
            newDirector = oldMovie.getDirector();
        int newDuration = oldMovie.getDuration();
        List<String> newCast = oldMovie.getAllCast();
        AgeRating newAgeRating = oldMovie.getAgeRating();

        do {
            System.out.println("\n========= Updating " + newMovieTitle + " =========");
            System.out.println("1. Change Movie Title");
            System.out.println("2. Change Showing Status");
            System.out.println("3. Change Synopsis");
            System.out.println("4. Change Director");
            System.out.println("5. Add new cast");
            System.out.println("6. Remove cast");
            System.out.println("7. Change duration of movie");
            System.out.println("8. Change Age Rating");
            System.out.println("0. Save changes and exit\n");

            choice = ip.getInt();
            int subChoice;

            switch (choice) {
                case 1:
                    System.out.println("Enter new movie title");
                    String tmpMovieTitle = sc.nextLine();
                    if (moviesDC.movieExists(tmpMovieTitle)) {
                        System.out.println("Movie already exists. Change to movie name not saved");
                        break;
                    } else {
                        newMovieTitle = tmpMovieTitle;
                    }
                    System.out.println();
                    break;

                case 2:
                    do {
                        System.out.println("Select new showing status");
                        System.out.println("1. Coming Soon");
                        System.out.println("2. Preview");
                        System.out.println("3. Now Showing");
                        System.out.println("4. End of Showing");
                        System.out.println("0. Go back");
                        subChoice = ip.getInt();

                        switch (subChoice) {
                            case 1:
                                newShowingStatus = "Coming Soon";
                                break;
                            case 2:
                                newShowingStatus = "Preview";
                                break;
                            case 3:
                                newShowingStatus = "Now Showing";
                                break;
                            case 4:
                                newShowingStatus = "End of Showing";
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Invalid choice");

                        }
                    } while (subChoice < 0 || subChoice > 4);
                    System.out.println();
                    break;
                
                case 3:
                    System.out.println("Enter new synopsis");
                    newSynopsis = ip.getString();
                    System.out.println();
                    break;
                
                case 4:
                    System.out.println("Enter new director");
                    newSynopsis = ip.getString();
                    System.out.println();
                    break;
                
                case 5:
                    System.out.println("Enter new cast member");
                    newCast.add(ip.getString());
                    System.out.println();
                    break;
                
                case 6:
                    i = 1; size = newCast.size();
                    for (String c : newCast) {
                        System.out.println(i + ". " + c);
                        i++;
                    }
                    do {
                        System.out.println("Choose which cast member to remove (0 to exit)");
                        subChoice = ip.getInt();
                        if (subChoice < 0 || subChoice > size)
                            System.out.println("Invalid Choice");
                        } while (subChoice < 0 || subChoice > size);
                
                    if (subChoice == 0) {
                        System.out.println("No changes made to cast. Returning to menu..");
                    } else {
                        newCast.remove(subChoice - 1);
                    }
                    System.out.println();
                    break;

                case 7:
                    System.out.println("Enter new movie duration");
                    newDuration = ip.getInt();
                    System.out.println();
                    break;
                
                case 8:
                    do {
                        System.out.println("1. G");
                        System.out.println("2. PG");
                        System.out.println("3. PG13");
                        System.out.println("4. NC16");
                        System.out.println("5. M18");
                        System.out.println("6. R21");
                        System.out.println("0. Go back");
                        
                        subChoice = ip.getInt();

                        switch (subChoice) {
                            case 1:
                                newAgeRating = AgeRating.G;
                                break;
                            case 2:
                                newAgeRating = AgeRating.PG;
                                break;
                            case 3:
                                newAgeRating = AgeRating.PG13;  
                                break;
                            case 4:
                                newAgeRating = AgeRating.NC16;
                                break;
                            case 5:
                                newAgeRating = AgeRating.M18;
                                break;
                            case 6:
                                newAgeRating = AgeRating.R21;
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Invalid choice");

                        }
                    } while (subChoice < 0 || subChoice > 6);
                    System.out.println();
                    break;
                
                case 0:
                    Movie newMovie = new Movie(newMovieTitle, newShowingStatus, newSynopsis, newAgeRating, newDirector, newCast.toArray(new String[0]), newDuration, oldMovie.getReviewArray(), oldMovie.getOverallRating(), oldMovie.getTotalSales());
                    moviesDC.updateMovie(oldMovie, newMovie);
                    System.out.println();
                    break;
                
                default:
                    System.out.println("Invalid choice\n");
            }
        } while (choice != 0);
    }

    /**
     * Delete movie
     */
    public void deleteMovie() {
        ArrayList<Movie> movies = moviesDC.getMovies();
        Movie movieToDelete = null;
        int i = 1, size = movies.size(), choice;
        for (Movie m : movies) {
            System.out.println(i + ". " + m.getMovieTitle());
            i++;
        }
        do {
            System.out.println("Enter movie choice (0 to exit): ");
            choice = ip.getInt();
            if (choice < 0 || choice >= size)
                System.out.println("Invalid Choice");
        } while (choice < 0 || choice >= size);

        if (choice == 0) {
            System.out.println("No changes made to movies. Returning to menu..");
        } else {
            movieToDelete = movies.get(choice - 1);
        }

        moviesDC.deleteMovie(movieToDelete.getMovieTitle());
        new ShowingsDatabaseController().deleteShowings(movieToDelete.getMovieTitle());
        new BookingController().deleteBookings(movieToDelete.getMovieTitle());
    }
}
