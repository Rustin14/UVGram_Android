package com.example.uvgram.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FollowingResponse {

    @SerializedName("message")
    List<User> usersList;

    public List<User> getUsersList() {
        return usersList;
    }

    public void setMessage(List<User> usersList) {
        this.usersList = usersList;
    }
}
