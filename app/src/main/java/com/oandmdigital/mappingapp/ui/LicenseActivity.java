package com.oandmdigital.mappingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.oandmdigital.mappingapp.R;

public class LicenseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_license);

        TextView mLicense = (TextView) findViewById(R.id.license_terms);
        mLicense.setText(GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(this));

    }

}
