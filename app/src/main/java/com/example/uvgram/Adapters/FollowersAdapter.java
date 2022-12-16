package com.example.uvgram.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uvgram.Models.User;
import com.example.uvgram.R;

import java.util.ArrayList;
import java.util.List;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.FollowersHolder> {

    private List<User> usersList = new ArrayList<>();
    Context context;


    @NonNull
    @Override
    public FollowersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View followerView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_follower_item, parent, false);
        return new FollowersHolder(followerView);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowersHolder holder, int position) {
        User currentUser = usersList.get(position);
        holder.bind(currentUser);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void setUsersList(List<User> users) {
        this.usersList = users;
        notifyDataSetChanged();
    }

    class FollowersHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView usernameTextView;

        public FollowersHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
        }

        public void bind(final User user) {
            nameTextView.setText(user.getName());
            usernameTextView.setText(user.getUsername());
        }

    }

}
