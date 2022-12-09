package com.example.uvgram.Repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Connection.UVGramDatabase;
import com.example.uvgram.Models.LikeResponses.DislikeResponse;
import com.example.uvgram.Models.LikeResponses.LikeResponse;
import com.example.uvgram.Models.LikeResponses.PostLikesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikesRepository {

    private final UVGramDatabase database;
    private final MutableLiveData<LikeResponse> likeResponse = new MutableLiveData<>();
    private final MutableLiveData<DislikeResponse> dislikeResponse = new MutableLiveData<>();
    MutableLiveData<PostLikesResponse> postlikesResponse = new MutableLiveData<>();
    SharedPreferences sharedPreferences;
    Context context;

    public LikesRepository(UVGramDatabase database, Context context) {
        this.database = database;
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public MutableLiveData<LikeResponse> likePost(String uuid) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);


        Call<LikeResponse> call = UVGramAPIAdapter
                .getApiService()
                .likePost("Bearer " + accessToken, uuid);

        call.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                likeResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {

            }
        });
        return likeResponse;
    }

    public MutableLiveData<DislikeResponse> dislikePost(String uuid) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);


        Call<DislikeResponse> call = UVGramAPIAdapter
                .getApiService()
                .dislikePost("Bearer " + accessToken, uuid);

        call.enqueue(new Callback<DislikeResponse>() {
            @Override
            public void onResponse(Call<DislikeResponse> call, Response<DislikeResponse> response) {
                dislikeResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DislikeResponse> call, Throwable t) {

            }
        });
        return dislikeResponse;
    }

    public MutableLiveData<LikeResponse> likeComment(String uuid) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);


        Call<LikeResponse> call = UVGramAPIAdapter
                .getApiService()
                .likeComment("Bearer " + accessToken, uuid);

        call.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                likeResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {

            }
        });
        return likeResponse;
    }

    public MutableLiveData<DislikeResponse> dislikeComment(String uuid) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);


        Call<DislikeResponse> call = UVGramAPIAdapter
                .getApiService().dislikeComment("Bearer " + accessToken, uuid);

        call.enqueue(new Callback<DislikeResponse>() {
            @Override
            public void onResponse(Call<DislikeResponse> call, Response<DislikeResponse> response) {
                dislikeResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DislikeResponse> call, Throwable t) {

            }
        });
        return dislikeResponse;
    }

    public MutableLiveData<PostLikesResponse> getPostLikesDetails(String uuid) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);


        Call<PostLikesResponse> call = UVGramAPIAdapter
                .getApiService().getPostLikesDetails("Bearer " + accessToken, uuid);

        call.enqueue(new Callback<PostLikesResponse>() {
            @Override
            public void onResponse(Call<PostLikesResponse> call, Response<PostLikesResponse> response) {
                if (response.isSuccessful()) {
                    postlikesResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PostLikesResponse> call, Throwable t) {

            }
        });
        return postlikesResponse;
    }








}
