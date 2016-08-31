package com.example.phantom.onlineshop.models;

import com.example.phantom.onlineshop.R;

public class Category {
    public static final String CATEGORY_SOUP = "Супы";
    public static final String CATEGORY_PIZZA = "Пицца";
    public static final String CATEGORY_DESSERT = "Десерты";
    public static final String CATEGORY_SALAD = "Салаты";
    private String name;
    private int imdResId;

    public static final Category[] categories = {
            new Category(CATEGORY_SALAD, R.drawable.salad),
            new Category(CATEGORY_SOUP, R.drawable.soup),
            new Category(CATEGORY_DESSERT, R.drawable.dessert),
            new Category(CATEGORY_PIZZA, R.drawable.pizza),
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
