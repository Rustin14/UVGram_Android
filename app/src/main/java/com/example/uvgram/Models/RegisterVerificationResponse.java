package com.example.uvgram.Models;

import com.google.gson.annotations.SerializedName;

public class RegisterVerificationResponse {

    @SerializedName("message")
    boolean message;

    public boolean getMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }
}
