package com.example.phantom.onlineshop.other;

import android.content.Context;
import android.widget.ImageView;

import com.example.phantom.onlineshop.R;
import com.squareup.picasso.Picasso;

public class PicassoClient {

    public static void downloadImage(Context context, String imageUrl, ImageView img) {
        if (imageUrl != null && imageUrl.length() > 15) {
            com.squareup.picasso.Picasso.with(context).load(imageUrl).placeholder(R.drawable.placeholder).into(img);
        } else {
            com.squareup.picasso.Picasso.with(context).load(R.drawable.placeholder).into(img);
        }
    }
}
