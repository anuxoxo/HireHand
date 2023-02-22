package com.anushka.hirehand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class JobSeekerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}