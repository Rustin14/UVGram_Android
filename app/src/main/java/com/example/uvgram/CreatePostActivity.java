package com.example.uvgram;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uvgram.Adapters.GalleryAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CreatePostActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GalleryAdapter galleryAdapter;
    List<String> images;
    TextView gallery_number;
    LinearLayout parentLayout;

    private static final int MY_READ_PERMISSION_CODE =  101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentLayout = findViewById(R.id.parentLayout);
        setContentView(R.layout.activity_create_post);
        gallery_number = findViewById(R.id.galleryNumber);
        recyclerView = findViewById(R.id.galleryImagesRecyclerView);

        // Verificar permiso
        if (ContextCompat.checkSelfPermission(CreatePostActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CreatePostActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_READ_PERMISSION_CODE);
        } else {
            loadImages();
        }

    }

    private void loadImages() {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_READ_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(parentLayout, "Permiso concedido.", Snackbar.LENGTH_SHORT).show();
                loadImages();
            } else {
                Snackbar.make(parentLayout, "Permiso denegado.", Snackbar.LENGTH_SHORT).show();
            }
        }

    }
}