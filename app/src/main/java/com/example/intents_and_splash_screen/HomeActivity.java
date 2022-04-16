package com.example.intents_and_splash_screen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView tvUsername;
    Button btnLogout, btnEditProfile;
    ImageView ivCall, ivMap, ivWeb;
    final int EDIT_PROFILE_CODE = 1;
    String phone,webUrl,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
                finish();
            }
        });

        Intent intent = getIntent();
        String username = intent.getStringExtra("usernameKey");
        tvUsername.setText(username);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this, EditProfile.class);
                startActivityForResult(intent1, EDIT_PROFILE_CODE);
            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.isEmpty())
                {
                    Intent intent1 = new Intent(Intent.ACTION_DIAL);
                    startActivity(intent1);
                }
                else
                {
                    // Uri => Universe Source Identifier
                    Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                    startActivity(intent1);
                }
            }
        });

        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+address)); // geo:0,0 => start address long,lat
                startActivity(intent1);
            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
                startActivity(intent1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==EDIT_PROFILE_CODE)
        {
            if(resultCode==RESULT_OK)
            {
                phone = data.getStringExtra("phone");
                address = data.getStringExtra("address");
                webUrl = data.getStringExtra("webUrl");

                makeVisible();
            }
            else if(resultCode==RESULT_CANCELED)
            {
                Toast.makeText(this, "Profile not edited", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makeVisible(){
        ivCall.setVisibility(View.VISIBLE);
        ivMap.setVisibility(View.VISIBLE);
        ivWeb.setVisibility(View.VISIBLE);
    }

    private void init(){
        tvUsername = findViewById(R.id.tvUsername);
        btnLogout = findViewById(R.id.btnLogout);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        ivCall = findViewById(R.id.ivCall);
        ivMap = findViewById(R.id.ivMap);
        ivWeb = findViewById(R.id.ivWeb);

        ivCall.setVisibility(View.INVISIBLE);
        ivMap.setVisibility(View.INVISIBLE);
        ivWeb.setVisibility(View.INVISIBLE);
    }
}