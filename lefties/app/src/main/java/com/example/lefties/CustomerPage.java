package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerPage extends AppCompatActivity {

    DBHelper dbh;
    RecyclerView inventoryList;
    Integer customerAcctId = 1;
    ArrayList<HashMap<String, String>> inventoryMapper = new ArrayList<>();
    Button addItem;
    ArrayList<HashMap> foods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);

       // Button btn = findViewById(R.id.itemBtnAddToCart);



        dbh = new DBHelper(this);
        if(dbh.viewDataFood().getCount() < 1){
            dbh.seedFoodTable();
        }
        inventoryList = findViewById(R.id.recyclerInventory);



        int columnCount = 2;
        inventoryList.setLayoutManager(
                new GridLayoutManager(this, columnCount));

        displayFoodItemFromRecycler();
    }

    /* public void goToAddItem(){
        Intent i = new Intent(getApplicationContext(), Cart.class);
        i.putExtra("customerAcctId", customerAcctId);
        startActivity(i);
    }*/

    public void displayFoodItemFromRecycler(){

        // CREATE ARRAYLIST of HashMap FROM DB
        foods = new ArrayList<HashMap>();
        Cursor c = dbh.viewDataFood();

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
        InventoryRecyclerAdapter adapter = new InventoryRecyclerAdapter(this, foods, false);
        inventoryList.setAdapter(adapter);
    }
}