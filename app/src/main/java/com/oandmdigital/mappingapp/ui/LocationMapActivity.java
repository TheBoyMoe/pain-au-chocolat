package com.oandmdigital.mappingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.oandmdigital.mappingapp.R;

public class LocationMapActivity extends AppCompatActivity {

    // debug key: 33:8C:2D:25:63:24:78:4B:AE:D3:93:11:4E:E2:10:91:6A:B0:F1:1C
    // API key: AIzaSyDw8XaAVZN_60XuIAR1JxzAZuHEhEz4ONs


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map);


        if(getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, LocationMapFragment.newInstance())
                    .commit();
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}