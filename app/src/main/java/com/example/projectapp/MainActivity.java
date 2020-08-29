package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.example.projectapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        String[] logarr = {"Connecting..","Server Connected..","User Already Validated..","MacId Sent to Cloud...",""};

        //textView = findViewById(R.id.textView);
        @SuppressLint("HardwareIds")
        String ID = Settings.Secure.getString(getContentResolver(),
        Settings.Secure.ANDROID_ID);
       // textView.setText("Device ID: "+ ID);

        Handler handler = new Handler();
        handler.postDelayed(() -> {

            for (String s : logarr) {

                activityMainBinding.console.append("\n" + s);
            }

        },1000);









    }





}
