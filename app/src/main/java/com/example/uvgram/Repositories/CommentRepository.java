package com.example.uvgram.Repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Models.CommentPostResponses.CommentPostResponse;
import com.example.uvgram.Models.GetCommentsResponses.GetCommentsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRepository {

    private final MutableLiveData<GetCommentsResponse> commentsResponse = new MutableLiveData<>();
    private final MutableLiveData<CommentPostResponse> postCommentResponse = new MutableLiveData<>();
    SharedPreferences sharedPreferences;
    Context context;

    public CommentRepository(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public MutableLiveData<GetCommentsResponse> getComments(String uuid) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        Call<GetCommentsResponse> call = UVGramAPIAdapter
                .getApiService()
                .getComments("Bearer " + accessToken, uuid);

        call.enqueue(new Callback<GetCommentsResponse>() {
            @Override
            public void onResponse(Call<GetCommentsResponse> call, Response<GetCommentsResponse> response) {
                if (response.isSuccessful()) {
                    commentsResponse.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<GetCommentsResponse> call, Throwable t) {

            }
        });
        return commentsResponse;
    }

    public MutableLiveData<CommentPostResponse> postComment(String comment, String uuid) {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        Call<CommentPostResponse> call = UVGramAPIAdapter
                .getApiService().postComment("Bearer " + accessToken,
                        comment,
                        uuid);

        call.enqueue(new Callback<CommentPostResponse>() {
            @Override
            public void onResponse(Call<CommentPostResponse> call, Response<CommentPostResponse> response) {
                postCommentResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CommentPostResponse> call, Throwable t) {

            }
        });
        return postCommentResponse;
    }


}
