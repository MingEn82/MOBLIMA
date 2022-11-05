package Entities;

/**
 * This is the class for defining a Review Object.
 * @author Koh Ming En
 * @version 1.0
 * @since 2022-11-02
 */
public class Review {

    /**
     * Rating of Review
     */
    private float rating;

    /**
     * Phone Number of Review
     */
    private int phoneNumber;

    /**
     * Review Text
     */
    private String reviewText;

    /**
     * This is the constructor for Review.
     * @param rating        rating of movie from 1-5
     * @param phoneNumber   phone number of reviewer
     * @param reviewText    review body
     */
    public Review(float rating, int phoneNumber, String reviewText) {
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.reviewText = reviewText;
    }

    /**
     * This is a constructor for Review.
     * @param rating        rating of movie from 1-5
     * @param phoneNumber   phone number of reviewer
     */
    public Review(float rating, int phoneNumber) {
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.reviewText = "";
    }


    /**
     * This method converts the review object to string.
     * @param delimiter delimiter for concatenating review object
     * @return String Of Review Object
     */
    public String toString(String delimiter) {
        String s = rating + delimiter + phoneNumber;
        if (!reviewText.equals(""))
            s += delimiter + reviewText;
        return s;
    }

    /**
     * This function prints the review onto console.
     */
    public void printReview() {
        System.out.printf(" Rating: %.2f\n", rating);
        if (!reviewText.equals("")) {
            System.out.println(" Review: " + reviewText);
        } else {
            System.out.println(" Review: N/A");
        }
        System.out.println(" ");
    }

    /**
     * This is getter function for getting rating.
     * @return Rating
     */
    public float getRating() {
        return this.rating;
    }

    /**
     * This is a setter function for setting rating.
     * @param rating    new rating of movie
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * This is a getter function for getting phone number.
     * @return Phone Number
     */
    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * This is a setter function for setting phone number.
     * @param phoneNumber   new phone number of reviewer
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This is a getter function for getting review text.
     * @return Review Text
     */
    public String getReviewText() {
        return this.reviewText;
    }

    /**
     * This is a setter function for setting review text.
     * @param reviewText    new review body
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    
}
