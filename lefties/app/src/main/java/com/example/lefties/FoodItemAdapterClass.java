package com.example.lefties;

import static android.view.View.GONE;



import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
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



public class FoodItemAdapterClass extends RecyclerView.Adapter {
    LayoutInflater layoutInflater;
    Context context;
    String contextClass;
    String[] strArr;
    ArrayList<HashMap> foods;
    DBHelper dbh;
    long acctId;
//    ConstraintSet.Layout rowAdminBtns;


    public FoodItemAdapterClass(@NonNull Context context, ArrayList<HashMap> foods, long acctId) {
        this.context = context;
        this.foods = foods;
        layoutInflater = LayoutInflater.from(context);
        dbh = new DBHelper(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgCompanyLogo;
        TextView foodName;
        TextView restaurantName;
        TextView discountedPrice;
        TextView regularPrice;
        ImageView foodImg;
        Button btnEdit;
        Button btnDelete;
        Button btnAddToCart;
        Button btnGoToRestaurant;



        // THIS MAPS ATTRIBUTES PER ITEM
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.itemFoodName);
            discountedPrice = itemView.findViewById(R.id.itemDiscountedPrice);
            regularPrice = itemView.findViewById(R.id.itemRegularPrice);
            regularPrice.setPaintFlags(regularPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            foodImg = itemView.findViewById(R.id.imgFood);
            restaurantName = itemView.findViewById(R.id.itemRestaurantName);
            btnDelete = itemView.findViewById(R.id.itemBtnDelete);
            btnEdit= itemView.findViewById(R.id.itemBtnEdit);
            btnGoToRestaurant = itemView.findViewById(R.id.itemBtnGoToRestaurant);
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
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        HashMap<String, String> foodItem = foods.get(position);
        String foodIdString = foodItem.get("food_id");
        long restaurantId = Long.parseLong(foodItem.get("account_id"));
        int foodId = Integer.parseInt(foodIdString);
        String name = foodItem.get("food_name");
        String regularPrice = foodItem.get("food_regular_price");
        String discountPrice = foodItem.get("food_discounted_price");
        String restaurantNameString = foodItem.get("restaurant_name");

        ((ViewHolder)holder).foodName.setText(name);
        ((ViewHolder)holder).restaurantName.setText(restaurantNameString);

        ((ViewHolder)holder).discountedPrice.setText("$ "+discountPrice);
        ((ViewHolder)holder).regularPrice.setText("$ "+regularPrice);

        // get image from drawable with id name
        int resID = context.getResources().getIdentifier("food_" + foodIdString , "drawable", context.getPackageName());
        ((ViewHolder)holder).foodImg.setImageResource(resID);

        contextClass = context.getClass().getSimpleName();
        if(contextClass.equals("RestaurantHomeActivity")){
            ((ViewHolder)holder).btnAddToCart.setVisibility(GONE);
            ((ViewHolder)holder).btnGoToRestaurant.setVisibility(GONE);

        }else{
            ((ViewHolder)holder).btnEdit.setVisibility(GONE);
            ((ViewHolder)holder).btnDelete.setVisibility(GONE);
            if(contextClass.equals("CustomerHomeActivity")){
                ((ViewHolder)holder).btnAddToCart.setVisibility(GONE);
            }else{
                ((ViewHolder)holder).btnGoToRestaurant.setVisibility(GONE);
            }
        }

        ((ViewHolder)holder).btnGoToRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustomerDisplayRestaurantActivity.class);
                intent.putExtra("acctId", acctId);
                intent.putExtra("restaurantId", restaurantId);
                intent.putExtra("restaurantName", restaurantNameString);
                context.startActivity(intent);
            }
        });

        ((ViewHolder)holder).btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CartActivity.class);
                context.startActivity(intent);
            }
        });

        ((ViewHolder)holder).btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("delete me");
                Boolean isDeleted = dbh.deleteRecFood(foodId);
                System.out.println(isDeleted);
                Intent i = new Intent(context, RestaurantHomeActivity.class);
                context.startActivity(i);
            }
        });

    }


    public void createOrder(){
        // find all orders related to user Id
    }
    public void addToCart(){
        // create an order if none

    }


    @Override
    public int getItemCount() {
        return foods.size() ;
    }
}
