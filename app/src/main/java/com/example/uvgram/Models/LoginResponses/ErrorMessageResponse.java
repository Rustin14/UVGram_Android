package com.example.uvgram.Models.LoginResponses;

import com.google.gson.annotations.SerializedName;

public class ErrorMessageResponse {

    @SerializedName("message")
    String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
