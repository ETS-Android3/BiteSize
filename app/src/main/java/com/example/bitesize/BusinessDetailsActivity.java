package com.example.bitesize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        final Business business = Business.getBusinessByName(businessName);

        if (business != null) {
            TextView food_text = findViewById(R.id.name);
            food_text.setText(business.getName());

            RatingBar business_rating = findViewById(R.id.ratingBar);
            business_rating.setStepSize(0.5f);
            business_rating.setRating(business.getRating());

            ImageView business_image = findViewById(R.id.imageView5);
            business_image.setImageDrawable(ContextCompat.getDrawable(this, business.getImageResourceId()));
            business_image.setContentDescription(business.getName());


            Button clickButton = (Button) findViewById(R.id.instagram_button);
            clickButton.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    String url = business.getInstagramURL();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });


        }
    }





}