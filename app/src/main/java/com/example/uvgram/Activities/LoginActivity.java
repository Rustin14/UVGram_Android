package com.example.uvgram.Activities;

import static com.example.uvgram.Utilities.Validations.checkEmptyTextFields;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Models.LoginMessage;
import com.example.uvgram.Models.LoginResponse;
import com.example.uvgram.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText usernameInput;
    TextInputEditText passwordView;
    LoginResponse tokens;
    Button loginButton;
    ConstraintLayout parentLayout;
    Context context;
    Button registrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameInput = findViewById(R.id.emailInputText);
        passwordView = findViewById(R.id.passwordInputText);
        parentLayout = findViewById(R.id.parentLayout);
        loginButton = findViewById(R.id.loginButton);
        registrationButton = findViewById(R.id.registrationButton);
        context = getApplicationContext();

        List<TextInputEditText> inputEditTextList = new ArrayList<>();
        inputEditTextList.add(usernameInput);
        inputEditTextList.add(passwordView);

        loginButton.setOnClickListener(view -> {
            if (checkEmptyTextFields(inputEditTextList)) {
                signIn(String.valueOf(usernameInput.getText()), String.valueOf(passwordView.getText()));
            } else {
                Snackbar.make(parentLayout, R.string.emptyInputs, Snackbar.LENGTH_SHORT);
            }
        });
        registrationButton.setOnClickListener(view -> {
            Intent myIntent = new Intent(getBaseContext(), RegistrationActivity.class);
            startActivity(myIntent);
        });
    }

    public void signIn(String username, String password) {
        Call<LoginResponse> call = UVGramAPIAdapter
                .getApiService()
                .postLogin(username, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginMessage loginMessage = response.body().getLoginMessage();
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("ACCESS_TOKEN", loginMessage.getAccessToken());
                    editor.putString("REFRESH_TOKEN", loginMessage.getRefreshToken());
                    editor.commit();

                    Intent myIntent = new Intent(getBaseContext(), HomepageActivity.class);
                    myIntent.putExtra("USERNAME", String.valueOf(usernameInput.getText()));
                    startActivity(myIntent);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.w("MyTag", "requestFailed", t);
            }
        });
    }

}