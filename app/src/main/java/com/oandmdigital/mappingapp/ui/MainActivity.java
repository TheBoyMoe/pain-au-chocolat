package com.oandmdigital.mappingapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.oandmdigital.mappingapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        if(getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, LocationListViewFragment.newInstance())
                    .commit();
        }

    }


}
