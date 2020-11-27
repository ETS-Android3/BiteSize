package com.example.bitesize;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchActivity extends AppCompatActivity {
    RecyclerView searchRecyclerView;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        RecyclerView searchRecycler = findViewById(R.id.search_recycler);
        Business[] businesses = (Business[]) Business.getAllBusinesses();
        String[] businessNames = new String[businesses.length];
        int[] businessImages = new int[businesses.length];
        for (int i = 0; i < businesses.length; i++) {
            businessNames[i] = businesses[i].getName();
            businessImages[i] = businesses[i].getImageResourceId();
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
}
