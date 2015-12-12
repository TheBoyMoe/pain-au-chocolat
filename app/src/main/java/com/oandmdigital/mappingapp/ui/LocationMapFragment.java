package com.oandmdigital.mappingapp.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oandmdigital.mappingapp.R;

public class LocationMapFragment extends Fragment{


    public LocationMapFragment() {}

    public static LocationMapFragment newInstance() {
        return new LocationMapFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_location_map, container, false);

        return view;
    }


}
