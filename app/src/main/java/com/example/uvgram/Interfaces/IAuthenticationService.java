package com.example.uvgram.Interfaces;

import com.example.uvgram.Models.FollowingResponse;
import com.example.uvgram.Models.GetPostsMessage;
import com.example.uvgram.Models.GetUserResponse;
import com.example.uvgram.Models.LoginResponse;
import com.example.uvgram.Models.RegisterResponse;
import com.example.uvgram.Models.RegisterVerificationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IAuthenticationService {

    @FormUrlEncoded
    @POST("/authentication/login")
    Call<LoginResponse> postLogin(
            @Field("emailOrUsername") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/accounts/create/verification/")
    Call<RegisterVerificationResponse> postRegisterVerification(
            @Field("username") String username,
            @Field("email") String email
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

    @GET("/user/followers-of/{username}")
    Call<FollowingResponse> getFollowersOf(
            @Header("Authorization") String token,
            @Path("username") String username
    );

    @GET("/user/followed-by/{username}")
    Call<FollowingResponse> getFollowedBy(
            @Header("Authorization") String token,
            @Path("username") String username
    );


    @GET ("/{username}")
    Call<GetUserResponse> getUser(
        @Path("username") String username
    );

    @GET("post/user/{username}")
    Call<GetPostsMessage> getPosts(
            @Header("Authorization") String token,
            @Path("username") String username
    );

    /*@POST("post/like")
    Call<>*/

}
