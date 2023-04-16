package com.anushka.hirehand; // Tanushri

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String name, photoURL, resumeLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        sharedPreferences = getSharedPreferences("HIRE_HAND_USER",MODE_PRIVATE);
        name = sharedPreferences.getString("name",null);
        resumeLink = sharedPreferences.getString("resumeLink",null);
        photoURL = sharedPreferences.getString("photoURL",null);

        new MainActivity.DownloadImageFromInternet((ImageView) findViewById(R.id.imageView6)).execute(photoURL);
        EditText yourName = findViewById(R.id.yourName);
        yourName.setText(name);
        EditText resumeLinkET = findViewById(R.id.resumeLink);
        resumeLinkET.setText(resumeLink);
    }

    public void saveProfileData(View v){
        EditText resumeLinkET = findViewById(R.id.resumeLink);
        String resumeLinkStr = resumeLinkET.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("resumeLink", resumeLinkStr);
        editor.commit();
        Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
    }
}