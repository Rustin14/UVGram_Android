package com.example.uvgram.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Models.AccountResponse;
import com.example.uvgram.Repositories.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {

    private final ProfileRepository repository;
    private MutableLiveData<AccountResponse> accountResponse = new MutableLiveData<>();

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new ProfileRepository(application.getApplicationContext());
    }

    public MutableLiveData<AccountResponse> getAccount() {
        accountResponse = repository.getAccount();
        return accountResponse;
    }
}
