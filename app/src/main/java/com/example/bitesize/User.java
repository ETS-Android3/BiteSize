package com.example.bitesize;

public class User {
    private String name;
    private String dateJoined;
    private int reviewCount;

    public User() {}

    public User(String name, String dateJoined, int reviewCount) {
        this.name = name;
        this.dateJoined = dateJoined;
        this.reviewCount = reviewCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
}
