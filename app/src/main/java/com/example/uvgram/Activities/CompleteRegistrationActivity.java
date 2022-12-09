package com.example.uvgram.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.uvgram.Models.RegisterVerificationResponse;
import com.example.uvgram.Models.User;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.RegistrationViewModel;
import com.example.uvgram.ViewModel.RegistrationViewModelFactory;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class CompleteRegistrationActivity extends AppCompatActivity {

    TextInputLayout dateTextField;
    TextInputEditText dateInput;
    MaterialDatePicker<Long> datePicker;
    User partialUser;
    Button saveButton;
    Button cancelButton;
    RegistrationViewModel viewModel;
    private MutableLiveData<RegisterVerificationResponse> verificationResponse = new MutableLiveData<>();

    TextInputEditText nameInput;
    TextInputEditText usernameInput;
    TextInputEditText emailInput;
    TextInputEditText phoneInput;
    TextInputEditText presentationInput;
    TextInputEditText passwordInput;
    RelativeLayout parentLayout;

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
        parentLayout = findViewById(R.id.parentLayout);

        viewModel = new ViewModelProvider(this,
                new RegistrationViewModelFactory(getApplication())).get(RegistrationViewModel.class);

        setUserInformation();

        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointBackward.now());
        datePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setCalendarConstraints(constraintsBuilder.build())
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();
        setDateTextFieldListener();

        saveButton.setOnClickListener(v ->
                viewModel.signUpVerification(String.valueOf(usernameInput.getText()),
                        String.valueOf(emailInput.getText())).observe(this, registerVerificationResponse -> {
                    if (registerVerificationResponse.getMessage()) {
                        completeUserInformation();
                        Intent myIntent = new Intent(this, RegisterVerificationActivity.class);
                        myIntent.putExtra("USER", partialUser);
                        startActivity(myIntent);
                    } else {
                        Snackbar.make(parentLayout, "No fue posible completar la operación, intente de nuevo.", Snackbar.LENGTH_LONG).show();
                    }
                })
        );

        cancelButton.setOnClickListener(v -> {
            Intent myIntent = new Intent(this, StartActivity.class);
            startActivity(myIntent);
        });
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

    public void completeUserInformation() {
        partialUser.setUsername(String.valueOf(usernameInput.getText()));
        partialUser.setBirthday(String.valueOf(dateInput.getText()));
        partialUser.setPhoneNumber(String.valueOf(phoneInput.getText()));
        partialUser.setPresentation(String.valueOf(presentationInput.getText()));
    }

}