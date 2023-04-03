package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderHistoryActivity extends AppCompatActivity {
    //OrderItemAdapterClass adapter;
    DBHelper dbh;
    //ArrayList<HashMap> orderDetails;
    //RecyclerView orderList;
    long acctId;
    //long restaurantId;
    //String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        //problem is here
        //Bundle extras = getIntent().getExtras();
        //acctId = extras.getLong("acctId");

        //restaurantId = extras.getLong("restaurantId");
        //restaurantName = extras.getString("acctName");

        dbh = new DBHelper(this);
        TextView name = findViewById(R.id.orderHistory);

        Cursor c = dbh.viewDataOrder();
        StringBuilder str = new StringBuilder();
        if(c.getCount() > 0){
            while(c.moveToNext()){
                str.append("To: " + c.getString(0) + " ");
                str.append(" ");
                str.append(" Address: " + c.getString(1) + "\n");
                str.append("\n");
                str.append("Status: " + c.getString(2) + " ");
                str.append(" ");
                str.append(" Date: " + c.getString(3) + "\n");
                str.append("\n");
                str.append("Mode: " + c.getString(4) + " ");
                str.append(" ");
                str.append(" Total: $" + c.getString(5));
                str.append("\n\n");

                str.append("\n");
            }
            name.setText(str);
        } else {
            Toast.makeText(OrderHistoryActivity.this, "Nothing to display", Toast.LENGTH_LONG).show();
        }
        //orderDetails = new ArrayList<>();

        //orderList = findViewById(R.id.orderHistRecycler);

        //int columnCount = 1;
        //orderList.setLayoutManager(
        //        new GridLayoutManager(this, columnCount)
        //);

        //adapter = new OrderItemAdapterClass(this, orderDetails);
        //orderList.setAdapter(adapter);
        //getOrderDetails();

    }
    /*public void getOrderDetails() {
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
    } */
}