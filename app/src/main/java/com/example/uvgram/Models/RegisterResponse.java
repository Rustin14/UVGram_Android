package com.example.uvgram.Models;

import com.example.uvgram.Models.LoginResponses.ErrorMessageResponse;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("exist")
    String exist;

    @SerializedName("message")
    String message;
    ErrorMessageResponse errorMessage;
    int httpCode;

    public ErrorMessageResponse getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessageResponse errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

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
