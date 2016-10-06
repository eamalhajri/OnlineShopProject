package com.example.phantom.onlineshop.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.phantom.onlineshop.R;
import com.example.phantom.onlineshop.adapters.CategoriesAdapter;
import com.example.phantom.onlineshop.models.Category;

public class TopFragment extends Fragment {
    public static final int NUMBERS_OF_CATEGORIES = 4;
    private final static String CATEGORY_ID_SALAD = "7";
    private final static String CATEGORY_ID_DESSERT = "10";
    private final static String CATEGORY_ID_PIZZA = "1";
    private final static String CATEGORY_ID_SOUP = "6";
    private Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_top, container, false);
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.top_recycler);

        Context context = getActivity();
        String[] categoryNames = new String[NUMBERS_OF_CATEGORIES];
        for (int i = 0; i < NUMBERS_OF_CATEGORIES; i++) {
            categoryNames[i] = Category.categories[i].getName();
        }
        int[] categoryImages = new int[NUMBERS_OF_CATEGORIES];
        for (int i = 0; i < NUMBERS_OF_CATEGORIES; i++) {
            categoryImages[i] = Category.categories[i].getImdResId();
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        CategoriesAdapter adapter = new CategoriesAdapter(categoryNames, categoryImages, context);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new CategoriesAdapter.Listener() {
            public void onClick(int position) {
               Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        fragment = new CategoryListFragment();
                        bundle.putString("ID", CATEGORY_ID_SALAD);
                        break;
                    case 1:
                        fragment = new CategoryListFragment();
                        bundle.putString("ID", CATEGORY_ID_SOUP);
                        break;
                    case 2:
                        fragment = new CategoryListFragment();
                        bundle.putString("ID", CATEGORY_ID_DESSERT);
                        break;
                    case 3:
                        fragment = new CategoryListFragment();
                        bundle.putString("ID", CATEGORY_ID_PIZZA);
                        break;
                    default:
                        break;
                }
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .addToBackStack(fragment.getClass().getSimpleName())
                        .commitAllowingStateLoss();
            }
        });
        return layout;
    }
}