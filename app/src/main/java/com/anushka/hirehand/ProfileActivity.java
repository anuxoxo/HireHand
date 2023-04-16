package com.anushka.hirehand; // Tanushri

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        SharedPreferences sharedPreferences = getSharedPreferences("HIRE_HAND_USER",MODE_PRIVATE);
        String name = sharedPreferences.getString("name",null);
        String id = sharedPreferences.getString("id",null);
        String photoURL = sharedPreferences.getString("photoURL",null);

        new MainActivity.DownloadImageFromInternet((ImageView) findViewById(R.id.imageView6)).execute(photoURL);
        EditText yourName = findViewById(R.id.yourName);
        yourName.setText(name);
    }
}