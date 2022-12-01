package com.example.uvgram.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FollowingResponse {

    @SerializedName("message")
    List<Message> message;

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }
}
