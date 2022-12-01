package com.example.uvgram.Models;

import com.google.gson.annotations.SerializedName;

public class RegisterVerificationResponse {

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
