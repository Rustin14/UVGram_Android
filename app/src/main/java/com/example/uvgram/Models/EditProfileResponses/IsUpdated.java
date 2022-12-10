package com.example.uvgram.Models.EditProfileResponses;

import com.google.gson.annotations.SerializedName;

public class IsUpdated {

    @SerializedName("isUpdated")
    boolean isUpdated;

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }
}
