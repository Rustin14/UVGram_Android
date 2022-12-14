package com.example.uvgram.Models.CreatePostResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatePostResponse {

    @SerializedName("message")
    @Expose
    private PostResponseMessage message;

    public PostResponseMessage getMessage() {
        return message;
    }

    public void setMessage(PostResponseMessage message) {
        this.message = message;
    }

}
