package com.example.phantom.onlineshop;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.phantom.onlineshop.adapters.DrawerAdapter;
import com.example.phantom.onlineshop.adapters.Header;
import com.example.phantom.onlineshop.database.Model;
import com.example.phantom.onlineshop.database.ModelDatabase;
import com.example.phantom.onlineshop.fragments.ContactsFragment;
import com.example.phantom.onlineshop.fragments.TopFragment;
import com.example.phantom.onlineshop.models.Offer;
import com.example.phantom.onlineshop.models.OffersResponse;
import com.example.phantom.onlineshop.notification.AlarmReceiver;
import com.example.phantom.onlineshop.rest.ApiClient;
import com.example.phantom.onlineshop.rest.ApiService;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle drawerToggle;
    private String[] drawerTitles;
    private RecyclerView drawerRecyclerView;
    private DrawerLayout drawerLayout;
    private ProgressDialog pd;
    private static ArrayList<Offer> offerList;
    private Fragment fragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlowManager.init(new FlowConfig.Builder(getApplicationContext()).build());
        initViews();
        initTopFragment();
        initDrawer();
        initActionBar();
        setAlarmNotification();//every 24 hours thanks user for using our service
        //Have to check update parsing page to create option of downloading new data or receive it from our db
        //Looking for an answer
//        Delete.table(Model.class);
//temporary option, if db exist app just receive data from db, else download new data

        DatabaseDefinition database = FlowManager.getDatabase(ModelDatabase.class);
        database.executeTransaction(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                List<Model> listModel = new Select()
                        .from(Model.class)
                        .queryList();
                listModel.size();
                if (listModel.size() == 0) {
                    initRestXML();
                }
            }
        });
    }

    public void setAlarmNotification() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);

        Calendar alarmStartTime = Calendar.getInstance();
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 17);
        alarmStartTime.set(Calendar.MINUTE, 0);
        alarmStartTime.set(Calendar.SECOND, 0);
        alarmManager.setRepeating(AlarmManager.RTC, alarmStartTime.getTimeInMillis(), getInterval(), pendingIntent);
    }

    private int getInterval() {
        int days = 1, hours = 24, minutes = 60, seconds = 60, milliseconds = 1000;
        return days * hours * minutes * seconds * milliseconds;
    }

    private void initTopFragment() {
        fragment = new TopFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    private void fillDB(ArrayList<Offer> offerList) throws NullPointerException {
        String url, name, price, description, picture, weight, categoryId;
        for (int i = 0; i < offerList.size(); i++) {
            url = offerList.get(i).getUrl();
            name = offerList.get(i).getName();
            price = offerList.get(i).getPrice();
            description = offerList.get(i).getDescription();
            picture = offerList.get(i).getPicture();
            categoryId = offerList.get(i).getCategory();
            //I have a problem with getting paramMap because some offer hasn't this field.
            //Should check it. Trying different options.
            weight = "";
            Model model = new Model(url, name, price, description, picture, categoryId, weight);
            model.async().save();
        }
//          if (!offerList.get(i).getParamMap().isEmpty()) {
//          weight = offerList.get(i).getParamMap().get("Вес");}
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    private void initDrawer() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        drawerRecyclerView.setLayoutManager(layoutManager);
        DrawerAdapter adapter = new DrawerAdapter(this, getHeader(), drawerTitles);
        drawerRecyclerView.setAdapter(adapter);
        adapter.setListener(new DrawerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                switch (position) {
                    case 2:
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
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer) {
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

    public Header getHeader() {
        return new Header();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        if (id == R.id.menu_list) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}