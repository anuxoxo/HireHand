package com.anushka.hirehand; // Varsha

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void navigateToProfile(View view) {
        Intent intent = new Intent(UserActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}