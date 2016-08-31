package com.example.phantom.onlineshop.fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.phantom.onlineshop.R;
import com.example.phantom.onlineshop.adapters.CategoryAdapter;
import com.example.phantom.onlineshop.models.Category;

public class TopFragment extends Fragment {
    public static final int NUMBERS_OF_CATEGORIES = 4;
    private final static String CATEGORY_ID_SALAD = "7";
    private final static String CATEGORY_ID_DESSERT = "10";
    private final static String CATEGORY_ID_PIZZA = "1";
    private final static String CATEGORY_ID_SOUP = "6";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_top, container, false);

        Context context = getActivity();
        String[] categoryNames = new String[NUMBERS_OF_CATEGORIES];
        for (int i = 0; i < NUMBERS_OF_CATEGORIES; i++) {
            categoryNames[i] = Category.categories[i].getName();
        }
        int[] categoryImages = new int[NUMBERS_OF_CATEGORIES];
        for (int i = 0; i < NUMBERS_OF_CATEGORIES; i++) {
            categoryImages[i] = Category.categories[i].getImdResId();
        }

        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.top_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        CategoryAdapter adapter = new CategoryAdapter(categoryNames, categoryImages, context);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new CategoryAdapter.Listener() {
            public void onClick(int position) {
                Fragment fragment;
                FragmentTransaction ft;
                Bundle bundle;
                switch (position) {
                    case 0:
                        fragment = new CategoryListFragment();
                        bundle = new Bundle();
                        bundle.putString("ID", CATEGORY_ID_SALAD);
                        fragment.setArguments(bundle);
                        ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                        break;
                    case 1:
                        fragment = new CategoryListFragment();
                        bundle = new Bundle();
                        bundle.putString("ID", CATEGORY_ID_SOUP);
                        fragment.setArguments(bundle);
                        ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                        break;
                    case 2:
                        fragment = new CategoryListFragment();
                        bundle = new Bundle();
                        bundle.putString("ID", CATEGORY_ID_DESSERT);
                        fragment.setArguments(bundle);
                        ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                        break;
                    case 3:
                        fragment = new CategoryListFragment();
                        bundle = new Bundle();
                        bundle.putString("ID", CATEGORY_ID_PIZZA);
                        fragment.setArguments(bundle);
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