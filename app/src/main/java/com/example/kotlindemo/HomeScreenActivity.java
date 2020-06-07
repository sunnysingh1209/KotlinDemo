package com.example.kotlindemo;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kotlindemo.adapter.HomeActivityAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class HomeScreenActivity extends AppCompatActivity {
    RecyclerView homeRecView;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    Handler handler;
    int Seconds, Minutes, MilliSeconds;

    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        homeRecView = findViewById(R.id.homeRecView);

        data = new ArrayList<>();
        data.add("Sunny");
        data.add("Sunny");
        data.add("Sunny");
        data.add("Sunny");

        homeRecView.setLayoutManager(new LinearLayoutManager(HomeScreenActivity.this));
        homeRecView.setAdapter(new HomeActivityAdapter(HomeScreenActivity.this, data));
    }


}
