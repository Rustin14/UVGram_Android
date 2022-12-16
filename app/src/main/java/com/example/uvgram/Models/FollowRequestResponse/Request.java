package com.example.uvgram.Models.FollowRequestResponse;

import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("status")
    String status;
    @SerializedName("name")
    String name;
    @SerializedName("username")
    String username;
    @SerializedName("presentation")
    String presentation;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
}
