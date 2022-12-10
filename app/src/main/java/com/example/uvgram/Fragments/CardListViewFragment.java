package com.example.uvgram.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uvgram.Activities.PublicationDetailActivity;
import com.example.uvgram.Adapters.PostAdapter;
import com.example.uvgram.Models.LikeResponses.LikedBy;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.CommentsViewModel;
import com.example.uvgram.ViewModel.CommentsViewModelFactory;
import com.example.uvgram.ViewModel.HomepageViewModel;
import com.example.uvgram.ViewModel.HomepageViewModelFactory;
import com.example.uvgram.ViewModel.LikesViewModel;
import com.example.uvgram.ViewModel.LikesViewModelFactory;

import java.util.List;

public class CardListViewFragment extends Fragment {

    String username;
    private HomepageViewModel viewModel;
    private LikesViewModel likesViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        username = preferences.getString("USERNAME", null);

        RecyclerView recyclerView = getView().findViewById(R.id.cardsListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        PostAdapter adapter = new PostAdapter();

        likesViewModel = new ViewModelProvider(this,
                new LikesViewModelFactory(getActivity().getApplication()))
                .get(LikesViewModel.class);

        adapter.setLikesViewModel(likesViewModel);


        adapter.setOnItemClickListener(post -> {
            Intent myIntent = new Intent(getContext(), PublicationDetailActivity.class);
            myIntent.putExtra("POST", post);
            myIntent.putExtra("USERNAME", username);
            startActivity(myIntent);
        });

        adapter.setCommentOnItemClickListener(post -> {
            Intent myIntent = new Intent(getContext(), PublicationDetailActivity.class);
            myIntent.putExtra("POST", post);
            myIntent.putExtra("USERNAME", username);
            myIntent.putExtra("CONFIG", 1);
            startActivity(myIntent);
        });

        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this,
                new HomepageViewModelFactory(getActivity().getApplication()))
                .get(HomepageViewModel.class);
        viewModel.getPostsList(username).observe(getViewLifecycleOwner(),
                posts -> {
                    adapter.setPostList(posts);
                    CommentsViewModel commentsViewModel = new ViewModelProvider(this,
                    new CommentsViewModelFactory(getActivity().getApplication())).get(CommentsViewModel.class);

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_list_view, container, false);
    }

    public boolean userLikedPost(List<LikedBy> likesDetails, String username) {
        boolean likesPost = false;
        for (int i = 0; i < likesDetails.size(); i++) {
            if (likesDetails.get(i).getUsername() == username) {
                likesPost = true;
            }
        }
        return likesPost;
    }



}