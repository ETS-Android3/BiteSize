package com.example.bitesize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    RecyclerView searchRecyclerView;
    SearchAdapter searchAdapter;

    DatabaseReference databaseBusinesses;
    ArrayList<Business> businessList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ImageView backButton = findViewById(R.id.backButtonSearch);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        databaseBusinesses = FirebaseDatabase.getInstance().getReference("businesses");
        databaseBusinesses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                businessList.clear();
                for (DataSnapshot businessSnapshot : dataSnapshot.getChildren()) {
                    if (businessSnapshot.getKey().equals(getIntent().getExtras().getString("keyword"))
                            || businessSnapshot.child("tag").getValue(String.class).equals(getIntent().getExtras().getString("keyword"))) {
                        Business business = businessSnapshot.getValue(Business.class);
                        businessList.add(business);
                    }
                }

                RecyclerView searchRecycler = findViewById(R.id.search_recycler);
                String[] businessNames = new String[businessList.size()];
                int[] businessImages = new int[businessList.size()];
                for (int i = 0; i < businessList.size(); i++) {
                    businessNames[i] = businessList.get(i).getName();
                    businessImages[i] = getResources().getIdentifier(businessList.get(i).getImageName(), "drawable", getPackageName());
                }
                CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(businessNames, businessImages);
                searchRecycler.setAdapter(adapter);

                GridLayoutManager lm = new GridLayoutManager(SearchActivity.this, 2);
                searchRecycler.setLayoutManager(lm);

                adapter.setListener((businessName) -> {
                    Intent i = new Intent(SearchActivity.this, BusinessDetailsActivity.class);
                    i.putExtra("businessName", businessName);
                    startActivity(i);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}
