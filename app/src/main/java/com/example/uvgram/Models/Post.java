package com.example.uvgram.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.File;
import java.util.List;

public class Post {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("comments_allowed")
    @Expose
    private Boolean commentsAllowed;
    @SerializedName("likes_allowed")
    @Expose
    private Boolean likesAllowed;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("likes")
    @Expose
    private String likes;
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("files")
    @Expose
    private List<File> files = null;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
