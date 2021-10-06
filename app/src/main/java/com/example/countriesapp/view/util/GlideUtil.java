package com.example.countriesapp.view.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.countriesapp.R;

public class GlideUtil {

    public static void loadImage(ImageView imageView, String imageUrl, CircularProgressDrawable circularDrawable){
        RequestOptions options = new RequestOptions()
                .placeholder(circularDrawable)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(imageView);
    }

    public static CircularProgressDrawable getCircularDrawable(Context context){
        CircularProgressDrawable circularDrawable = new CircularProgressDrawable(context);
        circularDrawable.setStrokeWidth(10f);
        circularDrawable.setCenterRadius(50f);
        circularDrawable.start();

        return circularDrawable;
    }


}
