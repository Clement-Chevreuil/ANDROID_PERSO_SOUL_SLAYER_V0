package com.example.android_perso_soul_slayer_v0.ACTIVITY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.android_perso_soul_slayer_v0.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_main);

        /*SharedPreferences prefs = getApplicationContext().getSharedPreferences("test1", MODE_PRIVATE);
        SharedPreferences.Editor shared = prefs.edit();
        shared.putString("test", "John Doe");
        shared.apply();*/


    }
}