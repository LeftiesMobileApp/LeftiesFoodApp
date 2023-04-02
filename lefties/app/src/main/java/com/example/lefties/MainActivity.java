package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbh = new DBHelper(this);

        EditText userEmail = findViewById(R.id.userEmail);
        EditText userPass = findViewById(R.id.userPass);
        Button btnLogin = findViewById(R.id.loginBtn);
        Button btnSignUp = findViewById(R.id.btnGoSignUp);
//        CheckBox checkBoxRestaurant = findViewById(R.id.checkboxRestaurant);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Raiyan-Temporary Bypass
//                if (checkBoxRestaurant.isChecked()){
//                    goToRestoHome();
//                }else{
//                    goToCustomerHome();
//                }

                Cursor c = dbh.viewDataAccount(userEmail.getText().toString(), userPass.getText().toString());
                StringBuilder str = new StringBuilder();
                if(c.getCount() > 0){
                    if (c.moveToFirst()){
                        String accountType = c.getString(2);
                        if(accountType.equals("Customer")){
                            Toast.makeText(MainActivity.this, "Welcome " + c.getString(1), Toast.LENGTH_SHORT).show();
                            goToCustomerHome()
;                        } else {
                            Toast.makeText(MainActivity.this, "Welcome " + c.getString(1), Toast.LENGTH_SHORT).show();
                            goToRestoHome();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter valid a Email or Password.", Toast.LENGTH_SHORT).show();
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
        Intent i = new Intent(getApplicationContext(),RestaurantHomeActivity.class);
        startActivity(i);
    }

    public void goToSignUpPage() {
        Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(i);
    }

    public void goToCart()
    {
        Intent i = new Intent(getApplicationContext(),Cart.class);
        startActivity(i);
    }

    public void goToCustomerHome()
    {
        Intent i = new Intent(getApplicationContext(),CustomerPage.class);
        startActivity(i);
    }
}