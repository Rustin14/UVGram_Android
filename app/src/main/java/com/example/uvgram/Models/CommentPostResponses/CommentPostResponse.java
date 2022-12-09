package com.example.uvgram.Models.CommentPostResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentPostResponse {

    @SerializedName("message")
    @Expose
    private CommentMessage commentMessage;

    public CommentMessage getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(CommentMessage commentMessage) {
        this.commentMessage = commentMessage;
    }
}
