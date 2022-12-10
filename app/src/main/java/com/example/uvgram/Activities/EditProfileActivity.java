package com.example.uvgram.Activities;

import static com.example.uvgram.Utilities.Validations.checkEmptyTextFields;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.uvgram.Models.Account;
import com.example.uvgram.R;
import com.example.uvgram.ViewModel.ProfileViewModel;
import com.example.uvgram.ViewModel.ProfileViewModelFactory;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    Account userAccount;
    SharedPreferences sharedPreferences;
    TextInputEditText nameInput;
    TextInputEditText usernameInput;
    TextInputEditText emailInputText;
    TextInputEditText dateInput;
    TextInputEditText phoneInput;
    TextInputEditText presentationInput;
    RelativeLayout relativeLayout;
    Button saveButton;
    Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        nameInput = findViewById(R.id.nameInput);
        usernameInput = findViewById(R.id.usernameInput);
        emailInputText = findViewById(R.id.emailInputText);
        dateInput = findViewById(R.id.dateInput);
        phoneInput = findViewById(R.id.phoneInput);
        presentationInput = findViewById(R.id.presentationInput);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        relativeLayout = findViewById(R.id.parentLayout);

        cancelButton.setOnClickListener(view -> {
            finish();
        });

        ProfileViewModel viewModel = new ViewModelProvider(this,
                new ProfileViewModelFactory(getApplication()))
                .get(ProfileViewModel.class);

        viewModel.getAccount().observe(this, accountResponse -> {
            userAccount = accountResponse.getAccount();
            fillTextFields(userAccount);
        });

        List<TextInputEditText> inputEditTextList = new ArrayList<>();
        inputEditTextList.add(nameInput);
        inputEditTextList.add(usernameInput);
        inputEditTextList.add(emailInputText);
        inputEditTextList.add(dateInput);
        inputEditTextList.add(phoneInput);
        inputEditTextList.add(presentationInput);

        saveButton.setOnClickListener(view -> {
            if (checkEmptyTextFields(inputEditTextList)) {
                viewModel.editProfile(setUserAccount()).observe(this, editProfileResponse -> {
                    if(editProfileResponse.getIsUpdated().isUpdated()) {
                        Snackbar.make(relativeLayout, "Actualización completada.", Snackbar.LENGTH_SHORT);
                        finish();
                    } else {
                        Snackbar.make(relativeLayout, "Ocurrió un problema. Vuelva a intentarlo.", Snackbar.LENGTH_SHORT);
                    }
                });
            }
        });
    }

    public void fillTextFields(Account userAccount) {
        nameInput.setText(userAccount.getName());
        usernameInput.setText(userAccount.getUsername());
        emailInputText.setText(userAccount.getEmail());
        dateInput.setText(userAccount.getBirthday());
        phoneInput.setText(userAccount.getPhoneNumber());
        presentationInput.setText(userAccount.getPresentation());
    }

    public Account setUserAccount() {
        Account account = new Account();
        account.setName(String.valueOf(nameInput.getText()));
        account.setUsername(String.valueOf(usernameInput.getText()));
        account.setPresentation(String.valueOf(presentationInput.getText()));
        account.setPhoneNumber(String.valueOf(phoneInput.getText()));
        account.setEmail(String.valueOf(emailInputText.getText()));
        account.setBirthday(String.valueOf(dateInput.getText()));
        return account;
    }






}