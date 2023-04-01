package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    CartItemAdapterClass adapter;
//    String[] food = {"Paasta With Seafood", "Bruschetta"};
//    int[] images = {R.drawable.placeholder, R.drawable.bgimg};
    DBHelper dbh;
    ArrayList cartItems;
    long acctId;
    RecyclerView cartList;
    String restaurantName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Bundle extras = getIntent().getExtras();
        acctId = extras.getLong("acctId");
        restaurantName = extras.getString("restaurantName");

        dbh = new DBHelper(this);
        cartItems = new ArrayList<>();

        Button btnBack = findViewById(R.id.btnBack);
        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        Chip chipDelivery = findViewById(R.id.chipDelivery);
        Chip chipPickup = findViewById(R.id.chipPickup);
        TextView address = findViewById(R.id.txtAddress);
        TextView totalAmount = findViewById(R.id.txtAmount);
        Button btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        Button btnOrderMoreFood = findViewById(R.id.btnOrderMore);

        cartList = findViewById(R.id.cartRecycler);

        int columnCount = 1;
//        cartList.setAdapter();
        cartList.setLayoutManager(
                new GridLayoutManager(this, columnCount)
        );


        getCartContent();

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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, MainActivity.class));
            }
        });

    }

    public void getCartContent() {
        Cursor c = dbh.viewCustomerCart(acctId);

        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                long cartItem = Long.parseLong(c.getString(0));
                cartItems.add(cartItem);
            }

        adapter = new CartItemAdapterClass(this, cartItems , acctId, restaurantName);
        cartList.setAdapter(adapter);
        }
    }

//    public void applyListView(){
////        ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
////
////        for(int i=0; i<food.length; i++)
////        {
////            HashMap<String, String> hashMap =new HashMap<>();
////            hashMap.put("txt", food[i]);
////            hashMap.put("images", Integer.toString(images[i]));
////            aList.add(hashMap);
////        }
//
//        String[] from ={"images", "txt"};
//        int[] to ={R.id.imageFood, R.id.FoodText};
//
//        SimpleAdapter adapter = new SimpleAdapter(CartActivity.this,
//                aList,R.layout.recycler_cart_item,from,to);
//
//        ListView listView = findViewById(R.id.listViewSummary);
//
//        listView.setAdapter(adapter);
//    }

}