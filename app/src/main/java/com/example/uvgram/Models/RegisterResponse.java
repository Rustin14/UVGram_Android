package com.example.uvgram.Models;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("exist")
    String exist;

    @SerializedName("message")
    String message;

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
