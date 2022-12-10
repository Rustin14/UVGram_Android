package com.example.uvgram.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.uvgram.Adapters.CommentsAdapter;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.CommentsViewModel;
import com.example.uvgram.ViewModel.CommentsViewModelFactory;
import com.example.uvgram.ViewModel.LikesViewModel;
import com.example.uvgram.ViewModel.LikesViewModelFactory;

public class CommentsListViewFragment extends Fragment {

    ListView commentsListView;
    CommentsViewModel commentsViewModel;
    LikesViewModel likesViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        commentsViewModel = new ViewModelProvider(this,
                new CommentsViewModelFactory(getActivity().getApplication())).get(CommentsViewModel.class);

        likesViewModel = new ViewModelProvider(this, new LikesViewModelFactory(getActivity().getApplication()))
                .get(LikesViewModel.class);

        commentsListView = getView().findViewById(R.id.commentsListView);

        CommentsAdapter commentsAdapter = new CommentsAdapter(getContext());
        commentsAdapter.setLikesViewModel(likesViewModel);

        commentsListView.setAdapter(commentsAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments_list_view, container, false);
    }
}