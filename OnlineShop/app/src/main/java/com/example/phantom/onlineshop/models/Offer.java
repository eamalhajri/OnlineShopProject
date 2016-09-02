package com.example.phantom.onlineshop.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.Map;

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
    @ElementMap(entry = "param", key = "name", attribute = true, required = false, inline = true)
    private Map<String, String> paramMap;

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

    public Map<String, String> getParamMap() {
        return paramMap;
    }
}
