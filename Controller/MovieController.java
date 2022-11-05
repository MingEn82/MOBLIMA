package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import Entities.Movie;

/**
 * This is a controller class that is deisgn to interact with Movie Database
 * Controller, Movie Object Entity and System Setting Controller
 * 
 * @author Teoh Xi Sheng
 * @version 1.0
 * @since 2022-11-02
 */
public abstract class MovieController {
    /**
     * Initialise Movie Database Controller object
     */
    private MovieDatabaseController movieDC;
    /**
     * Initialise System Setting Controller object
     */
    private SystemSettingController sSCtrl;

    /**
     * Initialise an Array of Movies object
     */
    ArrayList<Movie> allMovies;

    /**
     * Constructor
     */
    public MovieController() {
        movieDC = new MovieDatabaseController();
        allMovies = movieDC.getMovies();
    }

    /**
     * This is an abstract class for displaying menu
     */
    public abstract void displayMenu();

    /**
     * Displays movies using choice and returns the movie chosen
     * 
     * @param choice is the user input
     * @return chosen movie, null if no movie was chosen
     */
    public Movie displayMovies(int choice) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Movie> filteredMovies;
        movieDC.readFile();
        allMovies = movieDC.getMovies();

        switch (choice) {
            case 1:
                System.out.println("");
                System.out.println("================== List All Movies =====================");
                return printMovies(getMovies());
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
            case 6:
                System.out.println("");
                System.out.println("================== List All Movies =====================");
                return printMovies(allMovies);
            default:
                return null;
        }
    }

    /**
     * finds movies and return chosen movie
     * 
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
            System.out.println("+-------------------------------------------------------+");
            System.out.println("|            Type 0 to return to main menu              |");
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
                case 0:
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        } while (choice < 0 || choice > 5);
        return movie;
    };

    /**
     * View top movies by either total sales or overall rating for staff
     */
    public void viewTopMoviesForStaff() {
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
            System.out.print("Choice chosen is: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Collections.sort(sortedMovies,
                            Comparator.comparingInt(m -> ((Movie) m).getTotalSales()).reversed());
                    for (int i = 0; i < end; i++) {
                        sortedMovies.get(i).print();
                    }
                    break;
                case 2:
                    Collections.sort(sortedMovies,
                            Comparator.comparingDouble(m -> ((Movie) m).getOverallRating()).reversed());
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
     * View top movies by either total sales or overall rating for movie goer
     */
    public void viewTopMoviesForMovieGoer() {
        // staff will display all options, movie goer will need to check
        // Get display options
        sSCtrl = new SystemSettingController();
        int showBySales = sSCtrl.getSystemSetting().getdisplayTop5bySales();
        int showByRating = sSCtrl.getSystemSetting().getdisplayTop5byRating();
        int showBySales2 = sSCtrl.getSystemSetting().getdisplayTop5bySales();
        ArrayList<Integer> showList = new ArrayList<Integer>();
        // if (showBySales == 1) {
        // showList.add(showBySales);
        // }
        // if (showByRating == 1) {
        // showList.add(showByRating);
        // }
        ArrayList<Integer> showList2 = new ArrayList<Integer>();
        showList2.add(showBySales);
        showList2.add(showByRating);
        // Create deep copy of allMovies
        ArrayList<Movie> sortedMovies = new ArrayList<Movie>();
        for (Movie m : filterCurrentMovies()) {
            sortedMovies.add(m.clone());
        }
        int end = sortedMovies.size() > 5 ? 5 : sortedMovies.size();
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            int empty = 0;
            showBySales = sSCtrl.getSystemSetting().getdisplayTop5bySales();
            showByRating = sSCtrl.getSystemSetting().getdisplayTop5byRating();
            if (showBySales == 1) {
                showList.add(showBySales);
            }
            if (showByRating == 1) {
                showList.add(showByRating);
            }
            System.out.println("""
                    +-------------------------------------------------------+
                    |                 Viewing Top Movies                    |
                    |-------------------------------------------------------|""");
            for (int i = 1; i <= showList.size(); i++) {
                // if setting is to display
                if (showList.get(i - 1) == 1) {

                    if (showBySales == 1) {
                        empty++;
                        System.out.print("| " + i + ". ");
                        System.out.println("Sort by total sales                                |");
                        showBySales--;
                    } else if (showByRating == 1) {
                        empty++;
                        System.out.print("| " + i + ". ");
                        System.out.println("Sort by overall rating                             |");
                        showByRating--;
                    }
                }
            }
            if (empty == 0) {
                System.out.println("| No option available                                  |");
            }
            System.out.println("""
                    |-------------------------------------------------------|
                    |         Enter 0 to go back to Movie Goer Menu         |
                    +-------------------------------------------------------+
                    """);
            System.out.print("Choice chosen is: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    if (empty != 0) {
                        if (showList2.get(0) == 1) {
                            if (showBySales2 == 1) {
                                Collections.sort(sortedMovies,
                                        Comparator.comparingInt(m -> ((Movie) m).getTotalSales()).reversed());
                                for (int i = 0; i < end; i++) {
                                    sortedMovies.get(i).print();
                                }
                            } else {
                                Collections.sort(sortedMovies,
                                        Comparator.comparingDouble(m -> ((Movie) m).getOverallRating()).reversed());
                                for (int i = 0; i < end; i++) {
                                    sortedMovies.get(i).print();
                                }
                            }
                            break;
                        } else if (showList2.get(1) == 1) {
                            if (showBySales2 == 1) {
                                Collections.sort(sortedMovies,
                                        Comparator.comparingInt(m -> ((Movie) m).getTotalSales()).reversed());
                                for (int i = 0; i < end; i++) {
                                    sortedMovies.get(i).print();
                                }
                            } else {
                                Collections.sort(sortedMovies,
                                        Comparator.comparingDouble(m -> ((Movie) m).getOverallRating()).reversed());
                                for (int i = 0; i < end; i++) {
                                    sortedMovies.get(i).print();
                                }
                            }
                            break;
                        }

                    }
                    System.out.println("Invalid Input! Please try again.");
                    break;

                case 2:
                    if (empty > 1) {
                        if (showList2.get(1) == 1) {
                            Collections.sort(sortedMovies,
                                    Comparator.comparingDouble(m -> ((Movie) m).getOverallRating()).reversed());
                            for (int i = 0; i < end; i++) {
                                sortedMovies.get(i).print();
                            }
                            break;
                        }
                    }
                    System.out.println("Invalid Input! Please try again.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid Input! Please try again.");
            }
        } while (choice != 0);
    };

    /**
     * Prints all movies and returns chosen movie
     * 
     * @param movies
     * @return chosen movie object, null otherwise
     */
    private Movie printMovies(ArrayList<Movie> movies) {
        Scanner sc = new Scanner(System.in);
        int i = 1;
        Movie m = null;
        List<String> definedOrder = Arrays.asList("Coming Soon", "Preview", "Now Showing");
        Collections.sort(movies, Comparator.comparing(mov -> definedOrder.indexOf(((Movie) mov).getShowingStatus()))
        .thenComparing(mov -> ((Movie) mov).getMovieTitle()));

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
                m = movies.get(movieIdx - 1);
                m.print();
            }
        }

        return m;
    }

    /**
     * filters movies by showing status
     * 
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
     * 
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
     * Filters movies with showing status of "Coming Soon", "Now Showing" and "Preview"
     * 
     * @return ArrayList of Movies
     */
    public ArrayList<Movie> getMovies() {
        allMovies = new MovieDatabaseController().getMovies();
        allMovies.removeIf(m -> m.getShowingStatus().equals("End of Showing"));
        return allMovies;
    }

    /**
     * Filters movies with movie name
     * 
     * @param movieName
     * @return ArrayList of Movies
     */
    public ArrayList<Movie> filterMoviesByName(String movieName) {
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();

        for (Movie movie : allMovies) {
            if (movie.getMovieTitle().contains(movieName) && !movie.getShowingStatus().equals("End of Showing")) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }

    /**
     * Adds a sale to movie
     * 
     * @param movieTitle
     */
    public void addOneToTotalSales(String movieTitle) {
        movieDC.addOneToTotalSales(movieTitle);
    }
}
