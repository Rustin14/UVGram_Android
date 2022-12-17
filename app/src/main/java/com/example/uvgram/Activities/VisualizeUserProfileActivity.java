package com.example.uvgram.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.uvgram.Adapters.ViewPagerAdapter;
import com.example.uvgram.Models.Message;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.HomepageViewModel;
import com.example.uvgram.ViewModel.HomepageViewModelFactory;
import com.example.uvgram.ViewModel.ProfileViewModel;
import com.example.uvgram.ViewModel.ProfileViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

public class VisualizeUserProfileActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TextView userFullNameText;
    TextView usernameText;
    TextView presentationText;
    HomepageViewModel viewModel;
    ProfileViewModel profileViewModel;
    Button followButton;
    boolean isFollowed = false;
    Button unfollowButton;
    Button blockButton;
    Button followsListButton;
    Button followersListButton;
    Button postsButton;
    String username;
    Message profileUser;
    ConstraintLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize_user_profile);
        username = (String) getIntent().getSerializableExtra("USERNAME");

        userFullNameText = findViewById(R.id.userFullNameText);
        usernameText = findViewById(R.id.usernameText);
        presentationText = findViewById(R.id.presentationText);
        followButton = findViewById(R.id.followButton);
        unfollowButton = findViewById(R.id.unfollowButton);
        followsListButton = findViewById(R.id.followsButton);
        followersListButton = findViewById(R.id.followersButton);
        postsButton = findViewById(R.id.postsButton);
        blockButton = findViewById(R.id.blockButton);
        viewPager = findViewById(R.id.viewPager);
        parentLayout = findViewById(R.id.parentLayout);

        viewModel = new ViewModelProvider(this,
                new HomepageViewModelFactory(getApplication()))
                .get(HomepageViewModel.class);

        profileViewModel = new ViewModelProvider(this,
                new ProfileViewModelFactory(getApplication()))
                .get(ProfileViewModel.class);

        viewModel.getUser(username).observe(this, response -> {
            profileUser = response.getMessage();
            setUserInfo();
            viewPagerAdapter = new ViewPagerAdapter(this);
            viewPager.setAdapter(viewPagerAdapter);
        });

        blockButton.setOnClickListener(v -> {
            profileViewModel.blockUser(username).observe(this, blockResponse -> {
                if (blockResponse.getMessage().equals("you have blocked to " + username)) {
                    Snackbar.make(parentLayout, "Usuario bloqueado.", Snackbar.LENGTH_LONG).show();
                    Intent myIntent = new Intent(this, HomepageActivity.class);
                    myIntent.putExtra("USERNAME", username);
                    startActivity(myIntent);
                } else {
                    Snackbar.make(parentLayout, "Ha ocurrido un error. Inténtelo de nuevo.", Snackbar.LENGTH_LONG).show();
                }
            });
        });

        followButton.setOnClickListener(v -> {
            profileViewModel.followUser(username).observe(this, followResponse -> {
                unfollowButton.setEnabled(true);
                unfollowButton.setVisibility(View.VISIBLE);
                followButton.setEnabled(false);
                followButton.setVisibility(View.GONE);
                int followers = Integer.parseInt(profileUser.getFollowers()) + 1;
                followersListButton.setText(followers + "\nseguidores");
            });
        });

        unfollowButton.setOnClickListener(view -> {
            profileViewModel.unfollowUser(username).observe(this, unfollowResponse -> {
                if (unfollowResponse != null) {
                    followButton.setEnabled(true);
                    followButton.setVisibility(View.VISIBLE);
                    unfollowButton.setEnabled(false);
                    unfollowButton.setVisibility(View.GONE);
                    int followers = Integer.parseInt(profileUser.getFollowers()) - 1;
                    followersListButton.setText(followers + "\nseguidores");
                }
            });
        });

        followsListButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, VisualizeFollowsActivity.class);
            myIntent.putExtra("PROFILE_USER", username);
            startActivity(myIntent);
        });

        followersListButton.setOnClickListener(view -> {
            Intent myIntent = new Intent(this, VisualizeFollowersActivity.class);
            myIntent.putExtra("PROFILE_USER", username);
            startActivity(myIntent);
        });
    }

    private void setUserInfo() {
        userFullNameText.setText(profileUser.getName());
        usernameText.setText(profileUser.getUsername());
        presentationText.setText(profileUser.getPresentation());
        followersListButton.setText(profileUser.getFollowers() + "\nseguidores");
        followsListButton.setText(profileUser.getFollowed() + "\nseguidos");
        postsButton.setText(profileUser.getPosts().size() + "\npublicaciones");
        validateFollowState();
    }

    private void validateFollowState() {
        if (profileUser.isFollowed()) {
            unfollowButton.setEnabled(true);
            unfollowButton.setVisibility(View.VISIBLE);
            followButton.setEnabled(false);
            followButton.setVisibility(View.GONE);
        }
    }

}