package com.anushka.hirehand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

public class JobSeekerActivity extends AppCompatActivity {
    SQLiteDatabase db;
    SharedPreferences sharedPreferences;
    private String [] companyNames = {}, jobPositions = {}, userIds = {};
    public void viewDB() {
        try {
            Cursor resultSet = db.rawQuery("Select * from Jobs",null);
            companyNames = new String[resultSet.getCount()];
            jobPositions = new String[resultSet.getCount()];
            userIds = new String[resultSet.getCount()];

            resultSet.moveToFirst();

            for(int i = 0; i < resultSet.getCount(); i++){
                jobPositions[i]=resultSet.getString(0);
                companyNames[i]=resultSet.getString(1);
                userIds[i] = resultSet.getString(2);
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
        } catch(Exception e){
            Log.d("DB_DEBUG", e.getLocalizedMessage());
        }

        ListView lv_js = findViewById(R.id.lv_js);
        MyAdapter adapter = new MyAdapter();
        lv_js.setAdapter(adapter);
        lv_js.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(JobSeekerActivity.this, companyNames[position], Toast.LENGTH_SHORT).show());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        sharedPreferences = getSharedPreferences("HIRE_HAND_USER",MODE_PRIVATE);
        String photoURL = sharedPreferences.getString("photoURL",null);
        new MainActivity.DownloadImageFromInternet((ImageView) findViewById(R.id.userPhoto1)).execute(photoURL);
    }

    public void navigateToUserFromJS(View view) {
        Intent intent = new Intent(JobSeekerActivity.this, UserActivity.class);
        startActivity(intent);
    }

    public void sendResume(View v) throws JSONException {
       String user_name =  sharedPreferences.getString("name", null);
       String user_photo = sharedPreferences.getString("photoURL", null);
       String resume_link = sharedPreferences.getString("resumeLink", null);

        if(resume_link.length()<=0){
            Toast.makeText(this, "Please Add Resume Link first!", Toast.LENGTH_SHORT).show();
            return ;
        }

       JSONObject obj = (JSONObject) v.getTag();
       String job_position = obj.getString("job_position");

        try {
            db.execSQL("INSERT INTO Resumes VALUES('" + user_name + "','" + user_photo + "','" + job_position +"','"+ resume_link + "');");
            Toast.makeText(this, "Request Sent Successfully!", Toast.LENGTH_SHORT).show();
        } catch (SQLException e){
            Log.d("DB_ERROR",e.getLocalizedMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
            Button user_send = convertView.findViewById(R.id.user_send);

            user_cname.setText(companyNames[position]);
            user_cdes.setText(jobPositions[position]);

            JSONObject obj = new JSONObject();
            try {
                obj.put("company",companyNames[position]);
                obj.put("job_position",jobPositions[position]);
                obj.put("user",userIds[position]);
                user_send.setTag(obj);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


            return  convertView;
        }
    }
}
