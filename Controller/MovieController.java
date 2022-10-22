package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Entities.Movie;

public abstract class MovieController {
    private MovieDatabaseController movieDC;
    ArrayList<Movie> allMovies;

    public MovieController() {
        movieDC = new MovieDatabaseController();
        allMovies = movieDC.getMovies();
    }

    public void displayMovies(int choice) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Movie> filteredMovies;

        switch (choice) {
            case 1:
                printMovies(allMovies);
                break;
            case 2:
                filteredMovies = filterMoviesByShowingStatus("Coming Soon");
                printMovies(filteredMovies);
                break;
            case 3:
                filteredMovies = filterMoviesByShowingStatus("Preview");
                printMovies(filteredMovies);
                break;
            case 4:
                filteredMovies = filterMoviesByShowingStatus("Now Showing");
                printMovies(filteredMovies);
                break;
            case 5:
                System.out.println("Enter movie name: ");
                String movieTitle = sc.nextLine();
                filteredMovies = filterMoviesByName(movieTitle);
                printMovies(filteredMovies);
                break;
        }
    }

    public void findMovies() {};

    public void viewTopMovies() {};

    private void printMovies(ArrayList<Movie> movies) {
        Scanner sc = new Scanner(System.in);
        int i = 1;

        for (Movie movie : allMovies) {
            System.out.println(i + ". " + movie.getMovieTitle() + " (" + movie.getShowingStatus() + ")");
            System.out.println("");
            i++;
        }

        System.out.println("Choose a movie (Enter 0 to exit)");
        int movieIdx = sc.nextInt();
        if (movieIdx == 0)
            return;
        else if (movieIdx < 0 || movieIdx >= allMovies.size())
            return;
        Movie movie = allMovies.get(movieIdx-1);
        movie.print();
        return;
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
