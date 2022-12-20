package com.example.uvgram.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.uvgram.Models.User;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.LoginViewModel;
import com.example.uvgram.ViewModel.LoginViewModelFactory;
import com.example.uvgram.ViewModel.RegistrationViewModel;
import com.example.uvgram.ViewModel.RegistrationViewModelFactory;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterVerificationActivity extends AppCompatActivity {

    User userToRegister;
    TextInputEditText verificationInput;
    Button sendButton;
    View contextView;
    RegistrationViewModel viewModel;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        setContentView(R.layout.activity_register_verification);
        userToRegister = (User) getIntent().getSerializableExtra("USER");
        contextView = findViewById(R.id.parentLayout);

        verificationInput = findViewById(R.id.verificationInput);
        sendButton = findViewById(R.id.sendVerificationButton);

        viewModel = new ViewModelProvider(this,
                new RegistrationViewModelFactory(getApplication())).get(RegistrationViewModel.class);

        loginViewModel = new ViewModelProvider(this,
                new LoginViewModelFactory(getApplication())).get(LoginViewModel.class);

        sendButton.setOnClickListener(v -> {
            if (!String.valueOf(verificationInput.getText()).isEmpty()) {
                viewModel.signUpUser(userToRegister,
                        String.valueOf(verificationInput.getText())).observe(this, registerResponse -> {
                    if (registerResponse.getMessage().equals("New entity was added")) {
                        SharedPreferences.Editor editor = preferences.edit();
                        loginViewModel.login(userToRegister.getUsername(), userToRegister.getPassword())
                                .observe(this, loginResponse -> {
                                    editor.putString("ACCESS_TOKEN", loginResponse.getLoginMessage().getAccessToken());
                                    editor.putString("REFRESH_TOKEN", loginResponse.getLoginMessage().getRefreshToken());
                                    editor.putString("USERNAME", userToRegister.getUsername());
                                    editor.commit();
                                });
                        Intent myIntent = new Intent(getBaseContext(), HomepageActivity.class);
                        startActivity(myIntent);
                    } else if (registerResponse.getHttpCode() == 403) {
                        Snackbar.make(contextView, R.string.invalidCodeFormat, Snackbar.LENGTH_LONG).show();
                    }
                });
            } else {
                Snackbar.make(contextView, R.string.emptyInputs, Snackbar.LENGTH_LONG).show();
            }
        });

    }

}