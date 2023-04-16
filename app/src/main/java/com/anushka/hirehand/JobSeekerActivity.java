package com.anushka.hirehand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.View;
import android.view.View;

import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URI;

public class JobSeekerActivity extends AppCompatActivity {

    private ListView lv_js;

    private String [] cname = {"Company name","Company name","Company name"};

    Button b1,b2,b3;

    private String [] designation ={"Job Position : Software Engineer","Job Position : Software Engineer","Job Position : Software Engineer"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker);

        lv_js = findViewById(R.id.lv_js);
        MyAdapter adapter = new MyAdapter();
        lv_js.setAdapter(adapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        SharedPreferences sharedPreferences = getSharedPreferences("HIRE_HAND_USER",MODE_PRIVATE);
        String name = sharedPreferences.getString("name",null);
        String id = sharedPreferences.getString("id",null);
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
            return cname.length;
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
            user_cname.setText(cname[position]);
            user_cdes.setText(designation[position]);
            return  convertView;
        }
    }
}
