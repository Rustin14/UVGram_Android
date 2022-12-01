package com.example.uvgram;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.uvgram.Adapters.CardListViewAdapter;
import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Models.FollowingResponse;
import com.example.uvgram.Models.Message;
import com.example.uvgram.Models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardListViewFragment extends Fragment {

    ArrayList<Post> postList = new ArrayList<>();
    Message signedInUser;
    ListView listView;
    SharedPreferences sharedPreferences;
    ArrayList<Message> messagesList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("Hola, estoy creado.");
        listView = getView().findViewById(R.id.cardsListView);
        FragmentActivity fragmentActivity = getActivity();
        Intent intent =  fragmentActivity.getIntent();
        signedInUser = (Message) intent.getSerializableExtra("SIGNED_IN_USER");
        //signedInUser = (Message) fragmentActivity.getIntent().getExtras().get("USER");
        populatePostList();
        getFollows();
        listView.setAdapter(new CardListViewAdapter(getContext(), postList));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_list_view, container, false);
    }

    private void populatePostList() {
        for (int i = 0; i < 3; i++) {
            Post post = new Post();
            post.setDescription("Esta es la descripción del objeto número: " + i);
            postList.add(post);
        }
    }

    private void getFollows() {
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("ACCESS_TOKEN", "DEFAULT");

        Call<FollowingResponse> call = UVGramAPIAdapter
                .getApiService().getFollowedBy("Bearer " + accessToken, signedInUser.getUsername());
        call.enqueue(new Callback<FollowingResponse>() {
            @Override
            public void onResponse(Call<FollowingResponse> call, Response<FollowingResponse> response) {
                if (response.isSuccessful()) {
                    messagesList = (ArrayList<Message>) response.body().getMessage();
                    System.out.println(messagesList.get(0).getName());
                }
            }
            @Override
            public void onFailure(Call<FollowingResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
    }
}