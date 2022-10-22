package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entities.Movie;
import Entities.Review;

public class MovieDatabaseController implements DatabaseController {
    private String filePath = "Database/MoviesDatabase.txt";
    private File file;
    private ArrayList<Movie> movies;

    public MovieDatabaseController() {
        this.file = new File(filePath);
        this.movies = new ArrayList<Movie>();
        this.readFile();
    }

    public MovieDatabaseController(String filePath) {
        this.file = new File(filePath);
        this.movies = new ArrayList<Movie>();
        this.readFile();
    }
    
    public void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String movieLine = br.readLine();
            String movieTitle, showingStatus, synopsis, director;
            String[] cast;
            int duration, size;
            float overallRating;
            ArrayList<Review> reviews;
            String[] movieData;
            String[] reviewData;

            while (movieLine != null) {
                movieData = movieLine.split(", ");
                size = movieData.length;
                overallRating = -1;
                reviews = new ArrayList<Review>();

                movieTitle = movieData[0];
                showingStatus = movieData[1];
                duration = Integer.parseInt(movieData[2]);
                synopsis = movieData[3];
                director = movieData[4];
                cast = movieData[5].split(" & ");

                if (size > 6) {
                    overallRating = Float.parseFloat(movieData[6]);
                    for (int i = 7; i < size; i++) {
                        reviewData = movieData[i].split(" & ");
                        // Checks whether there is a review text body
                        if (reviewData.length == 3) {
                            reviews.add(new Review(Float.parseFloat(reviewData[0]), reviewData[1], reviewData[2]));
                        } else {
                            reviews.add(new Review(Float.parseFloat(reviewData[0]), reviewData[1]));
                        }
                    }
                }
                
                movies.add(new Movie(movieTitle, showingStatus, synopsis, director, cast, duration, movieTitle, reviews, overallRating));

                movieLine = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void addNewMovie(String movieTitle, String showingStatus, String synopsis, String director, String[] cast, int duration, String movieType, ArrayList<Review> reviews, float overallRating) {
        Movie newMovie = new Movie(movieTitle, showingStatus, synopsis, director, cast, duration, movieType, reviews, overallRating);
        movies.add(newMovie);
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(file, true));
            PrintWriter pw = new PrintWriter(bf);
            pw.append("\n");
            pw.append(newMovie.toString());

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeShowingStatus(String movieTitle, String showingStatus) {
        for (Movie movie : movies) {
            if (movie.getMovieTitle().equals(movieTitle)) {
                movie.setShowingStatus(showingStatus);
                this.updateDatabase();
                return;
            }
        }

        System.out.println("Movie was not found!");
        return;
    }

    public void addReview(String movieTitle, Review review) {
        for (Movie movie : movies) {
            if (movie.getMovieTitle().equals(movieTitle)) {
                movie.addReview(review);
                this.updateDatabase();
                return;
            }
        }

        System.out.println("Movie was not found!");
        return;
    }

    private void updateDatabase() {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(file, false));
            PrintWriter pw = new PrintWriter(bf);
            for (Movie movie : movies) {
                pw.println(movie.toString());
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
