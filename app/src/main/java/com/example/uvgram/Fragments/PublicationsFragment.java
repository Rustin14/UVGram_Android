package com.example.uvgram.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.uvgram.Activities.PublicationDetailActivity;
import com.example.uvgram.Adapters.GridImagesAdapter;
import com.example.uvgram.Models.Post;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.HomepageViewModel;
import com.example.uvgram.ViewModel.HomepageViewModelFactory;

import java.util.ArrayList;

public class PublicationsFragment extends Fragment {

    GridView gridView;
    HomepageViewModel homepageViewModel;
    ArrayList<Post> userPosts = new ArrayList<>();
    TextView emptyListView;
    String username;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        gridView = getView().findViewById(R.id.photosGridView);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        emptyListView = getView().findViewById(R.id.emptyListText);

        // Bloque para decidir si se muestran los Posts del usuario que inició sesión o de otro usuario.
        String intentUsername = (String) getActivity().getIntent().getSerializableExtra("USERNAME");
        if (intentUsername == null) {
            username = preferences.getString("USERNAME", null);
        } else {
            username = intentUsername;
        }

        GridImagesAdapter imagesAdapter = new GridImagesAdapter(getContext());

        imagesAdapter.setOnItemClickListener(post -> {
            Intent myIntent = new Intent(getContext(), PublicationDetailActivity.class);
            myIntent.putExtra("POST", post);
            myIntent.putExtra("USERNAME", username);
            startActivity(myIntent);
        });

        homepageViewModel = new ViewModelProvider(this,
                new HomepageViewModelFactory(getActivity().getApplication()))
                .get(HomepageViewModel.class);

        homepageViewModel.getUser(username).observe(getViewLifecycleOwner(), userResponse -> {
            if (userResponse.getMessage().getPosts().size() > 0) {
                userPosts.addAll(userResponse.getMessage().getPosts());
                imagesAdapter.setPostList(userPosts);
                gridView.setAdapter(imagesAdapter);
            } else {
                imagesAdapter.setPostList(new ArrayList<>());
                emptyListView.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publications, container, false);
    }

}