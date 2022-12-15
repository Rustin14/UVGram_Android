package com.example.uvgram.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Models.CreatePostResponse.CreatePostResponse;
import com.example.uvgram.Repositories.PostRepository;

import java.io.IOException;

public class PostViewModel extends AndroidViewModel {

    private final PostRepository repository;
    private MutableLiveData<CreatePostResponse> createPostResponse = new MutableLiveData<>();

    public PostViewModel(@NonNull Application application) {
        super(application);
        repository = new PostRepository(application.getApplicationContext());
    }

    public MutableLiveData<CreatePostResponse> createPost(String filePath, String description, boolean commentsAllowed, boolean likesAllowed) throws IOException {
        createPostResponse = repository.createPost(filePath, description, commentsAllowed, likesAllowed);
        return createPostResponse;
    }

}
