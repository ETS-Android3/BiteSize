package com.example.bitesize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView popularRecyclerView, recommendedRecyclerView;

    PopularAdapter popularAdapter;
    RecommendedAdapter recommendedAdapter;

    DatabaseReference databaseBusinesses;
    ArrayList<Business> businessList = new ArrayList<>();
    EditText searchEditText;
    Button searchButton, logoutButton;

    @Override
    protected void onStart() {
        super.onStart();

        // Logout
        logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

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
                RecyclerView allRecycler = findViewById(R.id.all_recycler);
                String[] businessNames = new String[businessList.size()];
                int[] businessImages = new int[businessList.size()];
                for (int i = 0; i < businessList.size(); i++) {
                    businessNames[i] = businessList.get(i).getName();
                    businessImages[i] = getResources().getIdentifier(businessList.get(i).getImageName(), "drawable", getPackageName());

                }

                CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(businessNames, businessImages);
                businessRecycler.setAdapter(adapter);
                allRecycler.setAdapter(adapter);

                GridLayoutManager lm = new GridLayoutManager(MainActivity.this, 1, RecyclerView.HORIZONTAL, false);
                businessRecycler.setLayoutManager(lm);
                GridLayoutManager lm2 = new GridLayoutManager(MainActivity.this, 2);
                allRecycler.setLayoutManager(lm2);


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

        searchEditText = findViewById(R.id.search_bar);
        searchEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Intent i = new Intent(MainActivity.this, SearchActivity.class);
                    i.putExtra("keyword", searchEditText.getText().toString().trim());
                    searchEditText.setText("");
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });

        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                i.putExtra("keyword", searchEditText.getText().toString().trim());
                searchEditText.setText("");
                startActivity(i);
            }
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

        recommendedRecyclerView = findViewById(R.id.all_recycler);
        recommendedAdapter = new RecommendedAdapter(this, recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedRecyclerView.setAdapter(recommendedAdapter);

    }
}