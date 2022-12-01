package com.example.uvgram.Models;

import com.google.gson.annotations.SerializedName;

public class GetUserResponse {

    @SerializedName("message")
    Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
