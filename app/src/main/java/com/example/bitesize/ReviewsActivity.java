package com.example.bitesize;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ReviewsActivity extends AppCompatActivity {

    ListView lvReviews;
    DatabaseReference databaseReviewItems;
    ArrayList<Review> reviewList;

    EditText editTextName;
    EditText editTextReview;
    RatingBar ratingBar;
    Float reviewNumber;
    Button buttonAddReview;




    @Override
    protected void onStart() {
        super.onStart();
        databaseReviewItems = FirebaseDatabase.getInstance().getReference("reviews");
        databaseReviewItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reviewList.clear();
                for (DataSnapshot reviewSnapshot : dataSnapshot.getChildren()) {
                    if (reviewSnapshot.getKey().equals(getIntent().getExtras().getString("businessName"))) {
                        for (DataSnapshot reviewItemSnapshot : reviewSnapshot.getChildren()) {
                            Review review = reviewItemSnapshot.getValue(Review.class);
                            reviewList.add(review);
                        }
                    }
                }


                ReviewListAdapter adapter = new ReviewListAdapter(ReviewsActivity.this, reviewList);
                lvReviews.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        lvReviews = findViewById(R.id.lvReviews);
        reviewList = new ArrayList<>();
        buttonAddReview = findViewById(R.id.buttonAddReview);
        editTextName = findViewById(R.id.editTextName);
        editTextReview = findViewById(R.id.editTextReview);
        ratingBar = findViewById(R.id.reviewRatingBar);

        buttonAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview();
            }
        });


    }



    private void addReview() {
        String name = editTextName.getText().toString().trim();
        String reviewReview = editTextReview.getText().toString().trim();
        float ratingNumber = ratingBar.getRating();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "You must enter a name.", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(reviewReview)) {
            Toast.makeText(this, "You must enter a review.", Toast.LENGTH_LONG).show();
            return;
        }

        String id = databaseReviewItems.push().getKey();
        Review review = new Review(name, reviewReview, ratingNumber);

        Task setValueTask = databaseReviewItems.child(getIntent().getExtras().getString("businessName")).child(id).setValue(review);

        setValueTask.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(ReviewsActivity.this,"Review added.",Toast.LENGTH_LONG).show();

                editTextName.setText("");
                editTextReview.setText("");
                ratingBar.setNumStars(0);
            }
        });

        setValueTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ReviewsActivity.this,
                        "Something went wrong. Try again.\n" + e.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }






}