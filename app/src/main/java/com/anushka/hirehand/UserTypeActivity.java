package com.anushka.hirehand; // Varsha

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class UserTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Button referrerTypeBtn = findViewById(R.id.referrerTypeBtn);
        referrerTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserTypeActivity.this, ReferrerScreen.class);
                startActivity(intent);
            }
        });
    }
}