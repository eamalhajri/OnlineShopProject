package com.example.phantom.onlineshop;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phantom.onlineshop.models.Category;

import org.w3c.dom.Text;

public class CategoryActivity extends Activity {
    public static final String EXTRA_CATEGORY = "categoryNo";
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        initActionBar();
        initViews();
        initBinds();
    }

    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViews() {
        textView = (TextView) findViewById(R.id.category_text);
        imageView = (ImageView) findViewById(R.id.category_image);
    }

    private void initBinds() {
        int categoryNo = getIntent().getIntExtra(EXTRA_CATEGORY, 0);
        int categoryImage = Category.categories[categoryNo].getImdResId();
        String categoryName = Category.categories[categoryNo].getName();
        textView.setText(categoryName);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, categoryImage));
        imageView.setContentDescription(categoryName);
    }
}
