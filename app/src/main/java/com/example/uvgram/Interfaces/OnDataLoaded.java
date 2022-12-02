package com.example.uvgram.Interfaces;

import com.example.uvgram.Models.Message;
import com.example.uvgram.Models.User;

import java.util.ArrayList;

public interface OnDataLoaded {

    public void onDataLoaded(Message user);

    public void onFollowsLoaded(ArrayList<User> follows);

    public void onUserLoaded(Message signedInUser);

}
