package com.example.uvgram.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.uvgram.R;

public class FinishCreatePostActivity extends AppCompatActivity {

    ImageView croppedImageView;
    String croppedImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_create_post);

        croppedImageView = findViewById(R.id.croppedImageView);
        croppedImageUri = (String) getIntent().getSerializableExtra("DESTINATION_URI");

        Glide.with(this).load(croppedImageUri).centerCrop().into(croppedImageView);
    }
}