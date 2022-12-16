package com.example.uvgram.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uvgram.Adapters.RequestsAdapter;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.ProfileViewModel;
import com.example.uvgram.ViewModel.ProfileViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

public class FollowRequestsActivity extends AppCompatActivity {

    private ProfileViewModel profileViewModel;
    private LinearLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_requests);
        TextView emptyListText = findViewById(R.id.emptyListText);
        parentLayout = findViewById(R.id.parentLayout);

        RecyclerView recyclerView = findViewById(R.id.requestsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setHasFixedSize(true);

        RequestsAdapter requestsAdapter = new RequestsAdapter();

        profileViewModel = new ViewModelProvider(this,
                new ProfileViewModelFactory(getApplication()))
                .get(ProfileViewModel.class);

        requestsAdapter.setOnAcceptItemClickListener(request -> {
            profileViewModel.acceptRequest(request.getUsername()).observe(this, followStateResponse -> {
                if (followStateResponse.getMessage().equals("true")) {
                    Snackbar.make(parentLayout, "Solicitud aceptada.", Snackbar.LENGTH_SHORT).show();
                    profileViewModel.getPendingRequests().observe(this, requestResponse -> {
                        if (requestResponse.getRequestList().size() == 0) {
                            emptyListText.setVisibility(View.VISIBLE);
                        } else {
                            requestsAdapter.setRequestList(requestResponse.getRequestList());
                        }
                    });
                } else {
                    Snackbar.make(parentLayout, "Ha ocurrido un error.", Snackbar.LENGTH_SHORT).show();
                }
            });
        });

        requestsAdapter.setOnRejectItemClickListener(request -> {
            profileViewModel.denyRequest(request.getUsername()).observe(this, followStateResponse -> {
                if (followStateResponse.getMessage().equals("true")) {
                    Snackbar.make(parentLayout, "Solicitud rechazada.", Snackbar.LENGTH_SHORT).show();
                    profileViewModel.getPendingRequests().observe(this, requestResponse -> {
                        if (requestResponse.getRequestList().size() == 0) {
                            emptyListText.setVisibility(View.VISIBLE);
                        } else {
                            requestsAdapter.setRequestList(requestResponse.getRequestList());
                        }
                    });
                } else {
                    Snackbar.make(parentLayout, "Ha ocurrido un error.", Snackbar.LENGTH_SHORT).show();
                }
            });
        });

        recyclerView.setAdapter(requestsAdapter);

        profileViewModel.getPendingRequests().observe(this, requestResponse -> {
            if (requestResponse.getRequestList().size() == 0) {
                emptyListText.setVisibility(View.VISIBLE);
            } else {
                requestsAdapter.setRequestList(requestResponse.getRequestList());
            }
        });
    }
}