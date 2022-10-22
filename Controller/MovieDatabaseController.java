package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            float overallRating = -1;
            ArrayList<Review> reviews;
            String[] movieData;
            String[] reviewData;

            while (movieLine != null) {
                movieData = movieLine.split(", ");
                size = movieData.length;
                reviews = new ArrayList<Review>();

                movieTitle = movieData[0];
                showingStatus = movieData[1];
                duration = Integer.parseInt(movieData[2]);
                synopsis = movieData[3];
                director = movieData[4];
                cast = movieData[5].split(" & ");

                if (size >= 5) {
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

                br.readLine();
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewMovie(String movieTitle, String showingStatus, String synopsis, String director, String[] cast, int duration, String movieType, ArrayList<Review> reviews, float overallRating) {
        Movie newMovie = new Movie(movieTitle, showingStatus, synopsis, director, cast, duration, movieType, reviews, overallRating);
        movies.add(newMovie);
    }

    private void updateDatabase() {
        
    }
}
