package com.example.uvgram.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uvgram.Adapters.GalleryAdapter;
import com.example.uvgram.R;
import com.example.uvgram.Utilities.ImagesGallery;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CreatePostActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GalleryAdapter galleryAdapter;
    List<String> images;
    MaterialToolbar toolbar;
    LinearLayout parentLayout;
    ImageView imagePreview;
    ViewGroup viewGroup;
    String selectedImagePath;


    private static final int MY_READ_PERMISSION_CODE =  101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentLayout = findViewById(R.id.parentLayout);
        setContentView(R.layout.activity_create_post);
        recyclerView = findViewById(R.id.galleryImagesRecyclerView);
        imagePreview = findViewById(R.id.imagePreview);
        toolbar = findViewById(R.id.topAppBar);

        toolbar.setOnMenuItemClickListener(item -> {
           switch (item.getItemId()) {
               case R.id.nextActivity:
                   Uri sourceUri = Uri.fromFile(new File(selectedImagePath));
                   String destinationUri = new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();
                   File outputFile = null;
                   try {
                       outputFile = File.createTempFile("tempImage", ".jpeg", getCacheDir());
                   } catch (IOException e) {
                       Snackbar.make(viewGroup, "Sucedió un error, vuelva a intentarlo.", Snackbar.LENGTH_LONG).show();
                   }
                   UCrop.of(sourceUri, Uri.fromFile(new File(getCacheDir(), destinationUri)))
                           .start(this);
                   return true;
           }
           return false;
        });

        viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        images = ImagesGallery.listOfImages(this);
        Glide.with(getBaseContext()).load(images.get(0)).centerCrop().into(imagePreview);
        galleryAdapter = new GalleryAdapter(this, images, path -> {
            selectedImagePath = path;
            Glide.with(getBaseContext()).load(path).centerCrop().into(imagePreview);
        });
        recyclerView.setAdapter(galleryAdapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_READ_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(this, viewGroup, "Permiso concedido.", Snackbar.LENGTH_SHORT).show();
                loadImages();
            } else {
                Snackbar.make(this, viewGroup, "Permiso denegado.", Snackbar.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            String croppedImagePath = String.valueOf(resultUri);
            Intent intent = new Intent(this, FinishCreatePostActivity.class);
            intent.putExtra("DESTINATION_URI", croppedImagePath);
            startActivity(intent);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
    }
}