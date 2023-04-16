package com.anushka.hirehand; // Varsha

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Arrays;

public class ReferrerScreen extends AppCompatActivity {
    SQLiteDatabase db;
    private ListView lv_ref;

    private String [] userNames = {}, userPhotos = {}, job_positions = {}, resumeLinks = {};
    public void viewResumes(String userId) {
        try {
            Cursor resultSet = db.rawQuery("Select * from Resumes where uploaded_by = '" + userId + "';",null);
            userNames = new String[resultSet.getCount()];
            userPhotos = new String[resultSet.getCount()];
            job_positions = new String[resultSet.getCount()];
            resumeLinks = new String[resultSet.getCount()];

            resultSet.moveToFirst();

            for(int i = 0; i < resultSet.getCount(); i++){
                userNames[i]=resultSet.getString(0);
                userPhotos[i]=resultSet.getString(1);
                job_positions[i] = resultSet.getString(2);
                resumeLinks[i] = resultSet.getString(3);
                resultSet.moveToNext();
            }
            resultSet.close();
            Log.d("DB_DEBUG", Arrays.toString(userNames));
        } catch(SQLException e){
            Log.d("DB_DEBUG", e.getLocalizedMessage());
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referrer_screen);
        db = openOrCreateDatabase("HIRE_HAND_DB", MODE_PRIVATE,null);
        SharedPreferences sharedPreferences = getSharedPreferences("HIRE_HAND_USER",MODE_PRIVATE);
        String userId = sharedPreferences.getString("id",null);

        try {
            viewResumes(userId);
        } catch(Exception e){
            Log.d("DB_DEBUG", e.getLocalizedMessage());
        }

        lv_ref = findViewById(R.id.lv_ref);
        MyAdapter adapter = new MyAdapter();
        lv_ref.setAdapter(adapter);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        ImageButton userProfileBtn = findViewById(R.id.userProfileBtn);
        String photoURL = sharedPreferences.getString("photoURL",null);
        new MainActivity.DownloadImageFromInternet((ImageView) userProfileBtn).execute(photoURL);

        userProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReferrerScreen.this, UserActivity.class);
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

    public void downloadListener(View v){
        String uri = (String) v.getTag();
        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(in);
    }

    public class MyAdapter extends BaseAdapter{
        @Override
        public int getCount()
        {
            return userPhotos.length;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.referer_screen_cv ,parent , false);
            TextView user_name = convertView.findViewById(R.id.user_name);
            TextView user_des = convertView.findViewById(R.id.user_des);
            ImageView user_img = convertView.findViewById(R.id.user_img);
            ImageButton d1 = convertView.findViewById(R.id.d1);
            d1.setTag(resumeLinks[position]);
            user_name.setText(userNames[position]);
            user_des.setText(job_positions[position]);
            new MainActivity.DownloadImageFromInternet((ImageView) user_img).execute(userPhotos[position]);
            return  convertView;
        }
    }
}