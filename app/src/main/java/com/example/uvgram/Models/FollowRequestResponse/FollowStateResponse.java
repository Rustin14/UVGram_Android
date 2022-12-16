package com.example.uvgram.Models.FollowRequestResponse;

import com.google.gson.annotations.SerializedName;

public class FollowStateResponse {

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
