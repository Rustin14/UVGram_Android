package com.example.uvgram.Models.LikeResponses;

import com.google.gson.annotations.SerializedName;

public class LikeResponse {

    @SerializedName("message")
    boolean message;

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }
}
