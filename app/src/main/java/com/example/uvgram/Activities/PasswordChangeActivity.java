package com.example.uvgram.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.uvgram.R;
import com.google.android.material.snackbar.Snackbar;

public class PasswordChangeActivity extends AppCompatActivity {

    View contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        contextView = findViewById(R.id.relativeLayout);
    }

    public void sendAction(View view) {
        int duration = Toast.LENGTH_SHORT;
        Snackbar.make(contextView, "¡Contraseña cambiada con éxito!", duration)
                .show();
    }
}