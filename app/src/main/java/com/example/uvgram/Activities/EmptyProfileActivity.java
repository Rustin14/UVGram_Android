package com.example.uvgram.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uvgram.R;

public class EmptyProfileActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_profile);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String signedInUsername = sharedPreferences.getString("USERNAME", null);
    }


}