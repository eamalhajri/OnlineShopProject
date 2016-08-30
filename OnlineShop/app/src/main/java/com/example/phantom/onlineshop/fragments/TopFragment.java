package com.example.phantom.onlineshop.fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.phantom.onlineshop.other.Constants;
import com.example.phantom.onlineshop.R;
import com.example.phantom.onlineshop.adapters.MainScreenAdapter;
import com.example.phantom.onlineshop.models.Category;

public class TopFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_top, container, false);
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.top_recycler);
        String[] categoryNames = new String[Constants.NUMBERS_OF_CATEGORIES];
        for (int i = 0; i < Constants.NUMBERS_OF_CATEGORIES; i++) {
            categoryNames[i] = Category.categories[i].getName();
        }
        int[] categoryImages = new int[Constants.NUMBERS_OF_CATEGORIES];
        for (int i = 0; i < Constants.NUMBERS_OF_CATEGORIES; i++) {
            categoryImages[i] = Category.categories[i].getImdResId();
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        MainScreenAdapter adapter = new MainScreenAdapter(categoryNames, categoryImages);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new MainScreenAdapter.Listener() {
            public void onClick(int position) {
                Fragment fragment;
                FragmentTransaction ft;
                switch (position) {
                    case 0:
                        fragment = new SaladFragment();
                        ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                        break;
                    case 1:
                        fragment = new SoupFragment();
                        ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                        break;
                    case 2:
                        fragment = new DessertFragment();
                        ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                        break;
                    case 3:
                        fragment = new PizzaFragment();
                        ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                        break;
                    default:
                        break;
                }
            }
        });
        return layout;
    }
}