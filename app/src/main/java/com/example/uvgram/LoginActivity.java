package com.example.uvgram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Models.LoginResponse;
import com.google.android.material.textfield.TextInputEditText;

import java.time.Duration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements Callback<LoginResponse> {

    TextInputEditText emailView;
    TextInputEditText passwordView;
    LoginResponse tokens;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailView = findViewById(R.id.emailInputText);
        passwordView = findViewById(R.id.passwordInputText);

        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> {
            signIn();
        });
    }

    public void signIn() {
        String email = String.valueOf(emailView.getText());
        String password = String.valueOf(passwordView.getText());

        Call<LoginResponse> call = UVGramAPIAdapter
                .getApiService()
                .postLogin(email, password);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if (response.isSuccessful()) {
            tokens = response.body();
            SharedPreferences preferences = getSharedPreferences("UVGram", Context.MODE_PRIVATE);
            preferences.edit().putString("access_token", tokens.getAccessToken()).apply();
            preferences.edit().putString("refresh_token", tokens.getRefreshToken()).apply();

            Intent myIntent = new Intent(this, HomepageActivity.class);
            startActivity(myIntent);

        }
    }

    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {
        Toast.makeText(this, "Ocurrió un error. Vuelva a intentarlo.", Toast.LENGTH_SHORT);
    }
}