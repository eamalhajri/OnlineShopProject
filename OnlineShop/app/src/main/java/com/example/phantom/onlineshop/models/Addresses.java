package com.example.phantom.onlineshop.models;

import com.example.phantom.onlineshop.R;

public class Addresses {
    private static final String ADDRESS_CAFE_1 = "г. Минск, ул. Петра Глебки, 5";
    private static final String ADDRESS_CAFE_2 = "г. Минск, ул. Веры Хоружей, 8";
    private static final String ADDRESS_CAFE_3 = "г. Минск, ул. Воронянского, 31";
    private static final String ADDRESS_CAFE_4 = "г. Минск, ул. Энгельса, 16";
    private String name;
    private int imdResId;

    public static final Addresses[] addresses = {
            new Addresses(ADDRESS_CAFE_4, R.drawable.address_01),
            new Addresses(ADDRESS_CAFE_1, R.drawable.address_02),
            new Addresses(ADDRESS_CAFE_3, R.drawable.address_03),
            new Addresses(ADDRESS_CAFE_2, R.drawable.address_04),
    };

    private Addresses(String name, int imgResId) {
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
}
