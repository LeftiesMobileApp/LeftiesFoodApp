package com.example.lefties;

import static android.view.View.GONE;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderItemAdapterClass extends RecyclerView.Adapter {
    LayoutInflater layoutInflater;
    Context context;
    long acctId;
    String contextClass;
    ArrayList<HashMap> orderDetails;
    DBHelper dbh;

    public OrderItemAdapterClass(@NonNull Context context, ArrayList<HashMap> orderDetails){
        this.context = context;
        this.orderDetails = orderDetails;
        layoutInflater = LayoutInflater.from(context);
        //Log.i("acct id long is ", ""+acctId);
        dbh = new DBHelper(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView orderDate;
        TextView orderStatus;
        TextView restName;
        TextView orderTotal;
        TextView accountName;
        TextView accountAddress;
        TextView orderId;
        Button btnViewOrderItems;
        Button btnOrderRemind;
        Button btnOrderComplete;
        Button btnOrderCancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDate = itemView.findViewById(R.id.textOrderDateTime);
            orderStatus = itemView.findViewById(R.id.txtorderStatus);
            restName = itemView.findViewById(R.id.textOrderRestaurantName);
            orderId = itemView.findViewById(R.id.textOrderId);
            orderTotal = itemView.findViewById(R.id.textOrderTotal);
            accountAddress = itemView.findViewById(R.id.textOrderAddress);
            accountName = itemView.findViewById(R.id.textOrderCustomerName);
            btnOrderCancel = itemView.findViewById(R.id.btnOrderCancel);
            btnOrderComplete = itemView.findViewById(R.id.btnOrderComplete);
            btnOrderRemind = itemView.findViewById(R.id.btnOrderRemind);
            btnViewOrderItems = itemView.findViewById(R.id.btnViewOrderDetails);
            return;
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  layoutInflater.inflate(R.layout.recycler_order_details, parent, false);
        OrderItemAdapterClass.ViewHolder viewHolder = new OrderItemAdapterClass.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HashMap<String, String> orders = orderDetails.get(position);
        //String restaurantNameString = orders.get("restaurant_name");
        String orderId = orders.get("order_id");
        String odate = orders.get("order_date");
        String ostatus = orders.get("order_status");
        String account_name = orders.get("customer_name");
        String account_address = orders.get("customer_address");
        String orderTotal = orders.get("total_order");

        ((ViewHolder)holder).orderId.setText(orderId);
        ((ViewHolder)holder).orderDate.setText(odate);
        ((ViewHolder)holder).orderStatus.setText(ostatus);
        ((ViewHolder)holder).orderTotal.setText(String.format("$ %.2f", Double.parseDouble(orderTotal)));

        //((ViewHolder)holder).restName.setText(restaurantNameString);
        ((ViewHolder)holder).accountAddress.setText(account_address);
        ((ViewHolder)holder).accountName.setText(account_name);

        showCorrectBtns(holder);

    }

    //to display "view order items" only in customer;
    //"cancel, complete, remind order" only in restaurant
    public void showCorrectBtns(RecyclerView.ViewHolder holder){
        contextClass = context.getClass().getSimpleName();
        if(contextClass.equals("RestaurantHomeActivity")){
            ((ViewHolder)holder).btnViewOrderItems.setVisibility(GONE);
        }
        else if(contextClass.equals("CustomerHomeActivity")){
            ((ViewHolder)holder).btnOrderCancel.setVisibility(GONE);
            ((ViewHolder)holder).btnOrderRemind.setVisibility(GONE);
            ((ViewHolder)holder).btnOrderComplete.setVisibility(GONE);
        }
    }

    @Override
    public int getItemCount() {
       //return 2;
       return orderDetails.size();
    }
}