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
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderHistoryActivity extends AppCompatActivity {

    DBHelper dbh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        dbh = new DBHelper(this);
        TextView name = findViewById(R.id.orderHistory);

        Cursor c = dbh.viewDataOrder();
        StringBuilder str = new StringBuilder();
        if(c.getCount() > 0){
            while(c.moveToNext()){
                str.append("1. Restaurant_name: " + c.getString(0) + "\n");
                str.append("\n");
                str.append("2. Restaurant_address: " + c.getString(1) + "\n");
                str.append("\n");
                str.append("3. order_status: " + c.getString(2) + "\n");
                str.append("\n");
                str.append("4. order_date: " + c.getString(3) + "\n");
                str.append("\n");
                str.append("5. order_type: " + c.getString(4) + "\n");
                str.append("\n");
                str.append("6. order_total: " + c.getString(5));



                str.append("\n");
            }
            name.setText(str);
        } else {
            Toast.makeText(OrderHistoryActivity.this, "Nothing to display", Toast.LENGTH_LONG).show();
        }



//        Cursor c = dbh.viewAt();
//        StringBuilder str = new StringBuilder();
//        if(c.getCount() > 0) {
//            while (c.moveToNext()) {
//                str.append(c.getString(0));
//                str.append("\n");
//            }
//            name.setText(str.toString());
//        } else {
//            name.setText("No data found");
//        }
//        c.close();
    }





}



//  Cursor c = dbh.viewDataOrder();
//        StringBuilder str = new StringBuilder();
//        if(c.getCount()>0){
//            while(c.moveToNext()){
//                str.append("order_Id: " + c.getString(0));
//                str.append("order_date: " + c.getString(1));
//                str.append("order_status: " + c.getString(2));
//                str.append("order_total : " + c.getString(3));
//                str.append("customer_name " + c.getString(4));
//                str.append("\n");
//            }
//            orderhist.setText(str);
//        }
//        else{
//            Toast.makeText(OrderHistoryActivity.this,"nothing to display",Toast.LENGTH_LONG).show();
//        }