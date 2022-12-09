package com.example.uvgram.Models.CommentPostResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentMessage {

    @SerializedName("isCreated")
    @Expose
    private Boolean isCreated;
    @SerializedName("commentDetails")
    @Expose
    private CommentDetails commentDetails;

    public Boolean getIsCreated() {
        return isCreated;
    }

    public void setIsCreated(Boolean isCreated) {
        this.isCreated = isCreated;
    }

    public CommentDetails getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(CommentDetails commentDetails) {
        this.commentDetails = commentDetails;
    }

}
