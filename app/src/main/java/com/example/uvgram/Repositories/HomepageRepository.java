package com.example.uvgram.Repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Connection.UVGramDatabase;
import com.example.uvgram.Interfaces.OnDataLoaded;
import com.example.uvgram.Models.FollowingResponse;
import com.example.uvgram.Models.GetFollowedByResponse;
import com.example.uvgram.Models.GetUserResponse;
import com.example.uvgram.Models.Message;
import com.example.uvgram.Models.Post;
import com.example.uvgram.Models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomepageRepository implements OnDataLoaded {

    Context context;
    SharedPreferences sharedPreferences;
    ArrayList<Post> postsArrayList = new ArrayList<>();
    private final UVGramDatabase database;
    private final MutableLiveData<List<Post>> postList = new MutableLiveData<>();
    private final MutableLiveData<GetUserResponse> userResponse = new MutableLiveData<>();
    private final MutableLiveData<GetFollowedByResponse> followedByResponse = new MutableLiveData<>();

    public HomepageRepository (UVGramDatabase database, Context context) {
        this.database = database;
        this.context = context;
    }

    // Obtener los usuarios a los que sigue el usuario que inició sesión
    private void getFollows(String username) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        Call<FollowingResponse> call = UVGramAPIAdapter
                .getApiService().getFollowedBy("Bearer " + accessToken, username);
        call.enqueue(new Callback<FollowingResponse>() {
            @Override
            public void onResponse(Call<FollowingResponse> call, Response<FollowingResponse> response) {
                if (response.isSuccessful()) {
                    onFollowsLoaded((ArrayList<User>) response.body().getUsersList());
                }
            }
            @Override
            public void onFailure(Call<FollowingResponse> call, Throwable t) {
                    Log.w("MyTag", "requestFailed", t);
            }
        });
    }


    // TODO: Agregar parámetro String username
    public MutableLiveData<List<Post>> getFollowedUsersPosts(String username) {
        Call<GetUserResponse> userCall = UVGramAPIAdapter
                .getApiService()
                .getUser(username);

        userCall.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if (response.isSuccessful()) {
                    onUserLoaded(response.body().getMessage());
                }
            }
            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
        return postList;
    }

    public MutableLiveData<GetFollowedByResponse> getFollowedUsers(String username) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        Call<GetFollowedByResponse> call = UVGramAPIAdapter
                .getApiService()
                .getFollowedByUsers("Bearer " + accessToken,
                        username);

        call.enqueue(new Callback<GetFollowedByResponse>() {
            @Override
            public void onResponse(Call<GetFollowedByResponse> call, Response<GetFollowedByResponse> response) {
                if (response.isSuccessful()) {
                    followedByResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GetFollowedByResponse> call, Throwable t) {

            }
        });
        return followedByResponse;
    }

    public MutableLiveData<GetFollowedByResponse> getFollowersUsers(String username) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", null);

        Call<GetFollowedByResponse> call = UVGramAPIAdapter
                .getApiService()
                .getFollowersOfUsers("Bearer " + accessToken,
                        username);

        call.enqueue(new Callback<GetFollowedByResponse>() {
            @Override
            public void onResponse(Call<GetFollowedByResponse> call, Response<GetFollowedByResponse> response) {
                if (response.isSuccessful()) {
                    followedByResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GetFollowedByResponse> call, Throwable t) {

            }
        });
        return followedByResponse;
    }

    // Obtener posts de cada usuario que siga el usuario que inició sesión
    public void getUsersPosts(ArrayList<User> followsList) {
        for (int i = 0; i < followsList.size(); i++) {
            int finalI = i;
            Call<GetUserResponse> userCall = UVGramAPIAdapter
                    .getApiService()
                    .getUser(followsList.get(i).getUsername());
            userCall.enqueue(new Callback<GetUserResponse>() {
                @Override
                public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                    if(response.isSuccessful()) {
                        onDataLoaded(response.body().getMessage());
                    }
                }
                @Override
                public void onFailure(Call<GetUserResponse> call, Throwable t) {
                    Log.w("MyTag", "requestFailed", t);
                }
            });
        }
    }

    public MutableLiveData<GetUserResponse> getSignedInUser(String username) {
        Call<GetUserResponse> userCall = UVGramAPIAdapter
                .getApiService()
                .getUser(username);

        userCall.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if (response.isSuccessful()) {
                    userResponse.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
        return userResponse;
    }

    public MutableLiveData<GetUserResponse> getUser(String username) {
        Call<GetUserResponse> userCall = UVGramAPIAdapter
                .getApiService()
                .getUser(username);

        userCall.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if (response.isSuccessful()) {
                    userResponse.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
        return userResponse;
    }


    @Override
    public void onDataLoaded(Message user) {
        for (int i = 0; i < user.getPosts().size(); i++) {
            user.getPosts().get(i).setUsername(user.getUsername());
            postsArrayList.add(user.getPosts().get(i));
        }
        postList.setValue(postsArrayList);
    }

    @Override
    public void onFollowsLoaded(ArrayList<User> follows) {
        getUsersPosts(follows);
    }

    @Override
    public void onUserLoaded(Message signedInUser) {
        getFollows(signedInUser.getUsername());
    }
}
