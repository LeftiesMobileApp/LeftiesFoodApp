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

public class InventoryRecyclerAdapter extends RecyclerView.Adapter {
    LayoutInflater layoutInflater;
    Context context;
    String[] strArr;
    ArrayList<HashMap> foods;
    Boolean isRestaurant;

    public InventoryRecyclerAdapter(@NonNull Context context, ArrayList<HashMap> foods, Boolean isRestaurant ) {
        this.context = context;
        this.foods = foods;
        this.isRestaurant = isRestaurant;
        layoutInflater = LayoutInflater.from(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgCompanyLogo;
        TextView foodName;
        TextView discountedPrice;
        TextView regularPrice;
        Button btnEdit;
        Button btnDelete;
        Button btnAddToCart;

        // THIS MAPS ATTRIBUTES PER ITEM
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.itemFoodName);
            discountedPrice = itemView.findViewById(R.id.itemDiscountedPrice);
            regularPrice = itemView.findViewById(R.id.itemRegularPrice);

            regularPrice.setPaintFlags(regularPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            btnDelete = itemView.findViewById(R.id.itemBtnDelete);
            btnEdit= itemView.findViewById(R.id.itemBtnEdit);
            btnAddToCart = itemView.findViewById(R.id.itemBtnAddToCart);

            return;
        }
        @Override
        public void onClick(View v) {
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view =  layoutInflater.inflate(R.layout.recyler_food_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view); // viewHolder holds the layoutInflater

        Button edit = view.findViewById(R.id.itemBtnEdit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context.getApplicationContext(), RestaurantAddAnItemActivity.class);
                context.startActivity(i);
            }
        });

        Button order = view.findViewById(R.id.itemBtnAddToCart);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context.getApplicationContext(), Cart.class);
                context.startActivity(i);
            }
        });
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        HashMap<String, String> foodItem = foods.get(position);
        int foodId = Integer.parseInt(foodItem.get("food_id"));
        String name = foodItem.get("food_name");
        String regularPrice = foodItem.get("food_regular_price");
        String discountPrice = foodItem.get("food_discounted_price");

        ((ViewHolder)holder).foodName.setText(name);
        ((ViewHolder)holder).discountedPrice.setText("$ "+discountPrice);
        ((ViewHolder)holder).regularPrice.setText("$ "+regularPrice);
        if(isRestaurant){
            ((ViewHolder)holder).btnAddToCart.setVisibility(GONE);
        }else{
            ((ViewHolder)holder).btnEdit.setVisibility(GONE);
            ((ViewHolder)holder).btnDelete.setVisibility(GONE);
        }


    }


    @Override
    public int getItemCount() {
        return foods.size() ;
    }
}
