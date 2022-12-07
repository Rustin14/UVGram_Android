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
import androidx.lifecycle.ViewModelProvider;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Models.LoginMessage;
import com.example.uvgram.Models.LoginResponse;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.LoginViewModel;
import com.example.uvgram.ViewModel.LoginViewModelFactory;
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

        LoginViewModel viewModel = new ViewModelProvider(this,
                new LoginViewModelFactory(getApplication())).get(LoginViewModel.class);

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
                viewModel.login(String.valueOf(usernameInput.getText()), String.valueOf(passwordView.getText()))
                        .observe(this, loginResponse -> {
                    if (loginResponse != null) {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("ACCESS_TOKEN", loginResponse.getLoginMessage().getAccessToken());
                        editor.putString("REFRESH_TOKEN", loginResponse.getLoginMessage().getRefreshToken());
                        editor.commit();

                        Snackbar.make(parentLayout, R.string.successfulLogin, Snackbar.LENGTH_LONG).show();

                        //Intent myIntent = new Intent(getBaseContext(), HomepageActivity.class);
                        //startActivity(myIntent);
                    }
                });

            } else {
                Snackbar.make(parentLayout, R.string.emptyInputs, Snackbar.LENGTH_SHORT);
            }
        });
        registrationButton.setOnClickListener(view -> {
            Intent myIntent = new Intent(getBaseContext(), RegistrationActivity.class);
            startActivity(myIntent);
        });
    }


}