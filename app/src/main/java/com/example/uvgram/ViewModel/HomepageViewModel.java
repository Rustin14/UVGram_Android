package com.example.uvgram.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Connection.UVGramDatabase;
import com.example.uvgram.Models.GetUserResponse;
import com.example.uvgram.Models.Post;
import com.example.uvgram.Repositories.HomepageRepository;

import java.util.List;

public class HomepageViewModel extends AndroidViewModel {

    private final HomepageRepository repository;

    private MutableLiveData<List<Post>> postsList = new MutableLiveData<>();
    private MutableLiveData<GetUserResponse> userResponse = new MutableLiveData<>();

    public HomepageViewModel (@NonNull Application application) {
        super(application);

        UVGramDatabase database = UVGramDatabase.getInstance(application);
        repository = new HomepageRepository(database, application.getApplicationContext());
    }

    public MutableLiveData<List<Post>> getPostsList() {
        postsList = repository.getUser();
        return postsList;
    }

    public MutableLiveData<GetUserResponse> getSignedInUser(String username) {
        userResponse = repository.getSignedInUser(username);
        return userResponse;
    }

}
