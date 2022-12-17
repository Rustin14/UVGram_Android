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
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class BlockedUsersAdapter extends RecyclerView.Adapter<BlockedUsersAdapter.BlockedUsersHolder> {

    private List<User> usersList = new ArrayList<>();
    OnItemClickListener onItemClickListener;
    Context context;

    @NonNull
    @Override
    public BlockedUsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View blockedUserView = LayoutInflater.from(context)
                .inflate(R.layout.blocked_user_item, parent, false);
        return new BlockedUsersHolder(blockedUserView);
    }

    @Override
    public void onBindViewHolder(@NonNull BlockedUsersHolder holder, int position) {
        User blockedUser = usersList.get(position);
        holder.bind(blockedUser, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class BlockedUsersHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView usernameTextView;
        MaterialButton unblockButton;

        public BlockedUsersHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            unblockButton = itemView.findViewById(R.id.unblockButton);
        }

        public void bind(final User user, OnItemClickListener listener) {
            nameTextView.setText(user.getName());
            usernameTextView.setText(user.getUsername());
            unblockButton.setOnClickListener(view -> {
                listener.onItemClick(user);
            });
        }

    }

}
