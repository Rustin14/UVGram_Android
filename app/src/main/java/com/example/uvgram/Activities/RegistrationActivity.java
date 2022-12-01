package com.example.uvgram.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uvgram.Models.User;
import com.example.uvgram.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivity extends AppCompatActivity {

    TextInputEditText nameInput;
    TextInputEditText emailInput;
    TextInputEditText passwordInput;
    TextInputEditText confirmedPasswordInput;
    Button signUpButton;
    View contextView;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        contextView = findViewById(R.id.parentLayout);

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInputText);
        passwordInput = findViewById(R.id.passwordInputText);
        confirmedPasswordInput = findViewById(R.id.confirmedPasswordInput);
        signUpButton = findViewById(R.id.signUpButton);
        loginButton = findViewById(R.id.loginButton);

        signUpButton.setOnClickListener(v -> completeRegistration());
        loginButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(myIntent);
        });

    }

    private User createPartialUser() {
        User user = new User();
        user.setName(String.valueOf(nameInput.getText()));
        user.setEmail(String.valueOf(emailInput.getText()));
        user.setPassword(String.valueOf(passwordInput.getText()));
        return user;
    }

    public void completeRegistration() {
        if (validateInputTexts()) {
            User user = createPartialUser();
            Intent myIntent = new Intent(getApplicationContext(), CompleteRegistrationActivity.class);
            myIntent.putExtra("PARTIAL_USER", user);
            startActivity(myIntent);
        }
    }

    private boolean validateInputTexts() {
        if (TextUtils.isEmpty(nameInput.getText()) || TextUtils.isEmpty(emailInput.getText())
                || TextUtils.isEmpty(passwordInput.getText()) || TextUtils.isEmpty(confirmedPasswordInput.getText())) {
            System.out.println("Hola.");
            Snackbar.make(contextView, "No dejes campos vacíos.", Snackbar.LENGTH_LONG).show();
            return false;
        } else {
            if (TextUtils.equals(String.valueOf(passwordInput.getText()), String.valueOf(confirmedPasswordInput.getText()))) {
                return true;
            } else {
                Snackbar.make(contextView, "Las contraseñas no son iguales.", Snackbar.LENGTH_LONG).show();
                return false;
            }
        }
    }
}