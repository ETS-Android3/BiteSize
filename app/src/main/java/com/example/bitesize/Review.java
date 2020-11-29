package com.example.bitesize;

public class Review {
    private String name;
    private String review;
    private float rating;

    public Review() {}

    public Review(String name, String review, float rating) {
        this.name = name;

        this.review = review;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
