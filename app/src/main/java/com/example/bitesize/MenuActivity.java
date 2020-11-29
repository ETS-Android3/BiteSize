package com.example.bitesize;

import android.content.Context;
import android.os.Bundle;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity {

    RecyclerView menuRecyclerView;
    DatabaseReference databaseMenuItems;
    ArrayList<MenuItem> menuList = new ArrayList<>();


    @Override
    protected void onStart() {
        super.onStart();
        databaseMenuItems = FirebaseDatabase.getInstance().getReference("menu_items");
        databaseMenuItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                menuList.clear();
                for (DataSnapshot menuSnapshot : dataSnapshot.getChildren()) {
                    if (menuSnapshot.getKey().equals(getIntent().getExtras().getString("businessName"))) {
                        for (DataSnapshot menuItemSnapshot : menuSnapshot.getChildren()) {
                            MenuItem menuItem = menuItemSnapshot.getValue(MenuItem.class);
                            menuList.add(menuItem);
                        }
                    }
                }

                menuRecyclerView = findViewById(R.id.menu_recycler);
                MenuItemAdapter adapter = new MenuItemAdapter(MenuActivity.this, menuList);
                menuRecyclerView.setAdapter(adapter);

                LinearLayoutManager lm = new LinearLayoutManager(MenuActivity.this);
                menuRecyclerView.setLayoutManager(lm);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }
}