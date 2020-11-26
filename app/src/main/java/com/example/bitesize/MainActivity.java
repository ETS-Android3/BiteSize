package com.example.bitesize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView popularRecyclerView, recommendedRecyclerView, allMenuRecyclerView;

    PopularAdapter popularAdapter;
    RecommendedAdapter recommendedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecyclerView businessRecycler = findViewById(R.id.popular_recycler);
        Business[] businesses = (Business[]) Business.getAllBusinesses();
        String[] businessNames = new String[businesses.length];
        int[] businessImages = new int[businesses.length];
        for (int i = 0; i < businessNames.length; i++) {
            businessNames[i] = businesses[i].getName();
            businessImages[i] = businesses[i].getImageResourceId();
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