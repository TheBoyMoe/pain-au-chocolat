package com.oandmdigital.mappingapp.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oandmdigital.mappingapp.R;

public class CustomMapFragment extends Fragment {

    private View mMapView;


    public CustomMapFragment() {}

    public static CustomMapFragment newInstance() {
        return new CustomMapFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMapView = inflater.inflate(R.layout.content_location_map, container, false);

        return mMapView;
    }






}
