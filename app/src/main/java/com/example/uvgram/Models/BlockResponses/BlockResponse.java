package com.example.uvgram.Models.BlockResponses;

import com.google.gson.annotations.SerializedName;

public class BlockResponse {

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
