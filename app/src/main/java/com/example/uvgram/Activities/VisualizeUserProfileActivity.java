package com.example.uvgram.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.uvgram.Adapters.ViewPagerAdapter;
import com.example.uvgram.Models.Message;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.HomepageViewModel;
import com.example.uvgram.ViewModel.HomepageViewModelFactory;
import com.example.uvgram.ViewModel.ProfileViewModel;
import com.example.uvgram.ViewModel.ProfileViewModelFactory;
import com.example.uvgram.VisualizeFollowersActivity;
import com.google.android.material.button.MaterialButton;
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
    MaterialButton unfollowButton;
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

        isFollowed = (boolean) getIntent().getSerializableExtra("IS_FOLLOWED");
        validateFollowState();

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
                followButton.setText("Seguido");
                followButton.setBackgroundColor(getResources().getColor(R.color.uvGreen));
                followButton.setTextColor(getResources().getColor(R.color.black));
                followButton.setEnabled(false);
                unfollowButton.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_person_remove_24));
                unfollowButton.setEnabled(true);
                int followers = Integer.parseInt(profileUser.getFollowers()) + 1;
                followersListButton.setText(followers + "\nseguidores");
            });
        });

        unfollowButton.setOnClickListener(view -> {
            profileViewModel.unfollowUser(username).observe(this, unfollowResponse -> {
                if (unfollowResponse != null) {
                    followButton.setText("Seguir");
                    followButton.setBackgroundColor(getResources().getColor(R.color.uvBlue));
                    followButton.setTextColor(getResources().getColor(R.color.white));
                    followButton.setEnabled(true);
                    unfollowButton.setEnabled(false);
                    unfollowButton.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_follow_account_24));
                    int followers = Integer.parseInt(profileUser.getFollowers()) - 1;
                    followersListButton.setText(followers + "\nseguidores");
                }
            });
        });

        followsListButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, VisualizeFollows.class);
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
    }

    private void validateFollowState() {
        if (isFollowed) {
            followButton.setText("Seguido");
            followButton.setBackgroundColor(getResources().getColor(R.color.uvGreen));
            followButton.setTextColor(getResources().getColor(R.color.white));
            followButton.setEnabled(false);
            unfollowButton.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_person_remove_24));
            unfollowButton.setEnabled(true);
        }
    }

}