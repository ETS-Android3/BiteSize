package com.example.bitesize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BusinessDetailsActivity extends AppCompatActivity {

    DatabaseReference databaseBusinesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);

        databaseBusinesses = FirebaseDatabase.getInstance().getReference("businesses");
//        databaseBusinesses.orderByKey().equalTo(getIntent().getExtras().getString("businessName"));
        Business business = new Business();
        databaseBusinesses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot businessSnapshot : dataSnapshot.getChildren()) {
                    if (businessSnapshot.child("name").getValue(String.class).equals(getIntent().getExtras().getString("businessName"))) {
                        System.out.println(businessSnapshot.child("name").getValue(String.class));
                        business.setName(businessSnapshot.child("name").getValue(String.class));
                        business.setBio(businessSnapshot.child("bio").getValue(String.class));
                        business.setCity(businessSnapshot.child("city").getValue(String.class));
                        business.setImageName(businessSnapshot.child("imageName").getValue(String.class));
                        business.setInstagramURL(businessSnapshot.child("instagramURL").getValue(String.class));
                        business.setPrice(businessSnapshot.child("price").getValue(Integer.class));
                        business.setRating(businessSnapshot.child("rating").getValue(Float.class));
                    }
                }
                displayBusinessDetails(business);

            };


            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }



    @SuppressLint("SetTextI18n")
    private void displayBusinessDetails(Business business) {

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                finish();
            }
        });

        if (business != null) {

            TextView food_text = findViewById(R.id.name);
            food_text.setText(business.getName());

            RatingBar business_rating = findViewById(R.id.ratingBar);
            business_rating.setStepSize(0.25f);
            business_rating.setRating(business.getRating());

            TextView rating = findViewById(R.id.rating);
            rating.setText(Float.toString(business.getRating()));

            ImageView business_image = findViewById(R.id.businessLogo);
            business_image.setImageResource(getResources().getIdentifier(business.getImageName(), "drawable", getPackageName()));
            business_image.setContentDescription(business.getName());

            TextView biography = findViewById(R.id.businessDetails);
            biography.setText(business.getBio());


            Button instagramButton = (Button) findViewById(R.id.instagram_button);
            instagramButton.setOnClickListener( new View.OnClickListener() {

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



            TextView location_text = findViewById(R.id.textViewLocation);
            location_text.setText(business.getCity());


            Button menuButton = (Button) findViewById(R.id.menu_button);
            String businessName = business.getName();
            menuButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(BusinessDetailsActivity.this, MenuActivity.class);
                    i.putExtra("businessName", businessName);
                    startActivity(i);
                    }
                });




            Button reviewsButton = (Button) findViewById(R.id.reviews_button);
            reviewsButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(BusinessDetailsActivity.this, ReviewsActivity.class);
                    i.putExtra("businessName", businessName);
                    startActivity(i);
                }
            });

        }
    }
}





