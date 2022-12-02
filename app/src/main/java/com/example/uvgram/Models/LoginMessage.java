package com.example.uvgram.Models;

import com.google.gson.annotations.SerializedName;

public class LoginMessage {

    @SerializedName("refreshToken")
    String refreshToken;

    @SerializedName("accessToken")
    String accessToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
