package com.example.uvgram.Models.EditProfileResponses;

import com.google.gson.annotations.SerializedName;

public class EditProfileResponse {

     @SerializedName("message")
     IsUpdated isUpdated;

     public IsUpdated getIsUpdated() {
          return isUpdated;
     }

     public void setIsUpdated(IsUpdated isUpdated) {
          this.isUpdated = isUpdated;
     }
}
