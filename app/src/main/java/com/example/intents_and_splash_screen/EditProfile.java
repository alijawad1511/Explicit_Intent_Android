package com.example.intents_and_splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity {

    TextView tvUsername;
    EditText etPhone, etAddress, etWebUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init();
    }

    private void init()
    {
        tvUsername = findViewById(R.id.etUsername);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etWebUrl = findViewById(R.id.etWebUrl);
    }

    public void updateProfile(View v)
    {
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String webUrl = etWebUrl.getText().toString().trim();
        boolean flag = true;

        // Here phone empty not checked. Because we will take it from Dialer during call
        if(address.isEmpty())
        {
            etAddress.setError("Phone is required");
            flag = false;
        }
        if(webUrl.isEmpty())
        {
            etWebUrl.setError("Phone is required");
            flag = false;
        }

        if(flag)
        {
            Intent intent = new Intent();
            intent.putExtra("phone",phone);
            intent.putExtra("address",address);
            intent.putExtra("webUrl",webUrl);
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    public void cancel(View v)
    {
        setResult(RESULT_CANCELED);
        finish();
    }
}