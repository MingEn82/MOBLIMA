package Entities;

/**
 * This is the class for defining a Review Object.
 */
public class Review {
    private float rating;
    private int phoneNumber;
    private String reviewText;

    /**
     * This is the constructor for Review.
     * @param rating
     * @param phoneNumber
     * @param reviewText
     */
    public Review(float rating, int phoneNumber, String reviewText) {
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.reviewText = reviewText;
    }

    /**
     * This is a constructor for Review.
     * @param rating
     * @param phoneNumber
     */
    public Review(float rating, int phoneNumber) {
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.reviewText = "";
    }


    /**
     * This method converts the review object to string.
     * @return
     */
    public String toString() {
        String s = rating + " & " + phoneNumber;
        if (!reviewText.equals(""))
            s += " & " + reviewText;
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
     * @return
     */
    public float getRating() {
        return this.rating;
    }

    /**
     * This is a setter function for setting rating.
     * @param rating
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * This is a getter function for getting phone number.
     * @return
     */
    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * This is a setter function for setting phone number.
     * @param phoneNumber
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This is a getter function for getting review text.
     * @return
     */
    public String getReviewText() {
        return this.reviewText;
    }

    /**
     * This is a setter function for setting review text.
     * @param reviewText
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    
}
