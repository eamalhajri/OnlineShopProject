package com.example.phantom.onlineshop.other;

import android.content.Context;
import android.widget.ImageView;

import com.example.phantom.onlineshop.R;
import com.squareup.picasso.Picasso;

public class PicassoClient {

    public static void downloadImage(Context context, String imageUrl, ImageView img) {
        if (imageUrl != null && imageUrl.length() > 15) {
            Picasso.with(context).load(imageUrl).placeholder(R.drawable.placeholder).into(img);
        } else {
            Picasso.with(context).load(R.drawable.placeholder).into(img);
        }
    }
}
