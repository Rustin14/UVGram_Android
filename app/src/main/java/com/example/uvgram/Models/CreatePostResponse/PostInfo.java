package com.example.uvgram.Models.CreatePostResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostInfo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("comments_allowed")
    @Expose
    private Boolean commentsAllowed;
    @SerializedName("likes_allowed")
    @Expose
    private Boolean likesAllowed;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("files")
    @Expose
    private List<PostFile> files = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCommentsAllowed() {
        return commentsAllowed;
    }

    public void setCommentsAllowed(Boolean commentsAllowed) {
        this.commentsAllowed = commentsAllowed;
    }

    public Boolean getLikesAllowed() {
        return likesAllowed;
    }

    public void setLikesAllowed(Boolean likesAllowed) {
        this.likesAllowed = likesAllowed;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<PostFile> getFiles() {
        return files;
    }

    public void setFiles(List<PostFile> files) {
        this.files = files;
    }

}
