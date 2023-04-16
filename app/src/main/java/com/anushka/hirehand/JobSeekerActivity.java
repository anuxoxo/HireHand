package com.anushka.hirehand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.View;
import android.view.View;

import android.view.ViewGroup;
import android.widget.TextView;

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
