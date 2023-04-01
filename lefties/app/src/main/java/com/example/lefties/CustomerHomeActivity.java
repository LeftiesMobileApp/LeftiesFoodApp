package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
// Macci - Renamed CustomerActivity to CustomerHomeActivity
public class CustomerHomeActivity extends AppCompatActivity {

    DBHelper dbh;
    RecyclerView inventoryList;
    ArrayList<HashMap<String, String>> inventoryMapper = new ArrayList<>();
    Button addItem;
    FoodItemAdapterClass adapter;
    ArrayList<HashMap> foods;

    Cursor c;

    long acctId;
    long restaurantIdChosen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        Bundle extras = getIntent().getExtras();
        acctId = extras.getLong("acctId");

       TextView name = findViewById(R.id.FromD);
       TextView address = findViewById(R.id.AtD);
       TextView city = findViewById(R.id.ToD);

        dbh = new DBHelper(this);

        inventoryList = findViewById(R.id.customerRestaurantRecycler);

        int columnCount = 2;
        inventoryList.setLayoutManager(
                new GridLayoutManager(this, columnCount));

        foods = new ArrayList<HashMap>();
        c = dbh.viewDataFoodWithRestaurantName();
        updateRecycler(c);


    }

    public void updateRecycler(Cursor c){
        if (c.getCount() > 0) {
            while (c.moveToNext()) { // while there is still line left
                HashMap<String, String> foodTableColumns = new HashMap<>();
                foodTableColumns.put("food_id", c.getString(0));
                foodTableColumns.put("account_id", c.getString(1));
                foodTableColumns.put("food_name", c.getString(2));
                foodTableColumns.put("food_discounted_price", c.getString(3));
                foodTableColumns.put("food_regular_price", c.getString(4));
                foodTableColumns.put("food_qty", c.getString(5));
                foodTableColumns.put("restaurant_name", c.getString(7));
                foods.add(foodTableColumns);
            }
        }
        adapter = new FoodItemAdapterClass(this, foods, acctId);
        inventoryList.setAdapter(adapter);
    }


    // Macci - Isolate search functionality

    public void setupSearchByCity(){
        // Rajat - Enable city search
        Spinner spinner = findViewById(R.id.spinner3);
        if (spinner != null && spinner.getSelectedItem() != null && spinner.getSelectedItem().toString().equals("Surrey")) {
            // Macci - Create reusable function updateRecycler that accepts the cursor
            c = dbh.viewDataFoodM();
            updateRecycler(c);
        }

        if (spinner != null && spinner.getSelectedItem() != null && spinner.getSelectedItem().toString().equals("Vancouver")) {
            c = dbh.viewDataFood();
            updateRecycler(c);
        }

        if (spinner != null && spinner.getSelectedItem() != null && spinner.getSelectedItem().toString().equals("Burnaby")) {
            c = dbh.viewDataFoodP();
            updateRecycler(c);

        }


    }

    public void setupSearchByType(){
        Button featured = findViewById(R.id.Featured);
        Button mexican = findViewById(R.id.typeMexican);
        Button Pastries = findViewById(R.id.Pastries);
        Button Chinese = findViewById(R.id.typeChinese);
        Button Chicken = findViewById(R.id.Chicken);
        Button Veg = findViewById(R.id.Vegetarian);

        featured.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = dbh.viewDataFoodF();

                if(c.getCount() > 0){
                    foods.clear(); // clear the previous list
                    while(c.moveToNext()){
                        HashMap<String, String> foodTableColumns = new HashMap<>();
                        foodTableColumns.put("food_id", c.getString(0));
                        foodTableColumns.put("account_id", c.getString(1));
                        foodTableColumns.put("food_name", c.getString(2));
                        foodTableColumns.put("food_discounted_price", c.getString(3));
                        foodTableColumns.put("food_regular_price", c.getString(4));
                        foodTableColumns.put("food_qty", c.getString(5));
                        foods.add(foodTableColumns);
                    }
                    adapter.notifyDataSetChanged(); // update the adapter with the new list
                }
            }
        });

        mexican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = dbh.viewDataFoodM();

                if(c.getCount() > 0){
                    foods.clear(); // clear the previous list
                    while(c.moveToNext()){
                        HashMap<String, String> foodTableColumns = new HashMap<>();
                        foodTableColumns.put("food_id", c.getString(0));
                        foodTableColumns.put("account_id", c.getString(1));
                        foodTableColumns.put("food_name", c.getString(2));
                        foodTableColumns.put("food_discounted_price", c.getString(3));
                        foodTableColumns.put("food_regular_price", c.getString(4));
                        foodTableColumns.put("food_qty", c.getString(5));
                        foods.add(foodTableColumns);
                    }
                    adapter.notifyDataSetChanged(); // update the adapter with the new list
                }
            }
        });

        Pastries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = dbh.viewDataFoodP();

                if(c.getCount() > 0){
                    foods.clear(); // clear the previous list
                    while(c.moveToNext()){
                        HashMap<String, String> foodTableColumns = new HashMap<>();
                        foodTableColumns.put("food_id", c.getString(0));
                        foodTableColumns.put("account_id", c.getString(1));
                        foodTableColumns.put("food_name", c.getString(2));
                        foodTableColumns.put("food_discounted_price", c.getString(3));
                        foodTableColumns.put("food_regular_price", c.getString(4));
                        foodTableColumns.put("food_qty", c.getString(5));
                        foods.add(foodTableColumns);
                    }
                    adapter.notifyDataSetChanged(); // update the adapter with the new list
                }
            }
        });

        Chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = dbh.viewDataFoodC();

                if(c.getCount() > 0){
                    foods.clear(); // clear the previous list
                    while(c.moveToNext()){
                        HashMap<String, String> foodTableColumns = new HashMap<>();
                        foodTableColumns.put("food_id", c.getString(0));
                        foodTableColumns.put("account_id", c.getString(1));
                        foodTableColumns.put("food_name", c.getString(2));
                        foodTableColumns.put("food_discounted_price", c.getString(3));
                        foodTableColumns.put("food_regular_price", c.getString(4));
                        foodTableColumns.put("food_qty", c.getString(5));
                        foods.add(foodTableColumns);
                    }
                    adapter.notifyDataSetChanged(); // update the adapter with the new list
                }
            }
        });

        Chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = dbh.viewDataFoodCh();

                if(c.getCount() > 0){
                    foods.clear(); // clear the previous list
                    while(c.moveToNext()){
                        HashMap<String, String> foodTableColumns = new HashMap<>();
                        foodTableColumns.put("food_id", c.getString(0));
                        foodTableColumns.put("account_id", c.getString(1));
                        foodTableColumns.put("food_name", c.getString(2));
                        foodTableColumns.put("food_discounted_price", c.getString(3));
                        foodTableColumns.put("food_regular_price", c.getString(4));
                        foodTableColumns.put("food_qty", c.getString(5));
                        foods.add(foodTableColumns);
                    }
                    adapter.notifyDataSetChanged(); // update the adapter with the new list
                }
            }
        });

        Veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = dbh.viewDataFoodVeg();

                if(c.getCount() > 0){
                    foods.clear(); // clear the previous list
                    while(c.moveToNext()){
                        HashMap<String, String> foodTableColumns = new HashMap<>();
                        foodTableColumns.put("food_id", c.getString(0));
                        foodTableColumns.put("account_id", c.getString(1));
                        foodTableColumns.put("food_name", c.getString(2));
                        foodTableColumns.put("food_discounted_price", c.getString(3));
                        foodTableColumns.put("food_regular_price", c.getString(4));
                        foodTableColumns.put("food_qty", c.getString(5));
                        foods.add(foodTableColumns);
                    }
                    adapter.notifyDataSetChanged(); // update the adapter with the new list
                }
            }
        });

    }

}

