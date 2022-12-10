package com.example.uvgram.Models.FollowResponses;

import com.google.gson.annotations.SerializedName;

public class UnfollowResponse {

    @SerializedName("message")
    String message;
    int httpCode;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
