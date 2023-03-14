package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class Cart extends AppCompatActivity {

//    Integer[] images = {};
//    String[] restName = {"PH", "UB", "TJ"};
//    String[] item = {};
//    String[] itemPrice = {};
    CartAdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Button btnBack = findViewById(R.id.btnBack);
        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        Chip chipDelivery = findViewById(R.id.chipDelivery);
        Chip chipPickup = findViewById(R.id.chipPickup);
        TextView address = findViewById(R.id.txtAddress);
        TextView totalAmount = findViewById(R.id.txtAmount);
        Button btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        Button btnOrderMoreFood = findViewById(R.id.btnOrderMore);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int numOfCol = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numOfCol));
        adapter = new CartAdapterClass(this); //images, item, itemPrice, restName
        recyclerView.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cart.this, MainActivity.class));
            }
        });

    }
}