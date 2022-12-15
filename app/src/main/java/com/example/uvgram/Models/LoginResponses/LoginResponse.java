package com.example.uvgram.Models.LoginResponses;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("message")
    LoginMessage loginMessage;
    LoginErrorResponse loginErrorResponse;

    public LoginErrorResponse getLoginErrorResponse() {
        return loginErrorResponse;
    }

    public void setLoginErrorResponse(LoginErrorResponse loginErrorResponse) {
        this.loginErrorResponse = loginErrorResponse;
    }

    public LoginErrorMessageResponse getLoginErrorMessage() {
        return loginErrorMessage;
    }

    public void setLoginErrorMessage(LoginErrorMessageResponse loginErrorMessage) {
        this.loginErrorMessage = loginErrorMessage;
    }

    LoginErrorMessageResponse loginErrorMessage;
    int httpCode;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public LoginMessage getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(LoginMessage loginMessage) {
        this.loginMessage = loginMessage;
    }
}
