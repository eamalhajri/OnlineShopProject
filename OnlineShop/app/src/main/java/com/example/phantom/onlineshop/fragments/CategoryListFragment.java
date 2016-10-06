package com.example.phantom.onlineshop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.phantom.onlineshop.R;
import com.example.phantom.onlineshop.adapters.OffersAdapter;
import com.example.phantom.onlineshop.database.Model;
import com.example.phantom.onlineshop.database.ModelDatabase;
import com.example.phantom.onlineshop.database.Model_Table;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;

import java.util.List;

public class CategoryListFragment extends Fragment {
    private String catId;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_category_list, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.category_list_recycler_view);

        Bundle bundle = getArguments();
        catId = bundle.getString("ID");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DatabaseDefinition database = FlowManager.getDatabase(ModelDatabase.class);
        database.executeTransaction(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                List<Model> listModel = new Select()
                        .from(Model.class)
                        .where(Model_Table.categoryId.is(catId))
                        .queryList();
                OffersAdapter adapter = new OffersAdapter(getActivity(), listModel);
                recyclerView.setAdapter(adapter);
            }
        });
        return layout;
    }
}
