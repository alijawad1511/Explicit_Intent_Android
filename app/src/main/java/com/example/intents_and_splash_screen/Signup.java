package com.example.intents_and_splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    EditText etUsername, etEmail, etPassword, etConfirmPassword;
    Button btnSignup, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if(!username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty())
                {
                    if(password.equals(confirmPassword))
                    {
                        // Not need of where we come from and where to go from this activity
                        Intent intent = new Intent();
                        intent.putExtra("usernameKey",username);
                        intent.putExtra("emailKey",email);
                        intent.putExtra("passwordKey",password);

                        // Result Code => We are sending back result or not (OK or CANCEL etc.)
                        setResult(RESULT_OK,intent);
                        finish();       // Finish this activity after move back to prev activity

                    }else{
                        etPassword.setError("Password and Confirm Password don't match");
                    }
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }


    private void init(){
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignup = findViewById(R.id.btnSignup);
        btnCancel = findViewById(R.id.btnCancel);
    }
}