package com.example.uvgram.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.uvgram.Activities.EditProfileActivity;
import com.example.uvgram.Activities.VisualizeFollows;
import com.example.uvgram.Adapters.ViewPagerAdapter;
import com.example.uvgram.Models.GetUserResponse;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.HomepageViewModel;
import com.example.uvgram.ViewModel.HomepageViewModelFactory;
import com.example.uvgram.VisualizeFollowersActivity;

public class VisualizeProfileFragment extends Fragment {

    ViewPager2 viewPager2;
    String username;
    ViewPagerAdapter viewPagerAdapter;
    SharedPreferences sharedPreferences;
    TextView userFullNameText;
    TextView usernameText;
    TextView presentationText;
    Button followsListButton;
    Button followersListButton;
    Button postsListButton;
    Button editProfileButton;
    Context context;
    private MutableLiveData<GetUserResponse> userResponse = new MutableLiveData<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        username = sharedPreferences.getString("USERNAME", null);

        userFullNameText = getView().findViewById(R.id.userFullNameText);
        usernameText = getView().findViewById(R.id.usernameText);
        presentationText = getView().findViewById(R.id.presentationText);
        editProfileButton = getView().findViewById(R.id.editProfileButton);
        followsListButton = getView().findViewById(R.id.followsButton);
        followersListButton = getView().findViewById(R.id.followersButton);
        postsListButton = getView().findViewById(R.id.postsButton);

        editProfileButton.setOnClickListener(view1 -> {
            Intent myIntent = new Intent(getContext(), EditProfileActivity.class);
            startActivity(myIntent);
        });

        HomepageViewModel homepageViewModel = new ViewModelProvider(this,
                new HomepageViewModelFactory(getActivity().getApplication()))
                .get(HomepageViewModel.class);

        userResponse = homepageViewModel.getSignedInUser(username);

        userResponse.observe(getViewLifecycleOwner(), response -> {
            userFullNameText.setText(response.getMessage().getName());
            usernameText.setText(response.getMessage().getUsername());
            presentationText.setText(response.getMessage().getPresentation());
            followsListButton.setText(response.getMessage().getFollowed() + "\nseguidos");
            followersListButton.setText(response.getMessage().getFollowers() + "\nseguidores");
            postsListButton.setText(response.getMessage().getPosts().size() + "\npublicaciones");
        });

        followersListButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(context , VisualizeFollowersActivity.class);
            startActivity(myIntent);
        });

        followsListButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(context, VisualizeFollows.class);
            startActivity(myIntent);
        });

        viewPager2 = getView().findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager2.setAdapter(viewPagerAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visualize_profile, container, false);
    }
}