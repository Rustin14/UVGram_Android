package com.example.uvgram.Models.GetCommentsResponses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCommentsResponse {

    @SerializedName("message")
    List<Comment> commentList;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
