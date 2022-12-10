package com.example.uvgram.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Connection.UVGramDatabase;
import com.example.uvgram.Models.LikeResponses.DislikeResponse;
import com.example.uvgram.Models.LikeResponses.LikeResponse;
import com.example.uvgram.Models.LikeResponses.PostLikesResponse;
import com.example.uvgram.Repositories.LikesRepository;

public class LikesViewModel extends AndroidViewModel {

    private final LikesRepository repository;
    private MutableLiveData<LikeResponse> likeResponse = new MutableLiveData<>();
    private MutableLiveData<DislikeResponse> dislikeResponse = new MutableLiveData<>();
    public MutableLiveData<PostLikesResponse> postLikesResponse = new MutableLiveData<>();


    public LikesViewModel(@NonNull Application application) {
        super(application);
        UVGramDatabase database = UVGramDatabase.getInstance(application);
        repository = new LikesRepository(database, application.getApplicationContext());
    }

    public MutableLiveData<LikeResponse> likePost(String uuid) {
        likeResponse = repository.likePost(uuid);
        return likeResponse;
    }

    public MutableLiveData<DislikeResponse> dislikePost(String uuid) {
        dislikeResponse = repository.dislikePost(uuid);
        return dislikeResponse;
    }

    public MutableLiveData<PostLikesResponse> getPostLikesDetails(String uuid) {
        postLikesResponse = repository.getPostLikesDetails(uuid);
        return postLikesResponse;
    }

    public MutableLiveData<LikeResponse> likeComment(String uuid) {
        MutableLiveData<LikeResponse> likeCommentResponse = repository.likeComment(uuid);
        return likeCommentResponse;
    }

    public MutableLiveData<DislikeResponse> dislikeComment(String uuid) {
        MutableLiveData<DislikeResponse> dislikeCommentResponse = repository.dislikeComment(uuid);
        return dislikeCommentResponse;
    }






}
