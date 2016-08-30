package com.example.phantom.onlineshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.phantom.onlineshop.models.PostList;
import com.example.phantom.onlineshop.other.PicassoDetailedClient;

import java.util.ArrayList;

public class DetailedActivity extends Activity {
    public static final String KEY_NAME = "key_name";
    public static final String KEY_WEIGHT = "key_weight";
    public static final String KEY_PRICE = "key_price";
    public static final String KEY_IMAGE_URL = "key_image_url";
    public static final String KEY_DESCRIPTION = "key_description";
    private String name, weight, price, imageUrl, description, categoryId;
    private TextView nameTv, weightTv, priceTv, descriptionTv, categoryIdTv;
    private ImageView imageView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        ArrayList<PostList> postLists = MainActivity.getPostLists();
        Intent intent = getIntent();
        name = intent.getExtras().getString(KEY_NAME);
        weight = intent.getExtras().getString(KEY_WEIGHT);
        price = intent.getExtras().getString(KEY_PRICE);
        imageUrl = intent.getExtras().getString(KEY_IMAGE_URL);
        description = intent.getExtras().getString(KEY_DESCRIPTION);
        initViews();
        initBinds();
    }

    private void initBinds() {
        nameTv.setText(name);
        weightTv.setText(weight);
        priceTv.setText(price);
        descriptionTv.setText(description);
        categoryIdTv.setText(categoryId);
        if (imageUrl.length() > 15) {
            PicassoDetailedClient.downloadImage(context, imageUrl, imageView);
        }
    }

    private void initViews() {
        nameTv = (TextView) findViewById(R.id.detailed_name);
        weightTv = (TextView) findViewById(R.id.detailed_weight);
        priceTv = (TextView) findViewById(R.id.detailed_price);
        imageView = (ImageView) findViewById(R.id.detailed_image);
        descriptionTv = (TextView) findViewById(R.id.detailed_description);
        categoryIdTv = (TextView) findViewById(R.id.detailed_categoryId);
    }

    public static String getKeyName() {
        return KEY_NAME;
    }

    public static String getKeyWeight() {
        return KEY_WEIGHT;
    }

    public static String getKeyPrice() {
        return KEY_PRICE;
    }

    public static String getKeyImageUrl() {
        return KEY_IMAGE_URL;
    }

    public static String getKeyDescription() {
        return KEY_DESCRIPTION;
    }

}
