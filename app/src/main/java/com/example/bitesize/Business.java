package com.example.bitesize;

import java.util.ArrayList;

public class Business {
    private String name;
    private String bio;
    private String city;
    private float rating;
    private int price;
    private ArrayList<String> reviews;
    private int reviewCount;
    private ArrayList<String> categories;
    private String instagramURL;
    private int imageResourceId;


    private static ArrayList<String> testing_categories;
    private static ArrayList<MenuItem> testing_menu;
    public static final Business[] businesses = {
            new Business("Yugo Sushi Bake", "Testing", "Burnaby", (float) 4.8, 25,
                    testing_categories = new ArrayList<String>(), "https://www.instagram.com/yugosushibake/?hl=en", R.drawable.recommended1, testing_menu)
    };

    public static Object[] getAllBusinesses() {
        return businesses;
    }


    private ArrayList<MenuItem> menu;

    public Business() {}

    public Business(String name, String bio, String city, float rating, int price, ArrayList<String> categories, String instagramURL, int imageResourceId, ArrayList<MenuItem> menu) {
        this.name = name;
        this.bio = bio;
        this.city = city;
        this.rating = rating;
        this.price = price;
        this.reviews = new ArrayList<>();
        this.reviewCount = this.reviews.size();
        this.categories = categories;
        this.instagramURL = instagramURL;
        this.imageResourceId = imageResourceId;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
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
