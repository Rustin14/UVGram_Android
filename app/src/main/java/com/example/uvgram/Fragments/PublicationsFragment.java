package com.example.uvgram.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uvgram.Adapters.GridImagesAdapter;
import com.example.uvgram.R;

import java.util.ArrayList;

public class PublicationsFragment extends Fragment{

    Context context;
    GridView gridView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        gridView = getView().findViewById(R.id.photosGridView);
        gridView.setAdapter(new GridImagesAdapter(getContext()));
    }

    public ArrayList<Bitmap> createImagesList() {
        ArrayList<Integer> imageList = new ArrayList<>();
        ArrayList<Bitmap> bitmapArrayList = new ArrayList<>();

        imageList.add(R.drawable.pxl_20221014_215725107_crop);
        imageList.add(R.drawable.pxl_20221014_215725107);
        imageList.add(R.drawable.pxl_20221001_231154257_crop);
        imageList.add(R.drawable.pxl_20221001_231154257);
        for (int i = 0; i < imageList.size(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                    imageList.get(i));
            bitmap = cropToSquare(bitmap);
            bitmapArrayList.add(bitmap);
        }
        return bitmapArrayList;
    }

    public void createImageViews () {
        ArrayList<Bitmap> imageList = createImagesList();
        ListAdapter adapter = gridView.getAdapter();
        for (int i = 0; i < imageList.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageBitmap(imageList.get(i));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publications, container, false);
    }

    public static Bitmap cropToSquare(Bitmap bitmap){
        int width  = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = (height > width) ? width : height;
        int newHeight = (height > width)? height - ( height - width) : height;
        int cropW = (width - height) / 2;
        cropW = (cropW < 0)? 0: cropW;
        int cropH = (height - width) / 2;
        cropH = (cropH < 0)? 0: cropH;
        Bitmap cropImg = Bitmap.createBitmap(bitmap, cropW, cropH, newWidth, newHeight);

        return cropImg;
    }


}