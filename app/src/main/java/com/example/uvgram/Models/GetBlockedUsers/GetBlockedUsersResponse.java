package com.example.uvgram.Models.GetBlockedUsers;

import com.example.uvgram.Models.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBlockedUsersResponse {

    @SerializedName("message")
    List<User> blockedUsers;

    public List<User> getBlockedUsers() {
        return blockedUsers;
    }

    public void setBlockedUsers(List<User> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }
}
