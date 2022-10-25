package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entities.AgeRating;
import Entities.Movie;

public class AdminMovieController extends MovieController{
    private ShowingsDatabaseController showingsDC;
    private MovieDatabaseController moviesDC;
    private BookingController bookingController;
    Scanner sc;

    
    public AdminMovieController() {
        showingsDC = new ShowingsDatabaseController();
        moviesDC = new MovieDatabaseController();
        bookingController = new BookingController();
        sc = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;

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
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    this.displayMovies(1);
                    break;
                case 2:
                    this.createMovie();
                    break;
                case 3:
                    this.updateMovie();
                    break;
                case 4:
                    this.deleteMovie();
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
        int choice;

        if (moviesDC.movieExists(movieTitle)) {
            System.out.println("Movie already exists!");
            return;
        }
        
        String showingStatus = null;
        do {
            System.out.println("Select showing status");
            System.out.println("1. Coming Soon");
            System.out.println("2. Preview");
            System.out.println("3. Now Showing");
            System.out.println("4. End of Showing");
            System.out.println("0. Go back");
            
            choice = sc.nextInt();

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
        
        System.out.println("Enter movie duration (in mins): ");
        int movieDuration = sc.nextInt();
        sc.nextLine();
        
        AgeRating ageRating = null;
        do {
            System.out.println("1. G");
            System.out.println("2. PG");
            System.out.println("3. PG13");
            System.out.println("4. NC16");
            System.out.println("5. M18");
            System.out.println("6. R21");
            System.out.println("0. Go back");
            
            choice = sc.nextInt();

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
        
        System.out.println("Enter movie synopsis: ");
        String synopsis = sc.nextLine();
        
        System.out.println("Enter director: ");
        String director = sc.nextLine();
        
        ArrayList<String> casts = new ArrayList<String>();
        String cast;
        do {
            System.out.println("Enter cast (0 to stop): ");
            cast = sc.nextLine();
            if (!cast.equals("0"))
                casts.add(cast);
        } while (!cast.equals("0"));
        moviesDC.addNewMovie(movieTitle, showingStatus, synopsis, ageRating, director, casts.toArray(new String[0]), movieDuration);
    }

    // Not sure if correct implementation
    public void updateMovie() {   
        ArrayList<Movie> movies = moviesDC.getMovies();
        Movie oldMovie = null;
        int i = 1, size = movies.size(), choice;
        for (Movie m : movies) {
            System.out.println(i + ". " + m.getMovieTitle());
            i++;
        }
        do {
            System.out.println("Enter movie choice (0 to exit): ");
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
            System.out.println("0. Save changes and exit");

            choice = sc.nextInt();
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
                case 2:
                    do {
                        System.out.println("1. Coming Soon");
                        System.out.println("2. Preview");
                        System.out.println("3. Now Showing");
                        System.out.println("4. End of Showing");
                        System.out.println("0. Go back");
                        
                        subChoice = sc.nextInt();

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
                    break;
                
                case 3:
                    System.out.println("Enter new synopsis");
                    newSynopsis = sc.nextLine();
                    break;
                
                case 4:
                    System.out.println("Enter new director");
                    newSynopsis = sc.nextLine();
                    break;
                
                case 5:
                    System.out.println("Enter new cast member");
                    newCast.add(sc.nextLine());
                    break;
                
                case 6:
                    i = 1; size = newCast.size();
                    for (String c : newCast) {
                        System.out.println(i + ". " + c);
                        i++;
                    }
                    do {
                        System.out.println("Choose which cast member to remove (0 to exit)");
                        subChoice = sc.nextInt();
                        if (subChoice < 0 || subChoice > size)
                            System.out.println("Invalid Choice");
                        } while (subChoice < 0 || subChoice > size);
                
                    if (subChoice == 0) {
                        System.out.println("No changes made to cast. Returning to menu..");
                    } else {
                        newCast.remove(subChoice - 1);
                    }

                case 7:
                    System.out.println("Enter new movie duration");
                    newDuration = sc.nextInt();
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
                        
                        subChoice = sc.nextInt();

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
                    break;
                
                case 0:
                    Movie newMovie = new Movie(newMovieTitle, newShowingStatus, newSynopsis, newAgeRating, newDirector, newCast.toArray(new String[0]), newDuration, oldMovie.getReviewArray(), oldMovie.getOverallRating(), oldMovie.getTotalSales());
                    moviesDC.updateMovie(oldMovie, newMovie);
                    break;
                
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

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
            choice = sc.nextInt();
            if (choice < 0 || choice >= size)
                System.out.println("Invalid Choice");
        } while (choice < 0 || choice >= size);

        if (choice == 0) {
            System.out.println("No changes made to movies. Returning to menu..");
        } else {
            movieToDelete = movies.get(choice - 1);
        }

        moviesDC.deleteMovie(movieToDelete.getMovieTitle());
        showingsDC.deleteShowings(movieToDelete.getMovieTitle());
        bookingController.deleteBookings(movieToDelete.getMovieTitle());
    }
}
