package com.example.phantom.onlineshop.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "offer", strict = false)
public class Offer {
    @Element(required = false, name = "url")
    private String url;
    @Element(required = false, name = "name")
    private String name;
    @Element(required = false, name = "price")
    private String price;
    @Element(required = false, name = "description")
    private String description;
    @Element(required = false, name = "picture")
    private String picture;
    @Element(required = false, name = "categoryId")
    private String categoryId;
    @ElementList(required = false, inline = true)
    private ArrayList<Param> paramList;

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public ArrayList<Param> getParamList() {
        return paramList;
    }

    public static String getWeight_text() {
        return Param.getWeightText();
    }

}
