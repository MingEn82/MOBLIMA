package Entities;

public class Review {
    private float rating;
    private int phoneNumber;
    private String reviewText;

    public Review(float rating, int phoneNumber, String reviewText) {
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.reviewText = reviewText;
    }

    public Review(float rating, int phoneNumber) {
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.reviewText = "";
    }

    public String toString() {
        String s = rating + " & " + phoneNumber;
        if (!reviewText.equals(""))
            s += " & " + reviewText;
        return s;
    }

    public void printReview() {
        System.out.printf("Rating: %.2f\n", rating);
        if (!reviewText.equals("")) {
            System.out.println("Review: " + reviewText + "\n");
        } else {
            System.out.println("Review: N/A");
        }
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReviewText() {
        return this.reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
    
}
