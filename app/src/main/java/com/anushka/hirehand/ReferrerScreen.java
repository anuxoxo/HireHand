package com.anushka.hirehand; // Varsha

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ReferrerScreen extends AppCompatActivity {
    private ListView lv_ref;
    private String [] name = {"Your name","Your name","Your name"};
    private int [] profile_pics ={R.drawable.user,R.drawable.user,R.drawable.user};
    private String [] designation ={"Software Engineer","Software Engineer","Software Engineer"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referrer_screen);
        lv_ref = findViewById(R.id.lv_ref);
        MyAdapter adapter = new MyAdapter();
        lv_ref.setAdapter(adapter);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageButton userProfileBtn = findViewById(R.id.userProfileBtn);
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
    public class MyAdapter extends BaseAdapter{
        @Override
        public int getCount()
        {
            return profile_pics.length;
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
            user_name.setText(name[position]);
            user_des.setText(designation[position]);
            user_img.setImageResource(profile_pics[position]);
            return  convertView;
        }
    }
}