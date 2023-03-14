package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantHomeActivity extends AppCompatActivity {
    RecyclerView inventoryList;
    String[] someArray = {"hello", "there", "hi", "again", "once more"};
    ArrayList<HashMap<String, String>> inventoryMapper = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_home);

        inventoryList = findViewById(R.id.recyclerInventory);
//        listCompanies = findViewById(R.id.listviewCompanies);

        int columnCount = 2;
        inventoryList.setLayoutManager(
                new GridLayoutManager(this, columnCount));
//        companies = prepareCompanies(companiesStringArr);
//
        InventoryRecyclerAdapter adapter = new InventoryRecyclerAdapter(this, someArray);
        inventoryList.setAdapter(adapter);
//        setupListView(inventoryMapper);
    }
}
