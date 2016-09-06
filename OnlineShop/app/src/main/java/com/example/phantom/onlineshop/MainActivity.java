package com.example.phantom.onlineshop;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.phantom.onlineshop.fragments.ContactsFragment;
import com.example.phantom.onlineshop.fragments.TopFragment;
import com.example.phantom.onlineshop.models.Offer;
import com.example.phantom.onlineshop.models.OffersResponse;
import com.example.phantom.onlineshop.rest.ApiClient;
import com.example.phantom.onlineshop.rest.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
    private int currentPosition = 0;
    private ActionBarDrawerToggle drawerToggle;
    private String[] drawerTitles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ProgressDialog pd;
    private static ArrayList<Offer> offerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDrawer();
        initPosition(savedInstanceState);
        initActionBar();
        initRestXML();
    }

    private void initViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerTitles = getResources().getStringArray(R.array.drawer_titles);
        drawerList = (ListView) findViewById(R.id.drawer_list);
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
        drawerList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, drawerTitles));
        drawerList.setOnItemClickListener(this);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
    }

    private void initPosition(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("position");
        } else {
            selectItem(0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", currentPosition);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }

    private void selectItem(int position) {
        currentPosition = position;
        Fragment fragment;
        FragmentTransaction ft;
        switch (position) {
            case 1:
                ContactsFragment contactsFragment = new ContactsFragment();
                android.support.v4.app.Fragment rfragment = getSupportFragmentManager().findFragmentByTag(contactsFragment.getClass().getSimpleName());
                if (rfragment != null) {
                    getSupportFragmentManager().popBackStackImmediate(rfragment.getClass().getSimpleName(), 0);
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, contactsFragment, contactsFragment.getClass().getSimpleName())
                        .addToBackStack(contactsFragment.getClass().getSimpleName())
                        .commitAllowingStateLoss();
                break;
            default:
                fragment = new TopFragment();
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(fragment.getClass().getSimpleName())
                        .commitAllowingStateLoss();
                break;
        }
        drawerLayout.closeDrawer(drawerList);
    }

    public static ArrayList<Offer> sortArray(String categoryId, ArrayList<Offer> offerLists) {
        ArrayList<Offer> offers = new ArrayList<>();
        for (int i = 0; i < offerLists.size(); i++) {
            if ((offerLists.get(i).getCategoryId()).equals(categoryId)) {
                offers.add(offerLists.get(i));
            }
        }
        return offers;
    }

    public static ArrayList<Offer> getOfferList() {
        return offerList;
    }


}