package com.example.phantom.onlineshop.rest;

import com.example.phantom.onlineshop.models.OffersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET(NetworkConstants.GET_OFFERS)
    Call<OffersResponse>getOffers();
}
