package com.example.uvgram.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.uvgram.Models.RegisterResponse;
import com.example.uvgram.Models.User;
import com.example.uvgram.R;
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
    private MutableLiveData<RegisterResponse> registerResponse = new MutableLiveData<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        setContentView(R.layout.activity_register_verification);
        userToRegister = (User) getIntent().getSerializableExtra("USER");
        contextView = findViewById(R.id.parentLayout);

        verificationInput = findViewById(R.id.verificationInput);
        sendButton = findViewById(R.id.sendButton);

        viewModel = new ViewModelProvider(this,
                new RegistrationViewModelFactory(getApplication())).get(RegistrationViewModel.class);

        sendButton.setOnClickListener(v -> {
            viewModel.signUpUser(userToRegister,
                String.valueOf(verificationInput.getText())).observe(this, registerResponse -> {
                    String message = registerResponse.getMessage();
                    if (message.equals("New entity was added")) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("USERNAME", userToRegister.getUsername());
                        Intent myIntent = new Intent(getBaseContext(), HomepageActivity.class);
                        startActivity(myIntent);
                    } else {
                        Snackbar.make(contextView, message, Snackbar.LENGTH_LONG).show();
                    }
                });
        });

        registerResponse.observe(this, registerResponse -> {
            String message = registerResponse.getMessage();
            if (message.equals("New entity was added")) {
                Intent myIntent = new Intent(getBaseContext(), HomepageActivity.class);
                myIntent.putExtra("USER", userToRegister);
                startActivity(myIntent);
            } else {
                Snackbar.make(contextView, message, Snackbar.LENGTH_LONG).show();
            }
        });
    }

}