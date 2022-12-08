package com.example.uvgram.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uvgram.Models.Post;
import com.example.uvgram.R;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {
    private List<Post> postList = new ArrayList<>();
    Context context;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View postView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_post_card, parent, false);
        return new PostHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post currentPost = postList.get(position);
        holder.bind(currentPost, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setPostList(List<Post> posts) {
        this.postList = posts;
        notifyDataSetChanged();
    }

    class PostHolder extends RecyclerView.ViewHolder {

        View postCardView;
        private ImageView postImage;
        private TextView usernameText;
        private TextView descriptionText;

        public PostHolder(View postCardView) {
            super(postCardView);
            this.postCardView = postCardView;
            postImage = postCardView.findViewById(R.id.postCardImage);
            usernameText = postCardView.findViewById(R.id.usernameText);
            descriptionText = postCardView.findViewById(R.id.descriptionText);
        }

        public void bind(final Post post, final OnItemClickListener listener) {
            String resourceUrl =  post.getFiles().get(0).getUrl();
            Glide.with(context).load(resourceUrl).centerCrop().into(postImage);
            usernameText.setText(post.getUsername());
            descriptionText.setText(post.getDescription());
            postCardView.setOnClickListener(v -> {
                listener.onItemClick(post);
            });
        }

    }

}
