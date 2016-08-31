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
import com.example.phantom.onlineshop.adapters.RecyclerViewAdapter;
import com.example.phantom.onlineshop.models.PostList;

import java.util.ArrayList;

public class CategoryListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_salad, container, false);

        Bundle bundle = getArguments();
        String catId = bundle.getString("ID");

        ArrayList<PostList> postLists = MainActivity.getPostLists();
        ArrayList<PostList> sortedPosts = MainActivity.sortArray(catId, postLists);

        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.salad_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), sortedPosts);
        recyclerView.setAdapter(adapter);
        return layout;
    }
}
