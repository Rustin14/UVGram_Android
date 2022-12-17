package com.example.uvgram.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uvgram.Adapters.BlockedUsersAdapter;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.ProfileViewModel;
import com.example.uvgram.ViewModel.ProfileViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

public class BlockedUsersActivity extends AppCompatActivity {

    private ProfileViewModel profileViewModel;
    LinearLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocked_users);
        TextView emptyListText = findViewById(R.id.emptyListText);
        parentLayout = findViewById(R.id.parentLayout);

        RecyclerView recyclerView = findViewById(R.id.blocksRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setHasFixedSize(true);

        BlockedUsersAdapter adapter = new BlockedUsersAdapter();

        profileViewModel = new ViewModelProvider(this,
                new ProfileViewModelFactory(getApplication()))
                .get(ProfileViewModel.class);

        adapter.setOnItemClickListener(user -> {
            profileViewModel.unblockUser(user.getUsername()).observe(this, unblockResponse -> {
                if (unblockResponse.getMessage().equals("you have unblocked to " + user.getUsername())) {
                    Snackbar.make(parentLayout, "Has desbloqueado a " + user.getUsername() + ".", Snackbar.LENGTH_LONG).show();
                    profileViewModel.getBlockedUsers().observe(this, getBlockedUsersResponse -> {
                        if (getBlockedUsersResponse.getBlockedUsers().size() == 0) {
                            emptyListText.setVisibility(View.VISIBLE);
                        } else {
                            adapter.setUsersList(getBlockedUsersResponse.getBlockedUsers());
                        }
                    });
                } else {
                    Snackbar.make(parentLayout, "Ocurrió un error, intente más tarde.", Snackbar.LENGTH_LONG).show();
                }
            });
        });

        recyclerView.setAdapter(adapter);

        profileViewModel.getBlockedUsers().observe(this, getBlockedUsersResponse -> {
            if (getBlockedUsersResponse.getBlockedUsers().size() == 0) {
                emptyListText.setVisibility(View.VISIBLE);
            } else {
                adapter.setUsersList(getBlockedUsersResponse.getBlockedUsers());
            }
        });
    }
}