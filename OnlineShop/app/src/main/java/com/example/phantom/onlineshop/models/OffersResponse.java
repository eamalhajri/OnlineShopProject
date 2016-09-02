package com.example.phantom.onlineshop.models;


import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "yml_catalog", strict = false)
public class OffersResponse {
    @Path("shop/offers")
    @ElementList(entry = "offer",inline = true)
    private static ArrayList<Offer> offerList;

    public ArrayList<Offer> getOfferList() {
        return offerList;
    }
}