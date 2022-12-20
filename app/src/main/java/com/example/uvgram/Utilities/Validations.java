package com.example.uvgram.Utilities;

import android.text.TextUtils;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class Validations {

    public static boolean checkEmptyTextFields(List<TextInputEditText> inputEditTextList) {
        for (int i = 0; i < inputEditTextList.size(); i++) {
            if (TextUtils.isEmpty(inputEditTextList.get(i).getText())) {
                return false;
            }
        }
        return true;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("^\\d{10}$")) {
            return true;
        } else {
            return false;
        }
    }

    

}
