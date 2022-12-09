package com.example.uvgram.Repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Connection.UVGramDatabase;
import com.example.uvgram.Models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private final UVGramDatabase database;
    private final MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();
    Context context;

    public LoginRepository (UVGramDatabase database, Context context) {
        this.database = database;
        this.context = context;
    }

    public MutableLiveData<LoginResponse> signIn(String username, String password) {
        Call<LoginResponse> call = UVGramAPIAdapter
                .getApiService()
                .postLogin(username, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
        return loginResponse;
    }



}
