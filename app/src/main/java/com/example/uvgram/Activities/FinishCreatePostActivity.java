package com.example.uvgram.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.PostViewModel;
import com.example.uvgram.ViewModel.PostViewModelFactory;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class FinishCreatePostActivity extends AppCompatActivity {

    ImageView croppedImageView;
    String croppedImageUri;
    PostViewModel postViewModel;
    TextInputEditText descriptionInput;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_create_post);

        croppedImageView = findViewById(R.id.croppedImageView);
        descriptionInput = findViewById(R.id.descriptionInput);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        croppedImageUri = (String) getIntent().getSerializableExtra("DESTINATION_URI");

        postViewModel = new ViewModelProvider(this,
                new PostViewModelFactory(getApplication())).get(PostViewModel.class);

        saveButton.setOnClickListener(view -> {
            String postDescription = String.valueOf(descriptionInput.getText());
            try {
                postViewModel.createPost(croppedImageUri, postDescription,
                        true, true).observe(this, createPostResponse -> {
                    Intent myIntent = new Intent(this, HomepageActivity.class);
                    startActivity(myIntent);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Glide.with(this).load(croppedImageUri).centerCrop().into(croppedImageView);
    }
}