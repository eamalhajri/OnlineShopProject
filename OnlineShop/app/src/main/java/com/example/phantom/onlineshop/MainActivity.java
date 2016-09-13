package com.example.phantom.onlineshop;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.phantom.onlineshop.adapters.DrawerAdapter;
import com.example.phantom.onlineshop.database.Model;
import com.example.phantom.onlineshop.fragments.ContactsFragment;
import com.example.phantom.onlineshop.fragments.TopFragment;
import com.example.phantom.onlineshop.models.Offer;
import com.example.phantom.onlineshop.models.OffersResponse;
import com.example.phantom.onlineshop.rest.ApiClient;
import com.example.phantom.onlineshop.rest.ApiService;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends FragmentActivity {
    private ActionBarDrawerToggle drawerToggle;
    private String[] drawerTitles;
    private RecyclerView drawerRecyclerView;
    private DrawerLayout drawerLayout;
    private ProgressDialog pd;
    private static ArrayList<Offer> offerList;
    private Fragment fragment;
    private static List<Model> listModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlowManager.init(new FlowConfig.Builder(getApplicationContext()).build());
        initViews();
        initTopFragment();
        initDrawer();
        initActionBar();
        Delete.table(Model.class);
        initRestXML();
    }

    private void initTopFragment() {
        fragment = new TopFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    private void fillDB(ArrayList<Offer> offerList) {
        String url, name, price, description, picture, weight = "", categoryId;
        for (int i = 0; i < offerList.size(); i++) {
            url = offerList.get(i).getUrl();
            name = offerList.get(i).getName();
            price = offerList.get(i).getPrice();
            description = offerList.get(i).getDescription();
            picture = offerList.get(i).getPicture();
            categoryId = offerList.get(i).getCategory();
            Model model = new Model(url, name, price, description, picture, categoryId, weight);
            model.save();
        }
        //  String keyWeight = (offerList.get(position).getParamMap().get("Вес"));
    }

    private void initViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerTitles = getResources().getStringArray(R.array.drawer_titles);
        drawerRecyclerView = (RecyclerView) findViewById(R.id.drawer_recycler_view);
    }

    private void initRestXML() {
        pd = ProgressDialog.show(MainActivity.this, "please wait", "downloading...");
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<OffersResponse> call = service.getOffers();
        call.enqueue(new Callback<OffersResponse>() {
            @Override
            public void onResponse(Call<OffersResponse> call, retrofit2.Response<OffersResponse> response) {
                offerList = response.body().getOfferList();
                Log.d("TAG", "onResponse: " + offerList.size());
                fillDB(offerList);
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<OffersResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.toString());
            }
        });
    }

    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void initDrawer() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        drawerRecyclerView.setLayoutManager(layoutManager);
        DrawerAdapter adapter = new DrawerAdapter(this, drawerTitles);
        drawerRecyclerView.setAdapter(adapter);
        adapter.setListener(new DrawerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                switch (position) {
                    case 1:
                        fragment = new ContactsFragment();
                        break;
                    default:
                        fragment = new TopFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(fragment.getClass().getSimpleName())
                        .commitAllowingStateLoss();
                drawerLayout.closeDrawer(drawerRecyclerView);
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}