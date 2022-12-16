package com.example.uvgram.Models.FollowRequestResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestResponse {

    @SerializedName("message")
    List<Request> requestList;

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }
}
