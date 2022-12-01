package com.example.uvgram.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uvgram.Models.Post;
import com.example.uvgram.R;

import java.util.ArrayList;

public class CardListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Post> postList;
    LayoutInflater inflater;

    public CardListViewAdapter(Context context, ArrayList<Post> postList) {
        this.context = context;
        this.postList = postList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View cardView, ViewGroup viewGroup) {
        cardView = inflater.inflate(R.layout.fragment_post_card, null);
        ImageView postImage = cardView.findViewById(R.id.postCardImage);
        TextView usernameText = cardView.findViewById(R.id.usernameText);
        TextView descriptionText = cardView.findViewById(R.id.descriptionText);
        postImage.setImageResource(R.drawable.pxl_20221001_231154257);
        descriptionText.setText(postList.get(position).getDescription());
        return cardView;
    }
}
