package com.oandmdigital.mappingapp.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.event.LocationClickEvent;
import com.oandmdigital.mappingapp.model.Shop;
import com.oandmdigital.mappingapp.model.ShopData;

import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class CustomMapFragment extends MapFragment implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMapClickListener{

    private View mMapView;
    private boolean mFirstTimeIn;
    private LatLngBounds.Builder mBuilder = new LatLngBounds.Builder();
    private HashMap<String, Shop> mStores = new HashMap<>();


    public CustomMapFragment() {}

    public static CustomMapFragment newInstance() {
        return new CustomMapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMapView = super.onCreateView(inflater, container, savedInstanceState);

        return mMapView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState == null)
            mFirstTimeIn = true;

        getMapAsync(this);
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        // add the markers to the map
        addMarkers(googleMap, ShopData.list);

        // center and zoom the map to encompass all markers
        if(mFirstTimeIn) {
            mMapView.post(new Runnable() {
                @Override
                public void run() {
                    CameraUpdate locations = CameraUpdateFactory.newLatLngBounds(mBuilder.build(), 64);
                    googleMap.moveCamera(locations);
                }
            });
        }

        // set the clickListeners
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMapClickListener(this);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        // post the corresponding location object to the bus
        EventBus.getDefault().post(new LocationClickEvent(mStores.get(marker.getId()), LocationClickEvent.MAP_MARKER_CLICK_EVENT));
        return true; // disable info window
    }

    @Override
    public void onMapClick(LatLng latLng) {
        // hide the popup when clicking anywhere else on the map
        EventBus.getDefault().post(new LocationClickEvent(null, LocationClickEvent.MAP_CLICK_EVENT));
    }


    private void addMarkers(GoogleMap googleMap, Shop[] list) {
        Marker marker = null;

        for (Shop location : list) {
            marker = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .title(location.getName())
                        .snippet(String.format("%s %s %s",
                                location.getAddress().getStreet(),
                                location.getAddress().getArea(),
                                location.getAddress().getPostalCode()))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker))
            );

            // add each marker to the LatLng Bounds obj
            mBuilder.include(marker.getPosition());

            // add each location to a HashMap, using the marker id as the key
            mStores.put(marker.getId(), location);
        }
    }



}
