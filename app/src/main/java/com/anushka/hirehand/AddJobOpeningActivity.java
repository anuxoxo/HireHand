package com.anushka.hirehand; // Anushka

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddJobOpeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_opening);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}