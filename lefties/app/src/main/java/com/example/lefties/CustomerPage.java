package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerPage extends AppCompatActivity {

    RecyclerView inventoryList;
    String[] someArray = {"hello", "there", "hi", "again", "once more"};
    ArrayList<HashMap<String, String>> inventoryMapper = new ArrayList<>();
    Button addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);

        inventoryList = findViewById(R.id.recyclerInventory);

        int columnCount = 2;
        inventoryList.setLayoutManager(
                new GridLayoutManager(this, columnCount));

        // BRING ME BACK WHEN RECYCLER IS FIXED
//        InventoryRecyclerAdapter adapter = new InventoryRecyclerAdapter(this, someArray);
//        inventoryList.setAdapter(adapter);


    }
}