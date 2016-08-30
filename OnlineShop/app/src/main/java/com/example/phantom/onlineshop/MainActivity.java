package com.example.phantom.onlineshop;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.phantom.onlineshop.fragments.ContactsFragment;
import com.example.phantom.onlineshop.fragments.TopFragment;
import com.example.phantom.onlineshop.models.PostList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
    private int currentPosition = 0;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    public static final String TAG = "TAG";
    private final static String urlAddress = "http://ufa.farfor.ru/getyml/?key=ukAXxeJYZN";
    OkHttpClient client = new OkHttpClient();
    private ProgressDialog pd;
    private static ArrayList<PostList> postLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();
        initPosition(savedInstanceState);
        initActionBar();
        startLoadingData();
    }

    private void initPosition(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("position");
        } else {
            selectItem(0);
        }
    }

    private void startLoadingData() {
        postLists = new ArrayList<>();
        pd = ProgressDialog.show(MainActivity.this, "please wait", "downloading...");
        new Thread(r).start();
    }

    private void initActionBar() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
        }
    };

    Runnable r = new Runnable() {
        @Override
        public void run() {
            try {
                final String xml = get(urlAddress);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        parse(xml);
                        Log.d(TAG, "startLoadingData: " + "parsing is complete");
                    }
                });
                handler.sendEmptyMessage(0);
            } catch (IOException e) {
                Log.d(TAG, "IOException: " + e.toString());
                pd.dismiss();
            }
        }
    };

    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public void parse(String xml) {
        String name = "";
        String weight = "";
        String price = "";
        String imageUrl = "";
        String description = "";
        String categoryId = "";
        try {
            XmlPullParser parser = prepareXpp(xml);
            Boolean isSiteMeta = true;
            String tagValue = null;
            int event = parser.getEventType();
            postLists.clear();
            do {
                String tagName = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("offer")) {
                            isSiteMeta = false;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        tagValue = parser.getText();
                    default:
                        break;
                    case XmlPullParser.END_TAG:
                        if (!isSiteMeta) {
                            if (tagName.equalsIgnoreCase("name")) {
                                name = tagValue;
                            } else if (tagName.equalsIgnoreCase("picture")) {
                                imageUrl = tagValue;
                            } else if (tagName.equalsIgnoreCase("price")) {
                                price = tagValue;
                            } else if (tagName.equalsIgnoreCase("description")) {
                                description = tagValue;
                            } else if (tagName.equalsIgnoreCase("param")) {
                                if (tagValue != null && tagValue.contains("гр")) {
                                    weight = tagValue;
                                }
                            } else if (tagName.equalsIgnoreCase("categoryId")) {
                                categoryId = tagValue;
                            }
                        }
                        if (tagName.equalsIgnoreCase("offer")) {
                            postLists.add(new PostList(name, weight, price,
                                    imageUrl, description, categoryId));
                            isSiteMeta = true;
                        }
                        break;
                }
                event = parser.next();
            } while (event != XmlPullParser.END_DOCUMENT);
        } catch (Exception e) {
            Log.d(TAG, "Exception: " + e.toString());
        }
    }

    private XmlPullParser prepareXpp(String rss) throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(rss));
        return parser;
    }

    private void initDrawer() {
        String[] drawerTitles = getResources().getStringArray(R.array.drawerTitles);
        drawerList = (ListView) findViewById(R.id.drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", currentPosition);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
        return super.onPrepareOptionsMenu(menu);
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
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_contacts:
                ContactsFragment contactsFragment = new ContactsFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, contactsFragment, contactsFragment.getClass().getSimpleName())
                        .addToBackStack(contactsFragment.getClass().getSimpleName())
                        .commitAllowingStateLoss();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(drawerList);
    }

    public static ArrayList<PostList> sortArray(String categoryId, ArrayList<PostList> postLists) {
        ArrayList<PostList> posts = new ArrayList<>();
        for (int i = 0; i < postLists.size(); i++) {
            if ((postLists.get(i).getCategoryId()).equals(categoryId)) {
                posts.add(postLists.get(i));
            }
        }
        return posts;
    }

    public static ArrayList<PostList> getPostLists() {
        return postLists;
    }
}