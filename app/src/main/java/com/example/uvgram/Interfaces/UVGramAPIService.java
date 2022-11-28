package com.example.uvgram.Interfaces;

import com.example.uvgram.Models.LoginResponse;
import com.example.uvgram.Models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UVGramAPIService {

    @FormUrlEncoded
    @POST("/authentication/login")
    Call<LoginResponse> postLogin(
            @Field("emailOrUsername") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/accounts/create")
    Call<RegisterResponse> postRegister(
            @Field("name") String name,
            @Field("presentation") String presentation,
            @Field("username") String username,
            @Field("password") String password,
            @Field("phoneNumber") String phoneNumber,
            @Field("email") String email,
            @Field("birthdate") String birthdate,
            @Field("verificationCode") String verificationCode
    );

}
