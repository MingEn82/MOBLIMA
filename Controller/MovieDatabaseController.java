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

public class MovieDatabaseController implements DatabaseController {
    private String filePath = "Database/MoviesDatabase.txt";
    private File file;
    private ArrayList<Movie> movies;
    private BookingsDatabaseController bookingsDC;

    public MovieDatabaseController() {
        this.file = new File(filePath);
        this.movies = new ArrayList<Movie>();
        bookingsDC = new BookingsDatabaseController();
        this.readFile();
    }

    public MovieDatabaseController(String filePath) {
        this.file = new File(filePath);
        this.movies = new ArrayList<Movie>();
        bookingsDC = new BookingsDatabaseController();
        this.readFile();
    }
    
    public void readFile() {
        this.movies = new ArrayList<Movie>();
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
                movieData = movieLine.split(", ");
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
                cast = movieData[6].split(" & ");
                totalSales = bookingsDC.getTotalSales(movieTitle);

                if (size > 7) {
                    overallRating = Float.parseFloat(movieData[7]);
                    for (int i = 8; i < size; i++) {
                        reviewData = movieData[i].split(" & ");
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

    public void updateMovie(String oldMovieTitle, Movie updatedMovie) {
        movies.removeIf(m -> m.getMovieTitle().equals(oldMovieTitle));
        movies.add(updatedMovie);
    }

    public boolean changeShowingStatus(String movieTitle, String showingStatus) {
        for (Movie movie : movies) {
            if (movie.getMovieTitle().equals(movieTitle)) {
                movie.setShowingStatus(showingStatus);
                this.updateDatabase();
                return true;
            }
        }

        System.out.println("Movie was not found");
        return false;
    }

    public void printReviews(String movieTitle) {
        System.out.println("|-------------------------------------------------------|");
        System.out.println("|                       Reviews                         |");
        System.out.println("|-------------------------------------------------------|");
        for (Movie movie : movies) {
            if (movie.getMovieTitle().equals(movieTitle)) {
                movie.printReviews();
                return;
            }
        }
        System.out.println("|-------------------------------------------------------|");
    }

    public void printReviews(String movieTitle, int phoneNumber) {
        for (Movie movie : movies) {
            if (movie.getMovieTitle().equals(movieTitle)) {
                movie.printReviews(phoneNumber);
                return;
            }
        }
    }

    public boolean hasReview(String movieTitle, int phoneNumber) {
        for (Movie movie : movies) {
            if (movie.getMovieTitle().equals(movieTitle)) {
                return movie.hasReview(phoneNumber);
            }
        }
        return false;
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

    public void updateMovie(Movie oldMovie, Movie newMovie) {
        movies.removeIf(m -> m.getMovieTitle().equals(oldMovie.getMovieTitle()));
        movies.add(newMovie);
        this.updateDatabase();
    }

    public int getMovieDuration(String movieTitle) {
        for (Movie movie : movies) {
            if (!movie.getMovieTitle().equals(movieTitle))
                continue;
            return movie.getDuration();
        }
        return -1;
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

    public boolean movieExists(String movieTitle) {
        for (Movie m : movies) {
            if (m.getMovieTitle().equals(movieTitle)) {
                return true;
            }
        }
        return false;
    }

}
