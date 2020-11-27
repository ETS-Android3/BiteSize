package com.example.bitesize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView popularRecyclerView, recommendedRecyclerView, allMenuRecyclerView;

    PopularAdapter popularAdapter;
    RecommendedAdapter recommendedAdapter;

    DatabaseReference databaseBusinesses;
    ArrayList<Business> businessList = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
        databaseBusinesses = FirebaseDatabase.getInstance().getReference("businesses");
        databaseBusinesses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                businessList.clear();
                for (DataSnapshot businessSnapshot : dataSnapshot.getChildren()) {
                    Business business = businessSnapshot.getValue(Business.class);
                    businessList.add(business);
                }

                RecyclerView businessRecycler = findViewById(R.id.popular_recycler);
                String[] businessNames = new String[businessList.size()];
                int[] businessImages = new int[businessList.size()];
                for (int i = 0; i < businessList.size(); i++) {
                    businessNames[i] = businessList.get(i).getName();
                    businessImages[i] = getResources().getIdentifier(businessList.get(i).getImageName(), "drawable", getPackageName());

                }

                CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(businessNames, businessImages);
                businessRecycler.setAdapter(adapter);

                GridLayoutManager lm = new GridLayoutManager(MainActivity.this, 2);
                businessRecycler.setLayoutManager(lm);


                adapter.setListener(new CaptionedImagesAdapter.Listener() {
                    public void onClick(String businessName) {
                        Intent i = new Intent(MainActivity.this, BusinessDetailsActivity.class);
                        i.putExtra("businessName", businessName);
                        startActivity(i);
                    }
                });

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void getPopularData(List<Business> popularList){


        popularRecyclerView = findViewById(R.id.popular_recycler);
        popularAdapter = new PopularAdapter(this, popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);

    }

    private void getRecommendedData(List<Business> recommendedList){

        recommendedRecyclerView = findViewById(R.id.recommended_recycler);
        recommendedAdapter = new RecommendedAdapter(this, recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedRecyclerView.setAdapter(recommendedAdapter);

    }
}