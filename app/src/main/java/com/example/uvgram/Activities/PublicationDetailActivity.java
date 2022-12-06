package com.example.uvgram.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uvgram.Adapters.CommentsAdapter;
import com.example.uvgram.R;

public class PublicationDetailActivity extends AppCompatActivity {

    ListView commentsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_detail);

        commentsListView = findViewById(R.id.commentsListView);
        commentsListView.setAdapter(new CommentsAdapter(getApplicationContext()));
    }

}