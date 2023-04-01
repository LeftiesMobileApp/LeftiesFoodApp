package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {

    CartAdapterClass adapter;
    String[] food = {"Paasta With Seafood", "Bruschetta"};
    int[] images = {R.drawable.placeholder, R.drawable.bgimg};

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
        Button btnPlaceOrder = findViewById(R.id.itemBtnAddToCart);
        Button btnOrderMoreFood = findViewById(R.id.btnOrderMore);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, OrderConfirmation.class));
            }
        });

        btnOrderMoreFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CustomerHomeActivity.class));
            }
        });

        applyListView();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });

    }

    public void applyListView(){
        ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for(int i=0; i<food.length; i++)
        {
            HashMap<String, String> hashMap =new HashMap<>();
            hashMap.put("txt", food[i]);
            hashMap.put("images", Integer.toString(images[i]));
            aList.add(hashMap);
        }

        String[] from ={"images", "txt"};
        int[] to ={R.id.imageFood, R.id.FoodText};

        SimpleAdapter adapter = new SimpleAdapter(CartActivity.this,
                aList,R.layout.recycler_cart_item,from,to);

        ListView listView = findViewById(R.id.listViewSummary);

        listView.setAdapter(adapter);
    }
}