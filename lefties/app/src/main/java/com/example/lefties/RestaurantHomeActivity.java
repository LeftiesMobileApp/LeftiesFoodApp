package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantHomeActivity extends AppCompatActivity {
    RecyclerView inventoryList;
    DBHelper dbh;
    Integer restaurantAcctId = 1;
    ArrayList<HashMap<String, String>> inventoryMapper = new ArrayList<>();
    Button addItem;
    Button generateReport;
    TextView headline;
    ArrayList<HashMap> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_home);

        headline = findViewById(R.id.txtRestoHomeWelcome);

        dbh = new DBHelper(this);
        if(dbh.viewDataFood().getCount() < 1){
            dbh.seedFoodTable();
        }


        inventoryList = findViewById(R.id.recyclerInventory);
        int columnCount = 2;
        inventoryList.setLayoutManager(
                new GridLayoutManager(this, columnCount));

        displayFoodItemFromRecycler();

        addItem = findViewById(R.id.btnAddItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddItem();
            }
        });

        generateReport = findViewById(R.id.btnRemind);
        generateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void goToAddItem(){
        Intent i = new Intent(getApplicationContext(), RestaurantAddAnItemActivity.class);
        i.putExtra("restaurantAcctId", restaurantAcctId);
        startActivity(i);
    }

    public void displayFoodItemFromRecycler(){

        // CREATE ARRAYLIST of HashMap FROM DB
        foods = new ArrayList<HashMap>();
        Cursor c = dbh.viewDataFoodByRestaurant(restaurantAcctId);

        if(c.getCount() > 0){
            while(c.moveToNext()){ // while there is still line left
                HashMap<String, String> foodTableColumns = new HashMap<>();
                foodTableColumns.put("food_id", c.getString(0));
                foodTableColumns.put("account_id", c.getString(1));
                foodTableColumns.put("food_name", c.getString(2));
                foodTableColumns.put("food_discounted_price", c.getString(3));
                foodTableColumns.put("food_regular_price", c.getString(4));
                foodTableColumns.put("food_qty", c.getString(5));
                foods.add(foodTableColumns);
            }
        }
        Boolean isRestaurant = true;
        FoodDisplayAdapterClass adapter = new FoodDisplayAdapterClass(this, foods, isRestaurant);
        inventoryList.setAdapter(adapter);
    }



}
