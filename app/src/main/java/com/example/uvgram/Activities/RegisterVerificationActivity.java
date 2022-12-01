package com.example.uvgram.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Models.RegisterResponse;
import com.example.uvgram.Models.User;
import com.example.uvgram.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterVerificationActivity extends AppCompatActivity implements Callback<RegisterResponse> {

    User userToRegister;
    TextInputEditText verificationInput;
    Button sendButton;
    View contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_verification);
        userToRegister = (User) getIntent().getSerializableExtra("USER");
        contextView = findViewById(R.id.parentLayout);

        verificationInput = findViewById(R.id.verificationInput);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> signUpUser());
    }

    public void signUpUser() {
        if (!TextUtils.isEmpty(verificationInput.getText())) {
            Call<RegisterResponse> signUpCall = UVGramAPIAdapter
                    .getApiService()
                    .postRegister(
                            userToRegister.getName(),
                            userToRegister.getPresentation(),
                            userToRegister.getUsername(),
                            userToRegister.getPassword(),
                            userToRegister.getPhoneNumber(),
                            userToRegister.getEmail(),
                            userToRegister.getBirthday(),
                            String.valueOf(verificationInput.getText())
                    );
            signUpCall.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
        if (response.isSuccessful()) {
            Snackbar.make(contextView, "¡Verificación realizada exitosamente!", Snackbar.LENGTH_SHORT).show();
            Intent myIntent = new Intent(this, HomepageActivity.class);
            myIntent.putExtra("USER", userToRegister);
            startActivity(myIntent);
        } else {
            Snackbar.make(contextView, "No fue posible realizar el registro. Vuelva a intentar.", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<RegisterResponse> call, Throwable t) {
    }
}