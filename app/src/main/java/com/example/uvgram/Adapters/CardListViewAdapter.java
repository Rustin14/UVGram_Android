package com.example.uvgram.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Interfaces.OnDataLoaded;
import com.example.uvgram.Models.FollowingResponse;
import com.example.uvgram.Models.GetUserResponse;
import com.example.uvgram.Models.Message;
import com.example.uvgram.Models.Post;
import com.example.uvgram.Models.User;
import com.example.uvgram.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardListViewAdapter extends BaseAdapter implements OnDataLoaded {

    Context context;
    ArrayList<Post> postList = new ArrayList<>();
    LayoutInflater inflater;
    ArrayList<Message> followedUserList = new ArrayList<>();
    Message signedInUser;
    ArrayList<Post> postsArrayList = new ArrayList<>();
    ArrayList<User> followsList;
    SharedPreferences sharedPreferences;
    String username;

    public CardListViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        getUser();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    private void getFollows() {
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

    public void getUser() {
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
    }

    public void getUsersAndPosts() {
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

    @Override
    public void onDataLoaded(Message user) {
        followedUserList.add(user);
        for (int i = 0; i < user.getPosts().size(); i++) {
            user.getPosts().get(i).setUsername(user.getUsername());
        }
        postsArrayList.addAll(user.getPosts());
        postList = postsArrayList;
    }

    @Override
    public void onFollowsLoaded(ArrayList<User> follows) {
        followsList = follows;
        getUsersAndPosts();
    }

    @Override
    public void onUserLoaded(Message signedInUser) {
        this.signedInUser = signedInUser;
        getFollows();
    }

    @Override
    public View getView(int position, View cardView, ViewGroup viewGroup) {
        cardView = inflater.inflate(R.layout.fragment_post_card, null);
        ImageView postImage = cardView.findViewById(R.id.postCardImage);
        TextView usernameText = cardView.findViewById(R.id.usernameText);
        TextView descriptionText = cardView.findViewById(R.id.descriptionText);
        postImage.setImageResource(R.drawable.pxl_20221001_231154257);
        usernameText.setText(postList.get(position).getUsername());
        descriptionText.setText(postList.get(position).getDescription());
        return cardView;
    }
}
