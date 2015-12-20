package com.oandmdigital.mappingapp.ui;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.common.Utils;
import com.oandmdigital.mappingapp.event.LocationClickEvent;
import com.oandmdigital.mappingapp.model.Shop;
import com.oandmdigital.mappingapp.pager.CustomPagerAdapter;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        ViewPager.OnPageChangeListener{

    private CoordinatorLayout mCoordinatorLayout;
    private ViewPager mViewPager;
    private TabLayout mPagerTabs;
    private View mLocationWrapper;
    private View mLocationPopup;
    private Shop mLocation;
    private boolean mIsPopupVisible;
    private TextView mLocationName;
    private TextView mLocationAddress;
    private TextView mLocationRating;
    private ImageView mImageDirections;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        // cache the req'd elements
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mPagerTabs = (TabLayout) findViewById(R.id.tab_layout);
        mLocationPopup = findViewById(R.id.popup_layout);
        mLocationWrapper = findViewById(R.id.location_wrapper);
        mLocationName = (TextView) findViewById(R.id.location_name);
        mLocationAddress = (TextView) findViewById(R.id.location_address);
        mLocationRating = (TextView) findViewById(R.id.location_rating);
        mImageDirections = (ImageView) findViewById(R.id.image_directions);

        // set the OnCLickListeners
        mViewPager.addOnPageChangeListener(this);
        mImageDirections.setOnClickListener(this);
        mLocationWrapper.setOnClickListener(this);


        // setup the popup layout to pop in to view when req'd
        mLocationPopup.setVisibility(View.GONE);
        mLocationPopup.animate().translationY(300).alpha(0.0f);
        mIsPopupVisible = false;


        // set the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.app_name));
        }


        // check that the device can display maps
        int isPlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if(isPlayServicesAvailable == ConnectionResult.SUCCESS
                && (Utils.getVersionFromPackageManager(this) >=2)) {

            // set the ViewPager and TabLayout
            mViewPager.setAdapter(new CustomPagerAdapter(this, getFragmentManager()));
            mPagerTabs.setupWithViewPager(mViewPager);

        } else {
            Snackbar.make(mCoordinatorLayout,
                    "Google Maps not supported on this device", Snackbar.LENGTH_INDEFINITE).show();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }


    @SuppressWarnings("unused")
    public void onEventMainThread(LocationClickEvent event) {

        int eventType = event.getEventType();
        Shop location = event.getLocation();

        switch (eventType) {
            case LocationClickEvent.MAP_MARKER_CLICK_EVENT:
                Toast.makeText(this, "Clicked on map marker", Toast.LENGTH_LONG).show();
                break;
            case LocationClickEvent.MAP_CLICK_EVENT:
                Toast.makeText(this, "Clicked on map", Toast.LENGTH_LONG).show();
                break;

            case LocationClickEvent.LIST_ITEM_CLICK_EVENT:
                Toast.makeText(this, "Clicked on list item", Toast.LENGTH_LONG).show();
                break;
        }

//        Intent intent = new Intent(this, LocationDetailActivity.class);
//        intent.putExtra(LocationDetailActivity.SHOP_PARCELABLE, event.getShop());
//        intent.putExtra(LocationDetailActivity.ITEM_POSITION, event.getPosition());
//        startActivity(intent);



    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
