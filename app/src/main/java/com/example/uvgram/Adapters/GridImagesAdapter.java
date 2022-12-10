package com.example.uvgram.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.uvgram.Models.Post;

import java.util.List;

public class GridImagesAdapter extends BaseAdapter {

    private Context context;
    List<Post> postList;
    OnItemClickListener onItemClickListener;

    public GridImagesAdapter(Context mContext) {
        this.context = mContext;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int i) {
        return postList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        String resourceUrl = postList.get(i).getFiles().get(0).getUrl();
        Glide.with(context).load(resourceUrl).centerCrop().into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(
                new GridView.LayoutParams(
                        340,
                        340
                )
        );
        imageView.setOnClickListener(view1 -> {
            onItemClickListener.onItemClick(postList.get(i));
        });
        return imageView;
    }
}
