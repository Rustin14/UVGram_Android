package com.example.uvgram.Models.GetPostCommentsResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comment {

    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("created_time")
    @Expose
    private String createdTime;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("likes")
    @Expose
    private String likes;
    @SerializedName("replies")
    @Expose
    private List<Object> replies = null;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public List<Object> getReplies() {
        return replies;
    }

    public void setReplies(List<Object> replies) {
        this.replies = replies;
    }

}
