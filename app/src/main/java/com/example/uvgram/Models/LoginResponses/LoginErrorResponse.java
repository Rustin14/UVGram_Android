package com.example.uvgram.Models.LoginResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginErrorResponse {

    @SerializedName("errors")
    @Expose
    private List<LoginError> errors = null;

    public List<LoginError> getErrors() {
        return errors;
    }

    public void setErrors(List<LoginError> errors) {
        this.errors = errors;
    }


}
