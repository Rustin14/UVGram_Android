package com.example.uvgram.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.uvgram.Adapters.CommentsAdapter;
import com.example.uvgram.Models.Post;
import com.example.uvgram.R;

public class PublicationDetailActivity extends AppCompatActivity {

    ListView commentsListView;
    TextView usernameTextView;
    ImageView postImageView;
    TextView descriptionTextView;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_detail);

        post = (Post) getIntent().getSerializableExtra("POST");

        usernameTextView = findViewById(R.id.usernameText);
        descriptionTextView = findViewById(R.id.descriptionText);
        postImageView = findViewById(R.id.postImage);

        commentsListView = findViewById(R.id.commentsListView);
        commentsListView.setAdapter(new CommentsAdapter(getApplicationContext()));

        usernameTextView.setText(post.getUsername());
        Glide.with(getApplicationContext()).load(post.getFiles().get(0).getUrl()).centerCrop().into(postImageView);
        descriptionTextView.setText(post.getDescription());

    }

}