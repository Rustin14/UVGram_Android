package com.example.uvgram;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uvgram.Adapters.FollowersAdapter;
import com.example.uvgram.ViewModel.HomepageViewModel;
import com.example.uvgram.ViewModel.HomepageViewModelFactory;

public class VisualizeFollowersActivity extends AppCompatActivity {

    String username;
    private HomepageViewModel homepageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        setContentView(R.layout.activity_visualize_followers);
        TextView emptyListText = findViewById(R.id.emptyListText);

        username = (String) getIntent().getSerializableExtra("PROFILE_USER");
        if (username == null) {
            username = preferences.getString("USERNAME", null);
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setHasFixedSize(true);

        FollowersAdapter followersAdapter = new FollowersAdapter();

        homepageViewModel = new ViewModelProvider(this,
                new HomepageViewModelFactory(getApplication()))
                .get(HomepageViewModel.class);

        recyclerView.setAdapter(followersAdapter);

        homepageViewModel.getFollowersOfUsers(username).observe(this, getFollowedByResponse -> {
            if (getFollowedByResponse.getUsersList().size() == 0) {
                emptyListText.setVisibility(View.VISIBLE);
            }
            followersAdapter.setUsersList(getFollowedByResponse.getUsersList());
        });

    }
}
