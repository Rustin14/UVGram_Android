package com.example.uvgram.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uvgram.Connection.UVGramAPIAdapter;
import com.example.uvgram.Models.RegisterVerificationResponse;
import com.example.uvgram.Models.User;
import com.example.uvgram.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompleteRegistrationActivity extends AppCompatActivity implements Callback<RegisterVerificationResponse> {

    TextInputLayout dateTextField;
    TextInputEditText dateInput;
    MaterialDatePicker<Long> datePicker;
    User partialUser;
    Button saveButton;
    Button cancelButton;

    TextInputEditText nameInput;
    TextInputEditText usernameInput;
    TextInputEditText emailInput;
    TextInputEditText phoneInput;
    TextInputEditText presentationInput;
    TextInputEditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_registration);
        partialUser = (User) getIntent().getSerializableExtra("PARTIAL_USER");

        dateTextField = findViewById(R.id.dateTextField);
        dateInput = findViewById(R.id.dateInput);
        nameInput = findViewById(R.id.nameInput);
        usernameInput = findViewById(R.id.usernameInput);
        phoneInput = findViewById(R.id.phoneInput);
        emailInput = findViewById(R.id.emailInputText);
        presentationInput = findViewById(R.id.presentationInput);
        passwordInput = findViewById(R.id.passwordInputText);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        setUserInformation();

        saveButton.setOnClickListener(v -> signUpVerification());

        cancelButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, StartActivity.class);
            startActivity(myIntent);
        });

        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointBackward.now());
        datePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setCalendarConstraints(constraintsBuilder.build())
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();
        setDateTextFieldListener();
    }

    public void setDateTextFieldListener() {
        dateTextField.setEndIconOnClickListener(view -> {
            datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });

        datePicker.addOnPositiveButtonClickListener(selection -> {
            SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTimeInMillis(selection);
            String strDate = format.format(calendar.getTime());
            dateInput.setText(strDate);
        });
    }

    public void setUserInformation() {
        nameInput.setText(partialUser.getName());
        nameInput.setKeyListener(null);
        emailInput.setText(partialUser.getEmail());
        emailInput.setKeyListener(null);
        passwordInput.setText(partialUser.getPassword());
        passwordInput.setKeyListener(null);
    }

    public void signUpVerification() {
        String email = String.valueOf(emailInput.getText());
        String username = String.valueOf(usernameInput.getText());

        Call<RegisterVerificationResponse> call = UVGramAPIAdapter
                .getApiService()
                .postRegisterVerification(username, email);
        call.enqueue(this);
    }

    public void completeUserInformation() {
        partialUser.setUsername(String.valueOf(usernameInput.getText()));
        partialUser.setBirthday(String.valueOf(dateInput.getText()));
        partialUser.setPhoneNumber(String.valueOf(phoneInput.getText()));
        partialUser.setPresentation(String.valueOf(presentationInput.getText()));
    }

    @Override
    public void onResponse(Call<RegisterVerificationResponse> call, Response<RegisterVerificationResponse> response) {
        if (response.isSuccessful()) {
            // Agregar transición a actividad de verificación
            completeUserInformation();
            Intent myIntent = new Intent(this, RegisterVerificationActivity.class);
            myIntent.putExtra("USER", partialUser);
            startActivity(myIntent);
        }
    }

    @Override
    public void onFailure(Call<RegisterVerificationResponse> call, Throwable t) {

    }
}