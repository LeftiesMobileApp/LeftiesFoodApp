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

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderHistoryActivity extends AppCompatActivity {
    OrderItemAdapterClass adapter;
    DBHelper dbh;
    ArrayList<HashMap> orderDetails;
    RecyclerView orderList;
    long acctId;
    //long restaurantId;
    //String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        //problem is here
        Bundle extras = getIntent().getExtras();
        //acctId = extras.getLong("acctId");

        //restaurantId = extras.getLong("restaurantId");
        //restaurantName = extras.getString("acctName");

        dbh = new DBHelper(this);
        orderDetails = new ArrayList<>();

        orderList = findViewById(R.id.orderHistRecycler);

        int columnCount = 1;
        orderList.setLayoutManager(
                new GridLayoutManager(this, columnCount)
        );

        //adapter = new OrderItemAdapterClass(this, orderDetails, acctId);
        //orderList.setAdapter(adapter);
        getOrderDetails();

    }
    public void getOrderDetails() {
        orderDetails = new ArrayList<HashMap>();
        Cursor c = dbh.viewDataOrder();

        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                HashMap<String, String> orderTableColumns = new HashMap<>();
               // long orderDetail = Long.parseLong(c.getString(0));
                orderTableColumns.put("order_id", c.getString(0));
                orderTableColumns.put("order_date", c.getString(1));
                orderTableColumns.put("order_status", c.getString(2));
                orderTableColumns.put("order_total", c.getString(3));
                orderTableColumns.put("account_name", c.getString(4));
                orderTableColumns.put("account_address", c.getString(5));
               // orderTableColumns.put("restaurant_name", restaurantName);
                orderDetails.add(orderTableColumns);
            }
        }
        adapter = new OrderItemAdapterClass(this, orderDetails);
        orderList.setAdapter(adapter);
    }
}