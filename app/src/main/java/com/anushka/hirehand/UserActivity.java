package com.anushka.hirehand; // Varsha

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

}