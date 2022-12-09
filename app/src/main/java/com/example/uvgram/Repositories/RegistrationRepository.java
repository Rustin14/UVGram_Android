package com.example.uvgram.Repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Models.RegisterResponse;
import com.example.uvgram.Models.RegisterVerificationResponse;
import com.example.uvgram.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationRepository {

    private final MutableLiveData<RegisterVerificationResponse> verificationResponse = new MutableLiveData<>();
    private final MutableLiveData<RegisterResponse> registerResponse = new MutableLiveData<>();
    SharedPreferences sharedPreferences;
    Context context;

    public RegistrationRepository (Context context) {
       this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public MutableLiveData<RegisterVerificationResponse> signUpVerification(String username, String email) {
        Call<RegisterVerificationResponse> call = UVGramAPIAdapter
                .getApiService()
                .postRegisterVerification(username, email);
        call.enqueue(new Callback<RegisterVerificationResponse>() {
            @Override
            public void onResponse(Call<RegisterVerificationResponse> call, Response<RegisterVerificationResponse> response) {
                if (response.isSuccessful()) {
                    verificationResponse.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<RegisterVerificationResponse> call, Throwable t) {
                    Log.w("MyTag", "requestFailed", t);
            }
        });
        return verificationResponse;
    }

    public MutableLiveData<RegisterResponse> signUpUser(User userToRegister, String verificationCode) {
        Call<RegisterResponse> signUpCall = UVGramAPIAdapter
                .getApiService()
                .postRegister(
                        userToRegister.getName(),
                        userToRegister.getPresentation(),
                        userToRegister.getUsername(),
                        userToRegister.getPassword(),
                        userToRegister.getPhoneNumber(),
                        userToRegister.getEmail(),
                        userToRegister.getBirthday(),
                        verificationCode
                );
        signUpCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    registerResponse.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
        return registerResponse;
    }

}
