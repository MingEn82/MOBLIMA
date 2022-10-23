package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Entities.Movie;

public abstract class MovieController {
    private MovieDatabaseController movieDC;
    ArrayList<Movie> allMovies;

    public MovieController() {
        movieDC = new MovieDatabaseController();
        allMovies = movieDC.getMovies();
    }

    public void displayMenu() {}

    public Movie displayMovies(int choice) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Movie> filteredMovies;

        switch (choice) {
            case 1:
                return printMovies(allMovies);
            case 2:
                filteredMovies = filterMoviesByShowingStatus("Coming Soon");
                return printMovies(filteredMovies);
            case 3:
                filteredMovies = filterMoviesByShowingStatus("Preview");
                return printMovies(filteredMovies);
            case 4:
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

    public Movie findMovies() {
        Scanner sc = new Scanner(System.in);
        int choice;
        Movie movie = null;
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

    public void viewTopMovies() {
        // Create deep copy of allMovies
        ArrayList<Movie> sortedMovies = new ArrayList<Movie>();
        for (Movie m : filterCurrentMovies()) {
            sortedMovies.add(m.clone());
        }
        int end = sortedMovies.size() > 5 ? 5 : sortedMovies.size();
        Collections.sort(sortedMovies);
        for (int i = 0; i < end; i++) {
            sortedMovies.get(i).print();
        }
    };

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
    
            System.out.println("Choose a movie (Enter 0 to exit)");
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

    public ArrayList<Movie> filterMoviesByShowingStatus(String showingStatus) {
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();

        for (Movie movie : allMovies) {
            if (movie.getShowingStatus().equals(showingStatus)) {
                filteredMovies.add(movie);
            }
        }
        
        return filteredMovies;
    }

    public ArrayList<Movie> filterCurrentMovies() {
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();

        for (Movie movie : allMovies) {
            if (movie.getShowingStatus().equals("Now Showing") || movie.getShowingStatus().equals("Preview")) {
                filteredMovies.add(movie);
            }
        }
        
        return filteredMovies;
    }

    public ArrayList<Movie> filterMoviesByName(String movieName) {
        ArrayList<Movie> filteredMovies = new ArrayList<Movie>();

        for (Movie movie : allMovies) {
            if (movie.getMovieTitle().contains(movieName)) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }
}
