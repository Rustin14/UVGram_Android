package com.example.uvgram.Models.LikeResponses;

import com.google.gson.annotations.SerializedName;

public class LikedBy {

    @SerializedName("name")
    String name;
    @SerializedName("username")
    String username;
    @SerializedName("isFollowed")
    boolean isFollowed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }

}
