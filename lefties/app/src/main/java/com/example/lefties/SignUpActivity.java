package com.example.lefties;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    //Database Initialize
    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Spinner Functionality
        Spinner spinner = (Spinner) findViewById(R.id.accontType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.accountType,
                android.R.layout.simple_spinner_dropdown_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Call New Database
        dbh = new DBHelper(this);
        EditText acnName = findViewById(R.id.accountName);
        Spinner acnType = findViewById(R.id.accontType);
        EditText acnEmail = findViewById(R.id.accountEmail);
        EditText acnPass = findViewById(R.id.accountPassword);
        EditText acnPhoneNumber = findViewById(R.id.accountPhoneNumber);
        EditText acnAddress = findViewById(R.id.accountAddress);
        Spinner acnCity = findViewById(R.id.accountCity);
        Button btnSignUpConfirm = findViewById(R.id.btnSignUp);

        btnSignUpConfirm.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
//            String acnTypeText = acnType.getSelectedItem().toString();
            @Override
            public void onClick(View v) {

                isInserted = dbh.addAccount(
                        acnName.getText().toString(),
                        acnType.getSelectedItem().toString(),
                        acnEmail.getText().toString(),
                        acnPass.getText().toString(),
                        acnPhoneNumber.getText().toString(),
                        acnAddress.getText().toString(),
//                        acnCity.getText().toString()
                        acnCity.getSelectedItem().toString()
                );
                if (isInserted) {
                    Toast.makeText(getApplicationContext(), "Account added successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to add account", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}