package com.anushka.hirehand;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

public class JobSeekerActivity extends AppCompatActivity {
    SQLiteDatabase db;
    private ListView lv_js;
    private String [] companyNames = {}, jobPositions = {};
    public void viewDB() {
        try {
            Cursor resultSet = db.rawQuery("Select * from Jobs",null);
            companyNames = new String[resultSet.getCount()];
            jobPositions = new String[resultSet.getCount()];

            resultSet.moveToFirst();

            for(int i = 0; i < resultSet.getCount(); i++){
                jobPositions[i]=resultSet.getString(0);
                companyNames[i]=resultSet.getString(1);
                resultSet.moveToNext();
            }
            resultSet.close();
        } catch(SQLException e){
            Log.d("DB_DEBUG", e.getLocalizedMessage());
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker);

        db = openOrCreateDatabase("HIRE_HAND_DB", MODE_PRIVATE,null);
        try {
        viewDB();
        }catch(Exception e){
            Log.d("DB_DEBUG", e.getLocalizedMessage());
        }

        lv_js = findViewById(R.id.lv_js);
        MyAdapter adapter = new MyAdapter();
        lv_js.setAdapter(adapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        SharedPreferences sharedPreferences = getSharedPreferences("HIRE_HAND_USER",MODE_PRIVATE);
        String photoURL = sharedPreferences.getString("photoURL",null);
        new MainActivity.DownloadImageFromInternet((ImageView) findViewById(R.id.userPhoto1)).execute(photoURL);
    }

    public void navigateToUserFromJS(View view) {
        Intent intent = new Intent(JobSeekerActivity.this, UserActivity.class);
        startActivity(intent);
    }

    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount()
        {
            return companyNames.length;
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
            convertView = getLayoutInflater().inflate(R.layout.job_seeker_cv ,parent , false);
            TextView user_cname = convertView.findViewById(R.id.user_cname);
            TextView user_cdes = convertView.findViewById(R.id.user_cdes);
            user_cname.setText(companyNames[position]);
            user_cdes.setText(jobPositions[position]);
            return  convertView;
        }
    }
}
