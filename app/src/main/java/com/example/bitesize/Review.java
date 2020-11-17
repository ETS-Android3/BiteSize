package com.example.bitesize;

public class Review {
    private User user;
    private Business business;
    private String review;
    private int rating;

    public Review() {}

    public Review(User user, Business business, String review, int rating) {
        this.user = user;
        this.business = business;
        this.review = review;
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
