package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class MainActivity extends AppCompatActivity {

    DatabaseHelperV1 dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbh = new DatabaseHelperV1(this);

        Button btnLogin = findViewById(R.id.loginBtn);
        Button btnSignUp = findViewById(R.id.btnSignUp);
        CheckBox checkBoxRestaurant = findViewById(R.id.checkboxRestaurant);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxRestaurant.isChecked()){
                    goToRestoHome();
                }else{
                    goToCustomerHome();
                }


            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUpPage();
            }
        });
    }

    public void goToRestoHome()
    {
        // TODO Auto-generated method stub
        Intent i = new Intent(getApplicationContext(),RestaurantHomeActivity.class);
        startActivity(i);
    }

    public void goToSignUpPage() {
        Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(i);
    }

    public void goToCart()
    {
        // TODO Auto-generated method stub
        Intent i = new Intent(getApplicationContext(),Cart.class);
        startActivity(i);

    }

    public void goToCustomerHome()
    {
        // TODO Auto-generated method stub
        Intent i = new Intent(getApplicationContext(),CustomerPage.class);
        startActivity(i);
    }
}