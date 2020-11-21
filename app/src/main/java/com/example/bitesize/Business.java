package com.example.bitesize;

import java.util.ArrayList;

public class Business {
    private String name;
    private String bio;
    private String city;
    private float rating;
    private int cost;
    private int reviewCount;
    private ArrayList<String> categories;
    private String instagramURL;
    private int imageResourceId;




    private static ArrayList<String> testing_categories;
    public static final Business[] businesses = {
            new Business("Yugo Sushi Bake", "Testing", "Burnaby", (float) 4.8, 25, 100, testing_categories = new ArrayList<String>(), "Instagram.com", R.drawable.recommended1)
    };

    public static Object[] getAllBusinesses() {
        return businesses;
    }



    public Business() {}

    public Business(String name, String bio, String city, float rating, int cost, int reviewCount, ArrayList<String> categories, String instagramURL, int imageResourceId) {
        this.name = name;
        this.bio = bio;
        this.city = city;
        this.rating = rating;
        this.cost = cost;
        this.reviewCount = reviewCount;
        this.categories = categories;
        this.instagramURL = instagramURL;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public String getInstagramURL() {
        return instagramURL;
    }

    public void setInstagramURL(String instagramURL) {
        this.instagramURL = instagramURL;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }



    public static Business getBusinessByName(String name) {
        Business business = null;
        for (int i = 0; i < businesses.length; i++ ) {
            Business b = businesses[i];
            if (name.toLowerCase().trim().equals(b.getName().toLowerCase())) {
                business = b;
                break;
            }
        }

        return business;
    }




}
