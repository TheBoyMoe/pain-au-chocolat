package com.oandmdigital.mappingapp.ui;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.event.FabOnCLick;

import de.greenrobot.event.EventBus;

public class LocationMapFragment extends Fragment {

    private boolean mStandardView = true;
    private View mMapView;


    public LocationMapFragment() {}

    public static LocationMapFragment newInstance() {
        return new LocationMapFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMapView = inflater.inflate(R.layout.content_location_map, container, false);

        return mMapView;
    }


    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(FabOnCLick event) {
        if(event.isOnClick()) {
            if(mStandardView) {
                mStandardView = false;
                // TODO switch to hybrid view
                Snackbar.make(mMapView, "Switching to Hybrid view", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            } else {
                mStandardView = true;
                // TODO switch to standard view
                Snackbar.make(mMapView, "Switching to Normal view", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }
    }



}
