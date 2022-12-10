package com.example.uvgram.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Models.CommentPostResponses.CommentPostResponse;
import com.example.uvgram.Models.GetCommentsResponses.GetCommentsResponse;
import com.example.uvgram.Repositories.CommentRepository;

public class CommentsViewModel extends AndroidViewModel {

    private final CommentRepository repository;

    public CommentsViewModel(@NonNull Application application) {
        super(application);
        repository = new CommentRepository(getApplication().getApplicationContext());
    }

    public MutableLiveData<GetCommentsResponse> getComments(String uuid) {
        MutableLiveData<GetCommentsResponse> commentsResponse = repository.getComments(uuid);
        return commentsResponse;
    }

    public MutableLiveData<CommentPostResponse> postComment(String comment, String uuid) {
        MutableLiveData<CommentPostResponse> postCommentResponse = repository.postComment(comment, uuid);
        return postCommentResponse;
    }







}
