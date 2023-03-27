package com.example.lefties;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.HashMap;

public class InventoryRecyclerAdapter extends RecyclerView.Adapter {
    LayoutInflater layoutInflater;
    Context context;
    String[] strArr;
    ArrayList<HashMap> foods;

    public InventoryRecyclerAdapter(@NonNull Context context, ArrayList<HashMap> foods ) {
//        super(context);
//        this.companies = companies;
        this.context = context;
        this.foods = foods;
        layoutInflater = LayoutInflater.from(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgCompanyLogo;
        TextView foodName;
        Button edit;
        Button placeholder;

        // THIS MAPS ATTRIBUTES PER ITEM
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.itemFoodName);
//            imgCompanyLogo = itemView.findViewById(R.id.imgCompanyLogo);
//            foodNameLabel = itemView.findViewById(R.id.txtCompanyName);
//            itemView.setOnClickListener(this);
            return;
        }
        @Override
        public void onClick(View v) {
//            System.out.println("clicked");
//            Intent i = new Intent(context.getApplicationContext(), Cart.class);
//                context.startActivity(i);
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

        Button order = view.findViewById(R.id.btnPlaceOrder);
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
        String name = foodItem.get("food_name");
//        int logoId = Integer.parseInt(company.get("logoId"));
        ((ViewHolder)holder).foodName.setText(name);
//        ((ViewHolder)holder).imgCompanyLogo.setImageResource(logoId);
//        viewHolder.findViewById(R.id.itemFoodName)
    }


    @Override
    public int getItemCount() {
        return foods.size() ;
    }
}
