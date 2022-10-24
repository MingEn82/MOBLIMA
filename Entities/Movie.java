package Entities;

import java.util.ArrayList;

public class Movie implements Comparable<Movie> {
    private String movieTitle;
    private String showingStatus;
    private String synopsis;
    private String director;
    private String[] cast;
    private int duration;
    private ArrayList<Review> reviews;
    private float overallRating;

    public Movie(String movieTitle, String showingStatus, String synopsis, String director, String[] cast, int duration, ArrayList<Review> reviews, float overallRating) {
        this.movieTitle = movieTitle;
        this.showingStatus = showingStatus;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.duration = duration;
        this.reviews = reviews;
        this.overallRating = overallRating;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getShowingStatus() {
        return this.showingStatus;
    }

    public void setShowingStatus(String showingStatus) {
        this.showingStatus = showingStatus;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return String.join(" & ", cast);
    }

    public void setCast(String[] cast) {
        this.cast = cast;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReviews() {
        String s = ", ";
        for (Review r : reviews) {
            s += r.toString() + ", ";
        }
        return s.substring(0, s.length() - 2);
    }

    public void printReviews() {
        for (Review r : reviews) {
            r.printReview();
        }
    }

    public void printReviews(int phoneNumber) {
        for (Review r : reviews) {
            if (r.getPhoneNumber() == phoneNumber) {
                r.printReview();
            }
        }
    }

    public boolean hasReview(int phoneNumber) {
        for (Review r : reviews) {
            if (r.getPhoneNumber() == phoneNumber) {
                return true;
            }
        }
        return false;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
    
    public void addReview(Review review) {
        overallRating = (overallRating * reviews.size() + review.getRating()) / (reviews.size() + 1);
        reviews.add(review);
    }

    public void updateReview(Review review) {
        reviews.removeIf(r -> r.getPhoneNumber() == review.getPhoneNumber());
        reviews.add(review);
        overallRating = 0;
        for (Review r : reviews) { overallRating += r.getRating(); }
        overallRating /= reviews.size();
    }

    public float getOverallRating() {
        return this.overallRating;
    }

    public void setOverallRating(float overallRating) {
        this.overallRating = overallRating;
    }

    public String toString() {
        String s = movieTitle + ", " + showingStatus + ", " + duration + ", " + synopsis + ", " + director + ", " + getCast();
        if (overallRating >= 0) {
            s += ", " + overallRating;
            s += getReviews();
        }
        return s;
    }

    public void print() {
        System.out.println("Movie Title: " + movieTitle + " (" + showingStatus + ")");
        System.out.println("Duration: " + duration + " mins");
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Director: " + director);
        System.out.println("Cast: " + String.join(", ", cast));
        if (overallRating >= 0) {
            System.out.println("Overall Rating: " + overallRating);
        } else {
            System.out.println("Overall Rating: N/A");
        }
        System.out.println("");
    }

    @Override
    public int compareTo(Movie compareMovie) {
        return overallRating - compareMovie.getOverallRating() > 0 ? -1 : 1;
    }

    public Movie clone() {
        return new Movie(movieTitle, showingStatus, synopsis, director, cast, duration, reviews, overallRating);
    }

}
