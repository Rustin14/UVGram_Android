package com.example.uvgram.Interfaces;

import com.example.uvgram.Models.AccountResponse;
import com.example.uvgram.Models.BlockResponses.BlockResponse;
import com.example.uvgram.Models.BlockResponses.UnblockResponse;
import com.example.uvgram.Models.CommentPostResponses.CommentPostResponse;
import com.example.uvgram.Models.EditProfileResponses.EditProfileResponse;
import com.example.uvgram.Models.FollowResponses.FollowResponse;
import com.example.uvgram.Models.FollowResponses.UnfollowResponse;
import com.example.uvgram.Models.FollowingResponse;
import com.example.uvgram.Models.GetCommentsResponses.GetCommentsResponse;
import com.example.uvgram.Models.GetPostsMessage;
import com.example.uvgram.Models.GetUserResponse;
import com.example.uvgram.Models.LikeResponses.DislikeResponse;
import com.example.uvgram.Models.LikeResponses.LikeResponse;
import com.example.uvgram.Models.LikeResponses.PostLikesResponse;
import com.example.uvgram.Models.LoginResponse;
import com.example.uvgram.Models.RegisterResponse;
import com.example.uvgram.Models.RegisterVerificationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
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

    @GET("/{username}")
    Call<GetUserResponse> getUser(
            @Path("username") String username
    );

    @GET("post/user/{username}")
    Call<GetPostsMessage> getPosts(
            @Header("Authorization") String token,
            @Path("username") String username
    );

    @FormUrlEncoded
    @POST("post/like")
    Call<LikeResponse> likePost(
            @Header("Authorization") String token,
            @Field("uuid") String uuid
    );

    @FormUrlEncoded
    @POST("post/dislike")
    Call<DislikeResponse> dislikePost(
            @Header("Authorization") String token,
            @Field("uuid") String uuid
    );

    @FormUrlEncoded
    @POST("post/comment/like")
    Call<LikeResponse> likeComment(
            @Header("Authorization") String token,
            @Field("uuid") String uuid
    );

    @FormUrlEncoded
    @POST("post/comment/dislike")
    Call<DislikeResponse> dislikeComment(
            @Header("Authorization") String token,
            @Field("uuid") String uuid
    );

    @FormUrlEncoded
    @POST("user/follow")
    Call<FollowResponse> followUser(
            @Header("Authorization") String token,
            @Field("username") String username
    );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "user/unfollow", hasBody = true)
    Call<UnfollowResponse> unfollowUser(
            @Header("Authorization") String token,
            @Field("username") String username
    );


    @FormUrlEncoded
    @POST("user/block")
    Call<BlockResponse> blockUser(
            @Header("Authorization") String token,
            @Field("username") String username
    );

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "user/unblock", hasBody = true)
    Call<UnblockResponse> unblockUser(
            @Header("Authorization") String token,
            @Field("username") String username
    );

    @GET("post/details/likes/{uuid}")
    Call<PostLikesResponse> getPostLikesDetails(
            @Header("Authorization") String token,
            @Path("uuid") String uuid
    );

    @GET("accounts/data")
    Call<AccountResponse> getAccount(
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @PATCH("accounts/edit/personal")
    Call<EditProfileResponse> editProfile(
            @Header("Authorization") String token,
            @Field("name") String name,
            @Field("presentation") String presentation,
            @Field("username") String username,
            @Field("phoneNumber") String phoneNumber,
            @Field("email") String email,
            @Field("birthdate") String birthdate,
            @Field("gender") String gender,
            @Field("idCareer") int idCareer
    );

    @GET("post/comment/all/{uuid}")
    Call<GetCommentsResponse> getComments(
            @Header("Authorization") String token,
            @Path("uuid") String uuid
    );

    @FormUrlEncoded
    @POST("post/comment/create/")
    Call<CommentPostResponse> postComment(
            @Header("Authorization") String token,
            @Field("comment") String comment,
            @Field("uuid") String uuid
    );

}
