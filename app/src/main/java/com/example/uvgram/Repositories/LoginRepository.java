package com.example.uvgram.Repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Connection.UVGramDatabase;
import com.example.uvgram.Models.LoginResponses.ErrorMessageResponse;
import com.example.uvgram.Models.LoginResponses.LoginErrorResponse;
import com.example.uvgram.Models.LoginResponses.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private final UVGramDatabase database;
    private final MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();
    Context context;
    final int DATA_NOT_MATCHING = 403;
    final int PASSWORD_FORMAT_INVALID = 400;


    public LoginRepository (UVGramDatabase database, Context context) {
        this.database = database;
        this.context = context;
    }

    // TODO: Implementar manejo de errores

    public MutableLiveData<LoginResponse> signIn(String username, String password) {
        Gson gson = new GsonBuilder().create();
        Call<LoginResponse> call = UVGramAPIAdapter
                .getApiService()
                .postLogin(username, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginResponse.setValue(response.body());
                    loginResponse.getValue().setHttpCode(response.code());
                } else if (response.code() == DATA_NOT_MATCHING) {
                    LoginResponse dataErrorResponse = new LoginResponse();
                    try {
                        dataErrorResponse.setLoginErrorMessage(gson.fromJson(response.errorBody().string(), ErrorMessageResponse.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dataErrorResponse.setHttpCode(response.code());
                    loginResponse.setValue(dataErrorResponse);
                } else if (response.code() == PASSWORD_FORMAT_INVALID){
                    LoginResponse dataErrorResponse = new LoginResponse();
                    try {
                        dataErrorResponse.setLoginErrorResponse(gson.fromJson(response.errorBody().string(), LoginErrorResponse.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dataErrorResponse.setHttpCode(response.code());
                    loginResponse.setValue(dataErrorResponse);
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
