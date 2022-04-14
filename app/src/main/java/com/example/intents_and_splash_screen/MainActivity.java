package com.example.intents_and_splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etUsername,etEmail,etPassword;
    Button btnLogin,btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidation()){
                    String username,email,password;
                    username = etUsername.getText().toString().trim();
                    email = etEmail.getText().toString().trim();
                    password = etPassword.getText().toString().trim();

                    // Create Intent
                    // Package Name used in 2nd Param to secure app
                    Intent intent = new Intent(MainActivity.this, com.example.intents_and_splash_screen.HomeActivity.class);

                    // To pass data
                    intent.putExtra("usernameKey",username);

                    startActivity(intent);
                    finish();
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private boolean checkValidation() {
        boolean flag = true;

        if(etUsername.getText().toString().trim().isEmpty()){
            etUsername.setError("Username can't be empty");
            flag = false;
        }

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
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
    }


}