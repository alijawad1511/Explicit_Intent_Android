package com.example.intents_and_splash_screen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etEmail,etPassword;
    Button btnLogin,btnSignup;

    // Activities Request Codes
    final int SIGNUP_ACTIVITY_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidation()){
                    String email,password;
                    email = etEmail.getText().toString().trim();
                    password = etPassword.getText().toString().trim();

                    User user = checkUser(email,password);

                    if(user!=null)
                    {
                        // Create Intent
                        // Package Name used in 2nd Param to secure app
                        Intent intent = new Intent(MainActivity.this, com.example.intents_and_splash_screen.HomeActivity.class);
                        intent.putExtra("usernameKey",user.getUsername());
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start an Activity to get data from that and through back to prev activity which called it
                Intent intent = new Intent(MainActivity.this, com.example.intents_and_splash_screen.Signup.class);

                // Request Code => Unique Code for an activity to be opened next
                startActivityForResult(intent,SIGNUP_ACTIVITY_CODE);
            }
        });
    }

    private User checkUser(String email, String password) {
        for(User user:Data.users)
        {
            if(user.getEmail().equals(email) && user.getPassword().equals(password))
            {
                return user;
            }
        }

        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode)
        {
            case(SIGNUP_ACTIVITY_CODE):
                if(resultCode==RESULT_OK)
                {
                    User user = new User(
                            data.getStringExtra("usernameKey"),
                            data.getStringExtra("emailKey"),
                            data.getStringExtra("passwordKey"));
                    Data.addUser(user);
                    Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
                }
                else if(resultCode==RESULT_CANCELED)
                {
                    Toast.makeText(this, "Signup is required before Login", Toast.LENGTH_SHORT).show();
                }
        }

    }

    private boolean checkValidation() {
        boolean flag = true;

        if(etEmail.getText().toString().trim().isEmpty()){
            etEmail.setError("Email can't be empty");
            flag = false;
        }

        if(etPassword.getText().toString().trim().isEmpty()){
            etPassword.setError("Password can't be empty");
            flag = false;
        }

        return flag;

    }

    private void init(){
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
    }


}