package com.example.uvgram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class PasswordChange extends AppCompatActivity {

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