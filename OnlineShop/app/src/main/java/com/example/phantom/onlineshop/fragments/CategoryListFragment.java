package com.example.phantom.onlineshop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.phantom.onlineshop.MainActivity;
import com.example.phantom.onlineshop.R;
import com.example.phantom.onlineshop.adapters.OffersAdapter;
import com.example.phantom.onlineshop.models.Offer;

import java.util.ArrayList;

public class CategoryListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_category_list, container, false);
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.category_list_recycler_view);

        Bundle bundle = getArguments();
        String catId = bundle.getString("ID");

        ArrayList<Offer> offers = MainActivity.getOfferList();
        ArrayList<Offer> sortedPosts = MainActivity.sortArray(catId, offers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        OffersAdapter adapter = new OffersAdapter(getActivity(), sortedPosts);
        recyclerView.setAdapter(adapter);
        return layout;
    }
}
