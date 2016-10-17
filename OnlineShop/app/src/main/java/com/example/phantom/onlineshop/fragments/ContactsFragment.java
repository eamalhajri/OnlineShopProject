package com.example.phantom.onlineshop.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.phantom.onlineshop.R;
import com.example.phantom.onlineshop.adapters.AddressesAdapter;
import com.example.phantom.onlineshop.adapters.CategoriesAdapter;
import com.example.phantom.onlineshop.models.Addresses;
import com.example.phantom.onlineshop.models.Category;

public class ContactsFragment extends Fragment {
    public static final int NUMBERS_OF_ADDRESSES = 4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_contacts,
                container, false);
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.contacts_recycler);

        Context context = getActivity();

        String[] addressesNames = new String[NUMBERS_OF_ADDRESSES];
        for (int i = 0; i < NUMBERS_OF_ADDRESSES; i++) {
            addressesNames[i] = Addresses.addresses[i].getName();
        }
        int[] addressesImages = new int[NUMBERS_OF_ADDRESSES];
        for (int i = 0; i < NUMBERS_OF_ADDRESSES; i++) {
            addressesImages[i] = Addresses.addresses[i].getImdResId();
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        AddressesAdapter adapter = new AddressesAdapter(addressesNames, addressesImages, context);
        recyclerView.setAdapter(adapter);

        return layout;
    }
}
