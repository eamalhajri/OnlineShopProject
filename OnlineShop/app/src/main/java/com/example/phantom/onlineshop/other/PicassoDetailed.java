package com.example.phantom.onlineshop.other;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoDetailed {

    public static void downloadImage(Context context, String imageUrl, ImageView img) {
        Picasso.with(context).load(imageUrl).into(img);
    }
}