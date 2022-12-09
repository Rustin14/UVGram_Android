package com.example.uvgram.Models.FollowResponses;

import com.google.gson.annotations.SerializedName;

public class FollowResponse {

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
