package com.anushka.hirehand; // Tanushri

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class JobSeekerActivity extends AppCompatActivity {
// test1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}