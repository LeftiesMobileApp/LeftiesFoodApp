package com.example.lefties;

import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;


public class CartItemAdapterClass extends RecyclerView.Adapter {
    LayoutInflater layoutInflater;
    Context context;
    String contextClass;
    ArrayList cartItems;
    DBHelper dbh;
    long acctId;
    String restaurantName;
    long restaurantId;


    public CartItemAdapterClass(@NonNull Context context, ArrayList cartItems , long acctId, String restaurantName) {
        this.context = context;
        this.cartItems = cartItems;
        this.restaurantName = restaurantName;
        layoutInflater = LayoutInflater.from(context);
        dbh = new DBHelper(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView restName, foodName, foodPrice, itemQty;
        Button btnPlus, btnMinus;

        Integer qty;


        // THIS MAPS ATTRIBUTES PER ITEM
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.txtFoodName);
            restName = itemView.findViewById(R.id.txtRestName);
            foodPrice = itemView.findViewById(R.id.txtIFoodPrice);
            itemQty = itemView.findViewById(R.id.txtQty);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);


            return;
        }

        @Override
        public void onClick(View v) {

        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view =  layoutInflater.inflate(R.layout.recycler_cart_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view); // viewHolder holds the layoutInflater
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).restName.setText(restaurantName);
        long foodId = (long) cartItems.get(position);
//        Cursor c = dbh.viewDataFoodById(foodId);
        Cursor c = dbh.viewDataFoodWithCartByFoodId(foodId);
        c.moveToFirst();
        Log.i("qty", c.getString(3));
        ((ViewHolder) holder).qty = Integer.parseInt(c.getString(9));

        if(c.getCount() > 0) {
            if (c.moveToFirst()) {
                ((ViewHolder) holder).foodName.setText(
                        c.getString(2)
                );
                ((ViewHolder) holder).foodPrice.setText(
                        "$ " + c.getString(3)
                );
                ((ViewHolder)holder).itemQty.setText(
                        ""+((ViewHolder) holder).qty
                );
            }
        }

        ((ViewHolder)holder).btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add
                ((ViewHolder) holder).qty = ((ViewHolder) holder).qty+1;
                dbh.updateQty(foodId, acctId, true);
                ((ViewHolder) holder).itemQty.setText(
                        ""+((ViewHolder) holder).qty
                );
            }
        });

        ((ViewHolder)holder).btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((ViewHolder) holder).qty<=0) return;
                // deduct
                ((ViewHolder) holder).qty = ((ViewHolder) holder).qty-1;
                dbh.updateQty(foodId, acctId, false);
                ((ViewHolder) holder).itemQty.setText(
                        ""+((ViewHolder) holder).qty
                );
            }
        });





    }



    public void createOrder(){
        // find all orders related to user Id
    }


    @Override
    public int getItemCount() {
        return cartItems.size() ;
    }
}
