package Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the class used to define a Movie Object and it's methods.
 */
public class Movie {
    private String movieTitle;
    private String showingStatus;
    private String synopsis;
    private AgeRating ageRating;
    private String director;
    private String[] cast;
    private int duration;
    private ArrayList<Review> reviews;
    private float overallRating;
    private int totalSales;

    public Movie(String movieTitle, String showingStatus, String synopsis, AgeRating ageRating, String director, String[] cast, int duration, ArrayList<Review> reviews, float overallRating, int totalSales) {
        this.movieTitle = movieTitle;
        this.showingStatus = showingStatus;
        this.synopsis = synopsis;
        this.ageRating = ageRating;
        this.director = director;
        this.cast = cast;
        this.duration = duration;
        this.reviews = reviews;
        this.overallRating = overallRating;
        this.totalSales = totalSales;
    }

    /**
     * This is a getter function for retrieving Movie Title.
     * @return
     */
    public String getMovieTitle() {
        return this.movieTitle;
    }

    /**
     * This is a setter function for setting Movie Title.
     * @param movieTitle
     */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    /**
     * This is a getter function for retrieving Showing Status.
     * @return
     */
    public String getShowingStatus() {
        return this.showingStatus;
    }

    /**
     * This is a setter function for setting Showing Status.
     * @param showingStatus
     */
    public void setShowingStatus(String showingStatus) {
        this.showingStatus = showingStatus;
    }

    /**
     * This is a getter function for retrieving Sypnosis.
     * @return
     */
    public String getSynopsis() {
        return this.synopsis;
    }

    /**
     * This is a setter function for setting Sypnosis.
     * @param synopsis
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * This is a getter function for retrieving Age Rating.
     * @return
     */
    public AgeRating getAgeRating() {
        return this.ageRating;
    }

    /**
     * This is a setter function for setting Age Rating.
     * @param ageRating
     */
    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    /**
     * This is a getter function for retrieving Director of the movie.
     * @return
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * This is a setter function for setting director of movie.
     * @param director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * This is a getter function for retrieving all casts (string) of the movie.
     * @return
     */
    public String getCast() {
        return String.join(" & ", cast);
    }

    /**
     * This is a getter function for retrieving all the casts (object) of the movie.
     * @return
     */
    public List<String> getAllCast() {
        return Arrays.asList(cast);
    }

    /**
     * This is a setter function for setting the casts.
     * @param cast
     */
    public void setCast(List<String> cast) {
        this.cast = cast.toArray(new String[0]);
    }

    /**
     * This is a getter function for retrieving the duration of the movie.
     * @return
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * This is a setter function for setting duration.
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * This is a getter function for retrieving Reviews (string) of the movie.
     * @return
     */
    public String getReviews() {
        String s = ", ";
        for (Review r : reviews) {
            s += r.toString() + ", ";
        }
        return s.substring(0, s.length() - 2);
    }

    /**
     * This is a getter function for retrieving Reviews (object) of the movie.
     * @return
     */
    public ArrayList<Review> getReviewArray() {
        return this.reviews;
    }

    /**
     * This function prints all the reviews in console.
     */
    public void printReviews() {
        for (Review r : reviews) {
            r.printReview();
        }
    }

    /**
     * This function prints reviews of a individual based on phone number.
     * @param phoneNumber
     */
    public void printReviews(int phoneNumber) {
        for (Review r : reviews) {
            if (r.getPhoneNumber() == phoneNumber) {
                r.printReview();
            }
        }
    }

    /**
     * This function checks whether a user has reviews based on a phone number.
     * @param phoneNumber
     * @return
     */
    public boolean hasReview(int phoneNumber) {
        for (Review r : reviews) {
            if (r.getPhoneNumber() == phoneNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function checks whether this movie has reviews
     * @param phoneNumber
     * @return
     */
    public boolean hasReview() {
        return reviews.size() > 0;
    }

    /**
     * This is a setter function for setting Reviews.
     * @param reviews
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
    
    /**
     * This is a function for adding new reviews.
     * @param review
     */
    public void addReview(Review review) {
        overallRating = (overallRating * reviews.size() + review.getRating()) / (reviews.size() + 1);
        reviews.add(review);
    }

    /**
     * This is a function for updating a review.
     * @param review
     */
    public void updateReview(Review review) {
        reviews.removeIf(r -> r.getPhoneNumber() == review.getPhoneNumber());
        reviews.add(review);
        overallRating = 0;
        for (Review r : reviews) { overallRating += r.getRating(); }
        overallRating /= reviews.size();
    }

    /**
     * This is a getter function for retrieving the overall rating.
     * @return
     */
    public float getOverallRating() {
        return this.overallRating;
    }

    /**
     * This is a setter function for setting Overall Rating.
     * @param overallRating
     */
    public void setOverallRating(float overallRating) {
        this.overallRating = overallRating;
    }

    /**
     * This is a getter function for retrieving Total Sales.
     * @return
     */
    public int getTotalSales() {
        return this.totalSales;
    }

    /**
     * This is a function for adding a new sale to the movie.
     */
    public void addOneSale() {
        this.totalSales += 1; 
    }

    /**
     * This is a function for removing a sale from the movie.
     */
    public void removeOneSale() {
        this.totalSales -= 1;
    }

    /**
     * This function converts the movie object to string and returns a string.
     * @return
     */
    public String toString() {
        String s = movieTitle + ", " + showingStatus + ", " + duration + ", " + ageRating.toString() + ", " + synopsis + ", " + director + ", " + getCast();
        if (overallRating >= 0) {
            s += ", " + overallRating;
            s += getReviews();
        }
        return s;
    }

    /**
     * This function prints the entire movie object onto console.
     */
    public void print() {
        System.out.println(""); // print empty line
        System.out.println("---------------------------------------------------------");
        System.out.println("Movie Title: " + movieTitle + " (" + showingStatus + ")");
        System.out.println("---------------------------------------------------------");
        System.out.println("Duration: " + duration + " mins");     
        System.out.println("Age Rating: " + ageRating.toString());          
        System.out.println("Synopsis: " + synopsis);                    
        System.out.println("Director: " + director);
        System.out.println("Cast: " + String.join(", ", cast));
        System.out.println("Total Sales: " + totalSales);
        
        if (overallRating >= 1) {
            System.out.println("Overall Rating: " + overallRating);
        } else {
            System.out.println("Overall Rating: N/A");
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("");
    }

    /**
     * This function is used to return the clone of a movie object.
     */
    public Movie clone() {
        return new Movie(movieTitle, showingStatus, synopsis, ageRating, director, cast, duration, reviews, overallRating, totalSales);
    }

}
