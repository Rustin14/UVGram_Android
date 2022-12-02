package com.example.uvgram.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPostsMessage {

    @SerializedName("message")
    List<Post> postList;

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
