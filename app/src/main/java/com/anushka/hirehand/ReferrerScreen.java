package com.anushka.hirehand; // Varsha

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ReferrerScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referrer_screen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageButton userProfileBtn = findViewById(R.id.userProfileBtn);
        userProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReferrerScreen.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        ImageButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReferrerScreen.this, AddJobOpeningActivity.class);
                startActivity(intent);
            }
        });
    }
}