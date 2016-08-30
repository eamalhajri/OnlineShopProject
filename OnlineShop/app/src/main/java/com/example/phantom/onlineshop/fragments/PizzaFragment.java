package com.example.phantom.onlineshop.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.phantom.onlineshop.MainActivity;
import com.example.phantom.onlineshop.R;
import com.example.phantom.onlineshop.adapters.RVCustomAdapter;
import com.example.phantom.onlineshop.models.PostList;

import java.util.ArrayList;

public class PizzaFragment extends Fragment {
    private final static String CATEGORY_ID = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_pizza, container, false);

        ArrayList<PostList> postLists = MainActivity.getPostLists();
        ArrayList<PostList> sortedPosts = MainActivity.sortArray(CATEGORY_ID, postLists);

        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.pizza_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RVCustomAdapter adapter = new RVCustomAdapter(getActivity(), sortedPosts);
        recyclerView.setAdapter(adapter);
        return layout;
    }
}