package com.example.uvgram.Repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Models.CreatePostResponse.CreatePostResponse;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {

    private final MutableLiveData<CreatePostResponse> createPostResponse = new MutableLiveData<>();
    Context context;
    SharedPreferences sharedPreferences;

    public PostRepository(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public MutableLiveData<CreatePostResponse> createPost(String filePath, String description, boolean commentsAllowed, boolean likesAllowed) throws IOException {
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        File file = new File(filePath);

        RequestBody fileBody = RequestBody.create(file, MediaType.parse("multipart/form-data"));
        MultipartBody.Part[] bodies = new MultipartBody.Part[1];
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file[]", file.getName(), fileBody);
        bodies[0] = body;
        RequestBody descriptionBody = RequestBody.create(description, MediaType.parse("text/plain"));

        Call<CreatePostResponse> call = UVGramAPIAdapter
                .getApiService()
                .createPost("Bearer " + accessToken,
                        bodies,
                        descriptionBody,
                        true,
                        true);
        call.enqueue(new Callback<CreatePostResponse>() {
            @Override
            public void onResponse(Call<CreatePostResponse> call, Response<CreatePostResponse> response) {
                if (response.isSuccessful()) {
                    createPostResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CreatePostResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
        return createPostResponse;
    }


}
