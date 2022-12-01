package com.example.uvgram.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.uvgram.R;
import com.example.uvgram.databinding.ActivityPasswordRecoveryBinding;
import com.google.android.material.card.MaterialCardView;

public class PasswordRecoveryActivity extends AppCompatActivity {

    ActivityPasswordRecoveryBinding dataBinding;
    ProgressBar progressBar;
    MaterialCardView textCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_password_recovery);
        progressBar = dataBinding.progressBar;
        textCard = dataBinding.textCard;
    }

    public void sendAction(View view) {
        textCard.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }



}