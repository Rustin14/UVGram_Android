package com.example.uvgram.Models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("message")
    LoginMessage loginMessage;

    public LoginMessage getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(LoginMessage loginMessage) {
        this.loginMessage = loginMessage;
    }
}
