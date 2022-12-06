package com.example.uvgram.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uvgram.R;

public class CommentsAdapter extends BaseAdapter {

    TextView usernameText;
    TextView commentText;
    LayoutInflater inflater;

    public CommentsAdapter(Context context) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.fragment_comment, null);
        usernameText = view.findViewById(R.id.usernameText);
        commentText = view.findViewById(R.id.commentText);
        usernameText.setText("gabofl");
        commentText.setText("Hola, cómo estás");
        return view;
    }
}
