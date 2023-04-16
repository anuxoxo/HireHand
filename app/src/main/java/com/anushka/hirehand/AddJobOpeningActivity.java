package com.anushka.hirehand; // Anushka

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddJobOpeningActivity extends AppCompatActivity {
    SQLiteDatabase db;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_opening);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        db = openOrCreateDatabase("HIRE_HAND_DB", MODE_PRIVATE,null);
    }

    public void addJob(View v){
        EditText companyName = findViewById(R.id.companyName);
        EditText jobPosition = findViewById(R.id.jobPosition);
        String companyNameStr = companyName.getText().toString();
        String jobPositionStr = jobPosition.getText().toString();

        sharedPreferences = getSharedPreferences("HIRE_HAND_USER", MODE_PRIVATE);
        String userId = sharedPreferences.getString("id",null);
        try {
            db.execSQL("INSERT INTO Jobs VALUES('" + jobPositionStr + "','" + companyNameStr + "','" + userId + "');");
            Toast.makeText(this, "Added Successfully!", Toast.LENGTH_SHORT).show();
        } catch (SQLException e){
            Log.d("DB_ERROR",e.getLocalizedMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}