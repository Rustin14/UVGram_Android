package com.example.uvgram.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.uvgram.R;

public class GridImagesAdapter extends BaseAdapter {

    private Context context;

    public int[] imagenesArray = {
            R.drawable.pxl_20221001_231154257,
            R.drawable.pxl_20221001_231154257_crop,
            R.drawable.pxl_20221014_215725107,
            R.drawable.pxl_20221014_215725107_crop,
            R.drawable.pxl_20221001_231154257,
            R.drawable.pxl_20221001_231154257_crop,
            R.drawable.pxl_20221014_215725107,
            R.drawable.pxl_20221014_215725107_crop,
            R.drawable.pxl_20221001_231154257,
            R.drawable.pxl_20221001_231154257_crop,
            R.drawable.pxl_20221014_215725107,
            R.drawable.pxl_20221014_215725107_crop
    };

    public GridImagesAdapter(Context mContext) {
        this.context = mContext;
    }

    @Override
    public int getCount() {
        return imagenesArray.length;
    }

    @Override
    public Object getItem(int i) {
        return imagenesArray[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imagenesArray[i]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(
                new GridView.LayoutParams(
                        340,
                        340
                )
        );
        return imageView;
    }
}
