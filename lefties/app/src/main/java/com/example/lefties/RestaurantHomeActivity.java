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
    ArrayList<HashMap<String, String>> inventoryMapper = new ArrayList<>();
    Button addItem;
    Button sendReminder;
    Button generateReport;
    TextView headline;
    String restaurantName;
    ArrayList<HashMap> foods;
    long acctId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_home);

        Bundle extras = getIntent().getExtras();
        acctId = extras.getLong("acctId");
        restaurantName = extras.getString("acctName");

        headline = findViewById(R.id.txtRestoHomeWelcome);

        dbh = new DBHelper(this);

        inventoryList = findViewById(R.id.customerRestaurantRecycler);
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

        generateReport = findViewById(R.id.btnGenerate);
        generateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RestaurantHomeActivity.this, OrderHistoryActivity.class));
            }
        });

        sendReminder = findViewById(R.id.btnRemind);
        sendReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void goToAddItem(){
        Intent i = new Intent(getApplicationContext(), RestaurantAddAnItemActivity.class);
        i.putExtra("acctId", acctId);
        startActivity(i);
    }

    public void displayFoodItemFromRecycler(){

        // CREATE ARRAYLIST of HashMap FROM DB
        foods = new ArrayList<HashMap>();
        Cursor c = dbh.viewDataFoodByRestaurant(acctId);

        if(c.getCount() > 0){
            while(c.moveToNext()){ // while there is still line left
                HashMap<String, String> foodTableColumns = new HashMap<>();
                foodTableColumns.put("food_id", c.getString(0));
                foodTableColumns.put("account_id", c.getString(1));
                foodTableColumns.put("food_name", c.getString(2));
                foodTableColumns.put("food_discounted_price", c.getString(3));
                foodTableColumns.put("food_regular_price", c.getString(4));
                foodTableColumns.put("food_qty", c.getString(5));
                foodTableColumns.put("restaurant_name", restaurantName);
                foods.add(foodTableColumns);
            }
        }
        Boolean isRestaurant = true;
        FoodItemAdapterClass adapter = new FoodItemAdapterClass(this, foods, acctId);
        inventoryList.setAdapter(adapter);
    }
}
