package com.example.uvgram.Models.LikeResponses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostLikesResponse {

    @SerializedName("message")
    List<LikedBy> message;

    public List<LikedBy> getMessage() {
        return message;
    }

    public void setMessage(List<LikedBy> message) {
        this.message = message;
    }
}
