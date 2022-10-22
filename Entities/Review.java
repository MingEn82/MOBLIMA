package Entities;

public class Review {
    private float rating;
    private String phoneNumber;
    private String reviewText;

    public Review(float rating, String phoneNumber, String reviewText) {
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.reviewText = reviewText;
    }

    public Review(float rating, String phoneNumber) {
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
}
