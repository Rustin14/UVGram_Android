package com.example.uvgram.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Connection.UVGramDatabase;
import com.example.uvgram.Models.LoginResponse;
import com.example.uvgram.Repositories.LoginRepository;

public class LoginViewModel extends AndroidViewModel {

    private final LoginRepository repository;
    private MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);

        UVGramDatabase database = UVGramDatabase.getInstance(application);
        repository = new LoginRepository(database, application.getApplicationContext());
    }

    public MutableLiveData<LoginResponse> login(String username, String password) {
        loginResponse = repository.signIn(username, password);
        return loginResponse;
    }
    

}
