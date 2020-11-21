package com.example.bitesize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class BusinessDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);
        displayBusinessDetails();
    }


    private void displayBusinessDetails() {
        String businessName = (String) getIntent().getExtras().get("businessName");

        Business business = Business.getBusinessByName(businessName);

        if (business != null) {
            TextView food_text = findViewById(R.id.name);
            food_text.setText(business.getName());

            RatingBar business_rating = findViewById(R.id.ratingBar);
            business_rating.setStepSize(0.5f);
            business_rating.setRating(business.getRating());

            ImageView business_image = findViewById(R.id.imageView5);
            business_image.setImageDrawable(ContextCompat.getDrawable(this, business.getImageResourceId()));
            business_image.setContentDescription(business.getName());
        }
    }





}