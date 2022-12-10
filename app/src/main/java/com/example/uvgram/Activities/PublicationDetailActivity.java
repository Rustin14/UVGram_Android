package com.example.uvgram.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.uvgram.Adapters.CommentsAdapter;
import com.example.uvgram.Models.Post;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.CommentsViewModel;
import com.example.uvgram.ViewModel.CommentsViewModelFactory;
import com.example.uvgram.ViewModel.LikesViewModel;
import com.example.uvgram.ViewModel.LikesViewModelFactory;
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
    MaterialButton commentButton;
    String username;
    Post post;
    CommentsViewModel commentsViewModel;

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
        commentButton = findViewById(R.id.commentButton);
        likeIcon = findViewById(R.id.likeIcon);

        CommentsAdapter adapter = new CommentsAdapter(getApplicationContext());

        if (getIntent().getSerializableExtra("CONFIG") != null) {
            focusCommentInput();
        }
        commentsViewModel = new ViewModelProvider(this,
                new CommentsViewModelFactory(getApplication())).get(CommentsViewModel.class);

        likesViewModel = new ViewModelProvider(this, new LikesViewModelFactory(getApplication()))
                .get(LikesViewModel.class);

        commentsViewModel.getComments(post.getUuid()).observe(this, getCommentsResponse -> {
            commentsListView = findViewById(R.id.commentsListView);
            adapter.setCommentList(getCommentsResponse.getCommentList());
            adapter.setLikesViewModel(likesViewModel);
            commentsListView.setAdapter(adapter);
        });

        likeIcon.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (!isChecked) {
                likesViewModel.dislikeComment(post.getUuid());
            } else {
                likesViewModel.likeComment(post.getUuid());
            }
        });

        commentButton.setOnClickListener(view -> {
            focusCommentInput();
        });

        sendButton.setOnClickListener(view -> {
            if (!String.valueOf(commentEditText.getText()).isEmpty()) {
                commentsViewModel.postComment(String.valueOf(commentEditText.getText()), post.getUuid())
                        .observe(this, commentPostResponse -> {
                            commentsViewModel.getComments(post.getUuid()).observe(this, getCommentsResponse -> {
                                adapter.setCommentList(getCommentsResponse.getCommentList());
                                commentEditText.setText("");
                            });
                });
            }
        });

        usernameTextView.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, VisualizeUserProfileActivity.class);
            myIntent.putExtra("USERNAME", post.getUsername());
            startActivity(myIntent);
        });

        if (post == null) {
            usernameTextView.setText(username);
        } else {
            usernameTextView.setText(post.getUsername());
        }
        Glide.with(getApplicationContext()).load(post.getFiles().get(0).getUrl()).centerCrop().into(postImageView);
        descriptionTextView.setText(post.getDescription());
    }

    private void focusCommentInput() {
        commentEditText.setFocusableInTouchMode(true);
        commentEditText.requestFocus();
        final InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(commentEditText, InputMethodManager.SHOW_IMPLICIT);
    }



}