package com.example.uvgram.Models.CommentPostResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentDetails {

    @SerializedName("commet")
    @Expose
    private String commet;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("created_time")
    @Expose
    private String createdTime;

    public String getCommet() {
        return commet;
    }

    public void setCommet(String commet) {
        this.commet = commet;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}
