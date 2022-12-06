package com.example.uvgram.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.uvgram.Adapters.CommentsAdapter;
import com.example.uvgram.R;

public class CommentsListViewFragment extends Fragment {

    ListView commentsListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commentsListView = getView().findViewById(R.id.commentsListView);
        commentsListView.setAdapter(new CommentsAdapter(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments_list_view, container, false);
    }
}