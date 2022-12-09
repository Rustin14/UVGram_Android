package com.example.uvgram.Models.LikeResponses;

import com.google.gson.annotations.SerializedName;

public class DislikeResponse {

    @SerializedName("message")
    boolean message;

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }

}
