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

public class OrderHistoryActivity extends AppCompatActivity {
    OrderItemAdapterClass adapter;
    DBHelper dbh;
    ArrayList orderDetails;
    RecyclerView orderList;
    long acctId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        Bundle extras = getIntent().getExtras();
        acctId = extras.getLong("acctId");

        dbh = new DBHelper(this);
        orderDetails = new ArrayList<>();

        orderList = findViewById(R.id.orderHistRecycler);

        int columnCount = 1;
        orderList.setLayoutManager(
                new GridLayoutManager(this, columnCount)
        );

        adapter = new OrderItemAdapterClass(this, orderDetails);
        orderList.setAdapter(adapter);

//        getOrderDetails();

    }

    public void getOrderDetails() {
        Cursor c = dbh.viewCustomerCart(acctId);

        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                long orderDetail = Long.parseLong(c.getString(0));
                orderDetails.add(orderDetail);
            }

            adapter = new OrderItemAdapterClass(this, orderDetails);
            orderList.setAdapter(adapter);
        }

    }

}