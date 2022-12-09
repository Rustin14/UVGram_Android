package com.example.uvgram.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.uvgram.Adapters.CommentsAdapter;
import com.example.uvgram.Models.Post;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.LikesViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class PublicationDetailActivity extends AppCompatActivity {

    ListView commentsListView;
    TextView usernameTextView;
    ImageView postImageView;
    TextView descriptionTextView;
    MaterialButton sendButton;
    TextInputEditText commentEditText;
    LikesViewModel likesViewModel;
    CheckBox likeIcon;
    String username;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_detail);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        post = (Post) getIntent().getSerializableExtra("POST");
        username = preferences.getString("USERNAME", null);

        usernameTextView = findViewById(R.id.usernameText);
        descriptionTextView = findViewById(R.id.descriptionText);
        postImageView = findViewById(R.id.postImage);
        sendButton = findViewById(R.id.sendButton);
        commentEditText = findViewById(R.id.commentEdit);
        likeIcon = findViewById(R.id.likeIcon);

        if (getIntent().getSerializableExtra("CONFIG") != null) {
            commentEditText.requestFocus();
        }

        commentsListView = findViewById(R.id.commentsListView);
        commentsListView.setAdapter(new CommentsAdapter(getApplicationContext()));

        usernameTextView.setText(post.getUsername());
        Glide.with(getApplicationContext()).load(post.getFiles().get(0).getUrl()).centerCrop().into(postImageView);
        descriptionTextView.setText(post.getDescription());
    }



}