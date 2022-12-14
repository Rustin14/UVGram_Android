package com.example.uvgram.Models.CreatePostResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostFile {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("encoding")
    @Expose
    private String encoding;
    @SerializedName("tempFilePath")
    @Expose
    private String tempFilePath;
    @SerializedName("truncated")
    @Expose
    private Boolean truncated;
    @SerializedName("mimetype")
    @Expose
    private String mimetype;
    @SerializedName("md5")
    @Expose
    private String md5;
    @SerializedName("filename")
    @Expose
    private String filename;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getTempFilePath() {
        return tempFilePath;
    }

    public void setTempFilePath(String tempFilePath) {
        this.tempFilePath = tempFilePath;
    }

    public Boolean getTruncated() {
        return truncated;
    }

    public void setTruncated(Boolean truncated) {
        this.truncated = truncated;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
