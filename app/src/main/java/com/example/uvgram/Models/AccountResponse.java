package com.example.uvgram.Models;

import com.google.gson.annotations.SerializedName;

public class AccountResponse {

    @SerializedName("message")
    Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
