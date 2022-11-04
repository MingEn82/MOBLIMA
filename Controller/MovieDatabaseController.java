package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entities.AgeRating;
import Entities.Movie;
import Entities.Review;

/**
 * MovieDatabaseController is a controller that is used to write and read from the movie text file.
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-03
 */
public class MovieDatabaseController implements DatabaseController {
    /**
     * Default path for Movie Database
     */
    private String filePath = "Database/MoviesDatabase.txt";

    /**
     * Delimiter for database text file
     */
    private static final String delimiter = "<b>";

    /**
     * Delimiter for database text file for reviews and cast
     */
    private static final String attributeDelimiter = "<s>";

    /**
     * Create file instance
     */
    private File file;

    /**
     * Create ArrayList of movies
     */
    private ArrayList<Movie> movies;

    /**
     * Constructor for MovieDatabaseController instance
     */
    public MovieDatabaseController() {
        this.file = new File(filePath);
        this.movies = new ArrayList<Movie>();
        this.readFile();
    }

    /**
     * Overloaded constructor for MovieDatabaseController in case database files are found elsewhere
     * @param filePath
     */
    public MovieDatabaseController(String filePath) {
        this.file = new File(filePath);
        this.movies = new ArrayList<Movie>();
        this.readFile();
    }
    
    /**
     * Reads database and creates ArrayList of Movies
     */
    public void readFile() {
        this.movies = new ArrayList<Movie>();
        BookingsDatabaseController bookingsDC = new BookingsDatabaseController();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String movieLine = br.readLine();
            String movieTitle, showingStatus, synopsis, director;
            AgeRating ageRating;
            String[] cast;
            int duration, totalSales, size;
            float overallRating;
            ArrayList<Review> reviews;
            String[] movieData, reviewData;

            while (movieLine != null) {
                movieData = movieLine.split(delimiter);
                size = movieData.length;
                if (size < 6) {
                    continue;
                }

                overallRating = -1;
                reviews = new ArrayList<Review>();

                movieTitle = movieData[0];
                showingStatus = movieData[1];
                duration = Integer.parseInt(movieData[2]);
                ageRating = AgeRating.valueOf(movieData[3]);
                synopsis = movieData[4];
                director = movieData[5];
                cast = movieData[6].split(attributeDelimiter);
                totalSales = bookingsDC.getTotalSales(movieTitle);

                if (size > 7) {
                    overallRating = Float.parseFloat(movieData[7]);
                    for (int i = 8; i < size; i++) {
                        reviewData = movieData[i].split(attributeDelimiter);
                        // Checks whether there is a review text body
                        if (reviewData.length == 3) {
                            reviews.add(new Review(Float.parseFloat(reviewData[0]), Integer.parseInt(reviewData[1]), reviewData[2]));
                        } else {
                            reviews.add(new Review(Float.parseFloat(reviewData[0]), Integer.parseInt(reviewData[1])));
                        }
                    }
                }
                
                movies.add(new Movie(movieTitle, showingStatus, synopsis, ageRating, director, cast, duration, reviews, overallRating, totalSales));

                movieLine = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function gets the ArrayList of Movies
     * @return ArrayList of Movies
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void addNewMovie(String movieTitle, String showingStatus, String synopsis, AgeRating ageRating, String director, String[] cast, int duration) {
        // Check for duplicate movie
        if (movieExists(movieTitle)) {
            System.out.println("Movie already exists!");
            return;
        }

        Movie newMovie = new Movie(movieTitle, showingStatus, synopsis, ageRating, director, cast, duration, null, -1, 0);
        movies.add(newMovie);
        this.updateDatabase();
    }

    /**
     * Deletes movie by setting the showing status to End of Showing
     * @param movieTitle
     */
    public void deleteMovie(String movieTitle) {
        for (Movie m : movies) {
            if (m.getMovieTitle().equals(movieTitle)) {
                m.setShowingStatus("End of Showing");
                System.out.println(movieTitle + " removed from database");
                this.updateDatabase();
                return;
            }
        }
        System.out.println("Movie not found");
    }

    /**
     * Deletes movie from database
     * @param movieTitle
     */
    public void removeMovieFromDatabase(String movieTitle) {
        movies.removeIf(m -> m.getMovieTitle().equals(movieTitle));
        this.updateDatabase();
    }

    /**
     * Prints all reviews of the movie
     * @param movieTitle
     */
    public void printReviews(String movieTitle) {
        if (!hasReview(movieTitle)) {
            System.out.println("No reviews found for " + movieTitle + "\n");
            return;
        }

        System.out.println("|-------------------------------------------------------|");
        System.out.println("|                       Reviews                         |");
        System.out.println("|-------------------------------------------------------|");
        for (Movie movie : movies) {
            if (movie.getMovieTitle().equals(movieTitle)) {
                movie.printReviews();
                System.out.println("|-------------------------------------------------------|\n");
                return;
            }
        }
    }

    /**
     * Prints review of movie, filtered by phone number
     * @param movieTitle
     * @param phoneNumber
     */
    public void printReviews(String movieTitle, int phoneNumber) {
        if (!hasReview(movieTitle)) {
            System.out.println("No reviews found for " + movieTitle + "\n");
            return;
        }

        for (Movie movie : movies) {
            if (movie.getMovieTitle().equals(movieTitle)) {
                movie.printReviews(phoneNumber);
                return;
            }
        }
    }

    /**
     * Checks whether person with phone number has left a review on movie
     * @param movieTitle
     * @param phoneNumber
     * @return true if person has left a review for this movie, false otherwise
     */
    public boolean hasReview(String movieTitle, int phoneNumber) {
        for (Movie movie : movies) {
            if (movie.getMovieTitle().equals(movieTitle)) {
                return movie.hasReview(phoneNumber);
            }
        }
        return false;
    }

    /**
     * Checks whether movie has at least one review
     * @return true if movie has at least one review, false otherwise
     */
    public boolean hasReview(String movieTitle) {
        for (Movie movie : movies) {
            if (movie.getMovieTitle().equals(movieTitle)) {
                return movie.hasReview();
            }
        }
        return false;
    }

    /**
     * Adds a review to movie
     * @param movieTitle
     * @param review
     */
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

    /**
     * Updates review of movie
     * @param movieTitle
     * @param review
     */
    public void updateReview(String movieTitle, Review review) {
        for (Movie movie : movies) {
            if (movie.getMovieTitle().equals(movieTitle)) {
                movie.updateReview(review);
                this.updateDatabase();
                return;
            }
        }

        System.out.println("Movie was not found!");
        return;
    }

    /**
     * Updates movie details
     * @param oldMovie
     * @param newMovie
     */
    public void updateMovie(Movie oldMovie, Movie newMovie) {
        movies.removeIf(m -> m.getMovieTitle().equals(oldMovie.getMovieTitle()));
        movies.add(newMovie);
        this.updateDatabase();
    }

    /**
     * Getter function for movie duration
     * @param movieTitle
     * @return movie duration in minutes
     */
    public int getMovieDuration(String movieTitle) {
        for (Movie movie : movies) {
            if (!movie.getMovieTitle().equals(movieTitle))
                continue;
            return movie.getDuration();
        }
        return -1;
    }

    /**
     * Getter function for movie
     * @param movieTitle
     * @return movie duration in minutes
     */
    public Movie getMovie(String movieTitle) {
        for (Movie movie : movies) {
            if (!movie.getMovieTitle().equals(movieTitle))
                continue;
            return movie;
        }
        return null;
    }

    /**
     * Updates database
     */
    private void updateDatabase() {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(file, false));
            PrintWriter pw = new PrintWriter(bf);
            for (Movie movie : movies) {
                pw.println(movie.toString(delimiter, attributeDelimiter));
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether movie exists
     * @param movieTitle
     * @return true if movie exists, false otherwise
     */
    public boolean movieExists(String movieTitle) {
        for (Movie m : movies) {
            if (m.getMovieTitle().equals(movieTitle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds one to total sales
     * @param movieTitle
     */
    public void addOneToTotalSales(String movieTitle) {
        for (Movie m : movies) {
            if (m.getMovieTitle().equals(movieTitle)) {
                m.addOneSale();
            }
        }
    }
}
