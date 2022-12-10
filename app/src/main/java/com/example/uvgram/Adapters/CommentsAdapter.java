package com.example.uvgram.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.uvgram.Models.GetCommentsResponses.Comment;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.LikesViewModel;

import java.util.List;

public class CommentsAdapter extends BaseAdapter {

    TextView usernameText;
    TextView commentText;
    LayoutInflater inflater;
    List<Comment> commentList;
    CheckBox likeIcon;
    LikesViewModel likesViewModel;

    public CommentsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    public void setLikesViewModel(LikesViewModel likesViewModel) {
        this.likesViewModel = likesViewModel;
    }


    @Override
    public int getCount() {
        return commentList.size();
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
        likeIcon = view.findViewById(R.id.likeIcon);
        usernameText.setText(commentList.get(i).getUsername());
        commentText.setText(commentList.get(i).getComment());
        likeIcon.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (!isChecked) {
                likesViewModel.dislikeComment(commentList.get(i).getUuid());
            } else {
                likesViewModel.likeComment(commentList.get(i).getUuid());
            }
        });
        return view;
    }
}
