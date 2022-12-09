package com.example.uvgram.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.uvgram.Models.RegisterResponse;
import com.example.uvgram.Models.RegisterVerificationResponse;
import com.example.uvgram.Models.User;
import com.example.uvgram.Repositories.RegistrationRepository;

public class RegistrationViewModel extends AndroidViewModel {

    private final RegistrationRepository repository;
    private MutableLiveData<RegisterVerificationResponse> verificationResponse = new MutableLiveData<>();
    private MutableLiveData<RegisterResponse> registerResponse = new MutableLiveData<>();

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
        repository =  new RegistrationRepository(application.getApplicationContext());
    }

    public MutableLiveData<RegisterVerificationResponse> signUpVerification(String username, String email) {
        verificationResponse = repository.signUpVerification(username, email);
        return verificationResponse;
    }

    public MutableLiveData<RegisterResponse> signUpUser(User userToRegister, String verificationCode) {
        registerResponse = repository.signUpUser(userToRegister, verificationCode);
        return registerResponse;
    }


}
