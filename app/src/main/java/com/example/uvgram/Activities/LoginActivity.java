package com.example.uvgram.Activities;

import static com.example.uvgram.Utilities.Validations.checkEmptyTextFields;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.example.uvgram.Models.LoginResponses.LoginErrorMessageResponse;
import com.example.uvgram.Models.LoginResponses.LoginErrorResponse;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.LoginViewModel;
import com.example.uvgram.ViewModel.LoginViewModelFactory;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText usernameInput;
    TextInputEditText passwordView;
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
                        if (loginResponse.getHttpCode() == 200) {
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("ACCESS_TOKEN", loginResponse.getLoginMessage().getAccessToken());
                            editor.putString("REFRESH_TOKEN", loginResponse.getLoginMessage().getRefreshToken());
                            editor.putString("USERNAME", String.valueOf(usernameInput.getText()));
                            editor.commit();

                            Snackbar.make(parentLayout, R.string.successfulLogin, Snackbar.LENGTH_LONG).show();

                            Intent myIntent = new Intent(getBaseContext(), HomepageActivity.class);
                            startActivity(myIntent);
                        } else if (loginResponse.getHttpCode() == 400) {
                            LoginErrorResponse loginErrorResponse = loginResponse.getLoginErrorResponse();
                            String errorMessage = loginErrorResponse.getErrors().get(0).getMsg();
                            if (errorMessage.equals("password must have the allowed length: {min: 6, max: 128}")) {
                                Snackbar.make(parentLayout, R.string.passwordFormatError, Snackbar.LENGTH_LONG).show();
                            }
                        } else if (loginResponse.getHttpCode() == 403){
                            LoginErrorMessageResponse errorMessage = loginResponse.getLoginErrorMessage();
                            String message = errorMessage.getErrorMessage();
                            if (message.equals("user not found")) {
                                Snackbar.make(parentLayout, R.string.userNotFound, Snackbar.LENGTH_LONG).show();
                            } else if (message.equals("password does not match")) {
                                Snackbar.make(parentLayout, R.string.incorrectPassword, Snackbar.LENGTH_LONG).show();
                            }
                        }
                    }
                });

            } else {
                Snackbar.make(parentLayout, R.string.emptyInputs, Snackbar.LENGTH_SHORT).show();
            }
        });
        registrationButton.setOnClickListener(view -> {
            Intent myIntent = new Intent(getBaseContext(), RegistrationActivity.class);
            startActivity(myIntent);
        });
    }


}