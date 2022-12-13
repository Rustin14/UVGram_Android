package com.example.uvgram.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uvgram.R;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private Context context;
    private List<String> images;
    protected PhotoListener photoListener;

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GalleryViewHolder(
                LayoutInflater.from(context).inflate(R.layout.gallery_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        String image = images.get(position);
        Glide.with(context).load(image).into(holder.image);
        holder.itemView.setOnClickListener(view -> {
            photoListener.onPhotoClick(image);
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.galleryImage);
        }
    }

    public interface PhotoListener {
        void onPhotoClick(String path);
    }



}
