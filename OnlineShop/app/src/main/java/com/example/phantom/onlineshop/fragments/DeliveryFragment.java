package com.example.phantom.onlineshop.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phantom.onlineshop.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class DeliveryFragment extends Fragment implements OnMapReadyCallback {
    private static final int REQUEST_ACCESS_FINE_LOCATION = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        initMaps();
        return view;
    }

    private void initMaps() {
        ((SupportMapFragment) getChildFragmentManager().
                findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng city, address1, address2, address3, address4;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        city = new LatLng(53.903752, 27.564201);
        address1 = new LatLng(53.908757, 27.469890);
        address2 = new LatLng(53.920255, 27.577232);
        address3 = new LatLng(53.878631, 27.558539);
        address4 = new LatLng(53.900015, 27.562690);
        map.addMarker(new MarkerOptions().position(address1).title("Закусочная №1").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        map.addMarker(new MarkerOptions().position(address2).title("Закусочная №2").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        map.addMarker(new MarkerOptions().position(address3).title("Закусочная №3").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        map.addMarker(new MarkerOptions().position(address4).title("Закусочная №4").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(city, 10));
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_FINE_LOCATION);
            }
            return;
        }
        map.setMyLocationEnabled(true);
    }
}