package com.example.phantom.onlineshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.phantom.onlineshop.other.DataForDetailedActivity;
import com.squareup.picasso.Picasso;

public class DetailedActivity extends Activity {
    public static final String KEY_NAME = "key_name";
    public static final String KEY_WEIGHT = "key_weight";
    public static final String KEY_PRICE = "key_price";
    public static final String KEY_IMAGE_URL = "key_image_url";
    public static final String KEY_DESCRIPTION = "key_description";
    private String name, weight, price, imageUrl, description;
    private TextView nameTv, weightTv, priceTv, descriptionTv;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        initViews();
        getExtras();
        initBinds();
    }

    private void getExtras() {
        DataForDetailedActivity data = getIntent().getParcelableExtra("DATA");
        name = data.keyName;
        weight = data.keyWeight;
        price = data.keyPrice;
        imageUrl = data.keyImageUrl;
        description = data.keyDescription;
    }

    private void initViews() {
        nameTv = (TextView) findViewById(R.id.detailed_name);
        weightTv = (TextView) findViewById(R.id.detailed_weight);
        priceTv = (TextView) findViewById(R.id.detailed_price);
        imageView = (ImageView) findViewById(R.id.detailed_image);
        descriptionTv = (TextView) findViewById(R.id.detailed_description);
    }

    private void initBinds() {
        nameTv.setText(name);
        weightTv.setText(weight);
        priceTv.setText(price);
        descriptionTv.setText(description);
        if (imageUrl.length() > 15) {
            Picasso.with(this)
                    .load(imageUrl)
                    .into(imageView);
        }
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
