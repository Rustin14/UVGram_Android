package com.example.uvgram.Models.LoginResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginError {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("param")
    @Expose
    private String param;
    @SerializedName("location")
    @Expose
    private String location;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
