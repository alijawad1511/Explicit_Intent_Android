package com.example.intents_and_splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvUsername = findViewById(R.id.tvUsername);

        Intent intent = getIntent();

        // String username = getIntent().getStringExtra("usernameKey");
        // OR
        String username = intent.getStringExtra("usernameKey");
        tvUsername.setText(username);

    }
}