package com.example.lefties;

import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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


    public CartItemAdapterClass(@NonNull Context context, ArrayList cartItems , long acctId) {
        this.context = context;
        this.cartItems = cartItems;
        layoutInflater = LayoutInflater.from(context);
        dbh = new DBHelper(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        TextView foodName;


        // THIS MAPS ATTRIBUTES PER ITEM
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            foodName = itemView.findViewById(R.id.itemFoodName);
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
//
//        HashMap<String, String> foodItem = foods.get(position);
//        String foodIdString = foodItem.get("food_id");
//        long restaurantId = Long.parseLong(foodItem.get("account_id"));
//        int foodId = Integer.parseInt(foodIdString);
//
//        ((ViewHolder)holder).foodName.setText(name);


    }



    public void createOrder(){
        // find all orders related to user Id
    }


    @Override
    public int getItemCount() {
        return cartItems.size() ;
    }
}
