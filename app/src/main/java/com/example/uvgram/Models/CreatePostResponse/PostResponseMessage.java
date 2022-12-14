package com.example.uvgram.Models.CreatePostResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostResponseMessage {

    @SerializedName("isCreated")
    @Expose
    private Boolean isCreated;
    @SerializedName("postInfo")
    @Expose
    private PostInfo postInfo;

    public Boolean getIsCreated() {
        return isCreated;
    }

    public void setIsCreated(Boolean isCreated) {
        this.isCreated = isCreated;
    }

    public PostInfo getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(PostInfo postInfo) {
        this.postInfo = postInfo;
    }


}
