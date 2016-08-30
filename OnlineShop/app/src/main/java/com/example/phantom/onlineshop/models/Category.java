package com.example.phantom.onlineshop.models;

import com.example.phantom.onlineshop.other.Constants;
import com.example.phantom.onlineshop.R;

public class Category {
    private String name;
    private int imdResId;

    public static final Category[] categories = {
            new Category(Constants.CATEGORY_SALAD, R.drawable.salad),
            new Category(Constants.CATEGORY_SOUP, R.drawable.soup),
            new Category(Constants.CATEGORY_DESSERT, R.drawable.dessert),
            new Category(Constants.CATEGORY_PIZZA, R.drawable.pizza),
    };

    public Category(String name, int imgResId) {
        this.name = name;
        this.imdResId = imgResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImdResId() {
        return imdResId;
    }

    public void setImdResId(int imdResId) {
        this.imdResId = imdResId;
    }
}
