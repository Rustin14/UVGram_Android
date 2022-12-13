package com.example.uvgram.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
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
    Button followedButton;
    LinearLayout followButtonLayout;
    LinearLayout followedButtonLayout;
    Button blockButton;
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
        //followedButton = findViewById(R.id.followedButton);
        //blockButton = findViewById(R.id.blockButton);
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

        /*blockButton.setOnClickListener(v -> {
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
        });*/

        followButton.setOnClickListener(v -> {
            if (followButton.getText().equals("Seguir")) {
                profileViewModel.followUser(username).observe(this, followResponse -> {
                    if (followResponse != null) {
                        followButton.setText("Seguido");
                        followButton.setBackgroundColor(getResources().getColor(R.color.uvGreen));
                        followButton.setTextColor(getResources().getColor(R.color.black));
                    } else {
                        Snackbar.make(parentLayout, "Ha ocurrido un error. Inténtelo de nuevo", Snackbar.LENGTH_LONG).show();
                    }
                });
            } else {
                profileViewModel.unfollowUser(username).observe(this, unfollowResponse -> {
                    if (unfollowResponse != null) {
                        followButton.setText("Seguir");
                        followButton.setBackgroundColor(getResources().getColor(R.color.uvBlue));
                        followButton.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        Snackbar.make(parentLayout, "Ha ocurrido un error. Inténtelo de nuevo", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });

        validateFollowState();
    }

    private void setUserInfo() {
        userFullNameText.setText(profileUser.getName());
        usernameText.setText(profileUser.getUsername());
        presentationText.setText(profileUser.getPresentation());
    }

    private void validateFollowState() {
        profileViewModel.followUser(username).observe(this, followResponse -> {
            if (followResponse.getHttpCode() == 403) {
                followButton.setText("Seguir");
                followButton.setBackgroundColor(getResources().getColor(R.color.uvBlue));
                followButton.setTextColor(getResources().getColor(R.color.white));
            } else {
                profileViewModel.unfollowUser(username);
            }
        });
    }

}