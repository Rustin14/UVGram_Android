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

    public ErrorMessageResponse getLoginErrorMessage() {
        return loginErrorMessage;
    }

    public void setLoginErrorMessage(ErrorMessageResponse loginErrorMessage) {
        this.loginErrorMessage = loginErrorMessage;
    }

    ErrorMessageResponse loginErrorMessage;
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
