package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.loginBtn);
        CheckBox checkBoxRestaurant = findViewById(R.id.checkboxRestaurant);
        boolean isRestaurant = checkBoxRestaurant.isChecked();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isRestaurant) goToRestoHome();
//                goToRestoHome();
                goToCart();
            }
        });
    }

    public void goToRestoHome()
    {
        // TODO Auto-generated method stub
        Intent i = new Intent(getApplicationContext(),RestaurantHomeActivity.class);
        startActivity(i);
        setContentView(R.layout.activity_restaurant_home);
    }

    public void goToCart()
    {
        // TODO Auto-generated method stub
        Intent i = new Intent(getApplicationContext(),Cart.class);
        startActivity(i);
        setContentView(R.layout.activity_cart);
    }
}