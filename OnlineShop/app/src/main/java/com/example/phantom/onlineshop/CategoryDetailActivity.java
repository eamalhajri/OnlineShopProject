package com.example.phantom.onlineshop;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phantom.onlineshop.models.Category;

public class CategoryDetailActivity extends Activity {
    public static final String EXTRA_CATEGORY = "categoryNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        int categoryNo = (Integer)getIntent().getExtras().get(EXTRA_CATEGORY);
        String categoryName = Category.categories[categoryNo].getName();
        TextView textView = (TextView)findViewById(R.id.category_text);
        textView.setText(categoryName);
        int categoryImage = Category.categories[categoryNo].getImdResId();
        ImageView imageView = (ImageView)findViewById(R.id.category_image);
        imageView.setImageDrawable(getResources().getDrawable(categoryImage));
        imageView.setContentDescription(categoryName);
    }

}
