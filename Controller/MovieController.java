package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Entities.Movie;

public abstract class MovieController {
    private MovieDatabaseController movieDC;
    ArrayList<Movie> allMovies;

    public MovieController() {
        movieDC = new MovieDatabaseController();
        allMovies = movieDC.getMovies();
    }

    public abstract void displayMenu();

    /**
     * Displays movies using choice and returns the movie chosen
     * @param choice
     * @return chosen movie, null if no movie was chosen
     */
    public Movie displayMovies(int choice) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Movie> filteredMovies;
        movieDC.readFile();
        
        switch (choice) {
            case 1:
                System.out.println("");
                System.out.println("================== List All Movies =====================");
                return printMovies(movieDC.getMovies());
            case 2:
                System.out.println("");
                System.out.println("================== Movies Coming Soon ==================");
                filteredMovies = filterMoviesByShowingStatus("Coming Soon");
                return printMovies(filteredMovies);
            case 3:
                System.out.println("");
                System.out.println("================= Movies in Preview ====================");
                filteredMovies = filterMoviesByShowingStatus("Preview");
                return printMovies(filteredMovies);
            case 4:
                System.out.println("");
                System.out.println("=============== Movies (Now Showing) ===================");
                filteredMovies = filterMoviesByShowingStatus("Now Showing");
                return printMovies(filteredMovies);
            case 5:
                System.out.println("Enter movie name: ");
                String movieTitle = sc.nextLine();
                filteredMovies = filterMoviesByName(movieTitle);
                return printMovies(filteredMovies);
            default:
                return null;
        }
    }

    /**
     * finds movies and return chosen movie
     * @return chosen movie, null otherwise
     */
    public Movie findMovies() {
        Scanner sc = new Scanner(System.in);
        int choice;
        Movie movie = null;
        do {
            System.out.println("+-------------------------------------------------------+");
            System.out.println("|                      Find Movies                      |");
            System.out.println("+-------------------------------------------------------+");
            System.out.println("| 1. List all movies                                    |");
            System.out.println("| 2. Coming Soon                                        |");
            System.out.println("| 3. Preview                                            |");
            System.out.println("| 4. Now Showing                                        |");
            System.out.println("| 5. Search movie by title                              |");
            System.out.println("| 6. Back to Main Menu                                  |");
            System.out.println("+-------------------------------------------------------+");
            System.out.println("");

            System.out.print("Choice chosen is: ");
            
            choice = sc.nextInt();
            System.out.println("");

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    movie = displayMovies(choice);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (choice < 1 || choice > 6);
        return movie;
    };

    /**
     * View top movies by either total sales or overall rating
     */
    public void viewTopMovies() {
        // Create deep copy of allMovies
        ArrayList<Movie> sortedMovies = new ArrayList<Movie>();
        for (Movie m : filterCurrentMovies()) {
            sortedMovies.add(m.clone());
        }
        int end = sortedMovies.size() > 5 ? 5 : sortedMovies.size();
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("""
                +-------------------------------------------------------+
                |                 Viewing Top Movies                    |
                |-------------------------------------------------------|
                | 1. Sort by total sales                                |
                | 2. Sort by overall rating                             |
                |-------------------------------------------------------|
                |         Enter 0 to go back to Movie Goer Menu         |
                +-------------------------------------------------------+
                """);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Collections.sort(sortedMovies, Comparator.comparingInt(m -> ((Movie) m).getTotalSales()).reversed());
                    for (int i = 0; i < end; i++) {
                        sortedMovies.get(i).print();
                    }
                    break;
                case 2:
                    Collections.sort(sortedMovies, Comparator.comparingDouble(m -> ((Movie) m).getOverallRating()).reversed());
                    for (int i = 0; i < end; i++) {
                        sortedMovies.get(i).print();
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        } while (choice != 0);
    };

    /**
     * Prints all movies and returns chosen movie
     * @param movies
     * @return chosen movie object, null otherwise
     */
    private Movie printMovies(ArrayList<Movie> movies) {
        Scanner sc = new Scanner(System.in);
        int i = 1;
        Movie m = null;

        if (movies.size() == 0) {
            System.out.println("No movies found");
        } else {
            for (Movie movie : movies) {
                System.out.println(i + ". " + movie.getMovieTitle() + " (" + movie.getShowingStatus() + ")");
                i++;
            }
            System.out.println("========================================================");
            System.out.println("");
            System.out.println("Choose a movie (Enter 0 to exit)");
            System.out.print("Choice is: ");
            int movieIdx = sc.nextInt();
            if (movieIdx == 0)
                ;
            else if (movieIdx < 0 || movieIdx > allMovies.size())
                ;
            else {
                m = movies.get(movieIdx-1);
                m.print();
            }
        }
        
        return m;
    }

    /**
     * filters movies by showing status
     * @param showingStatus
     * @return ArrayList of Movies
     */
    public ArrayList<Movie> filterMoviesByShowingStatus(String showingStatus) {
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();

        for (Movie movie : allMovies) {
            if (movie.getShowingStatus().equals(showingStatus)) {
                filteredMovies.add(movie);
            }
        }
        
        return filteredMovies;
    }

    /**
     * Filters movies with showing status of "Now Showing" and "Preview"
     * @return ArrayList of Movies
     */
    public ArrayList<Movie> filterCurrentMovies() {
        allMovies = new MovieDatabaseController().getMovies();
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();

        for (Movie movie : allMovies) {
            if (movie.getShowingStatus().equals("Now Showing") || movie.getShowingStatus().equals("Preview")) {
                filteredMovies.add(movie);
            }
        }
        
        return filteredMovies;
    }

    /**
     * Filters movies with movie name
     * @param movieName
     * @return ArrayList of Movies
     */
    public ArrayList<Movie> filterMoviesByName(String movieName) {
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();

        for (Movie movie : allMovies) {
            if (movie.getMovieTitle().contains(movieName)) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }

    /**
     * Adds a sale to movie
     * @param movieTitle
     */
    public void addOneToTotalSales(String movieTitle) {
        movieDC.addOneToTotalSales(movieTitle);
    }
}
