package com.example.phantom.onlineshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phantom.onlineshop.other.DataForDetailedActivity;
import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {
    public static final String KEY_DATA = "DATA";
    private String name, weight, price, imageUrl, description;
    private TextView nameTv, weightTv, priceTv, descriptionTv;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        initViews();
        getParcelableExtra();
        initBinds();
        initActionBar();
    }

    private void initActionBar() {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
            }
    }

    private void getParcelableExtra() {
        DataForDetailedActivity data = getIntent().getParcelableExtra(KEY_DATA);
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
}
