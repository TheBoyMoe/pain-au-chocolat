package com.oandmdigital.mappingapp.ui;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.common.Utils;
import com.oandmdigital.mappingapp.event.LocationClickEvent;
import com.oandmdigital.mappingapp.model.Shop;
import com.oandmdigital.mappingapp.pager.CustomPagerAdapter;

import de.greenrobot.event.EventBus;

@SuppressWarnings("FieldCanBeLocal")
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


            mViewPager = (ViewPager) findViewById(R.id.view_pager);
            if(mViewPager == null) {
                // device >= 600dp, display the list and map fragments simultaneously
                if(getFragmentManager().findFragmentById(R.id.frame_left) == null) {
                    FragmentStatePagerAdapter adapter = new CustomPagerAdapter(this, getFragmentManager());
                    getFragmentManager().beginTransaction()
                            .add(R.id.frame_left, adapter.getItem(0))
                            .add(R.id.frame_right, adapter.getItem(1))
                            .commit();
                }

            } else {
                // on a phone, use ViewPager and TabLayout
                mPagerTabs = (TabLayout) findViewById(R.id.tab_layout);
                // set the ViewPager and TabLayout
                mViewPager.setAdapter(new CustomPagerAdapter(this, getFragmentManager()));
                mPagerTabs.setupWithViewPager(mViewPager);
                mViewPager.addOnPageChangeListener(this);

            }


            // cache the req'd elements
            mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
            mLocationPopup = findViewById(R.id.popup_layout);
            mLocationWrapper = findViewById(R.id.location_wrapper);
            mLocationName = (TextView) findViewById(R.id.location_name);
            mLocationAddress = (TextView) findViewById(R.id.location_address);
            mLocationRating = (TextView) findViewById(R.id.location_rating);
            mImageDirections = (ImageView) findViewById(R.id.image_directions);

            // set the OnCLickListeners
            mImageDirections.setOnClickListener(this);
            mLocationWrapper.setOnClickListener(this);


            // setup the popup layout to pop in to view when req'd
            mLocationPopup.setVisibility(View.GONE);
            mLocationPopup.animate().translationY(300).alpha(0.0f);
            mIsPopupVisible = false;


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
        mLocation = event.getLocation();

        switch (eventType) {
            case LocationClickEvent.MAP_MARKER_CLICK_EVENT:
                if(!mIsPopupVisible) {
                    Utils.animateViewUp(mLocationPopup);
                    mIsPopupVisible = true;
                }
                populateLocationPopup();
                break;

            case LocationClickEvent.MAP_CLICK_EVENT:
                if(mIsPopupVisible) {
                    Utils.animateViewDown(mLocationPopup);
                    mIsPopupVisible = false;
                }
                break;

            case LocationClickEvent.LIST_ITEM_CLICK_EVENT:
                launchLocationDetailActivity();
                break;
        }

    }


    private void launchLocationDetailActivity() {
        Intent intent = new Intent(this, LocationDetailActivity.class);
        intent.putExtra(LocationDetailActivity.SHOP_PARCELABLE, mLocation);
        startActivity(intent);
    }


    private void populateLocationPopup() {
        mLocationName.setText(mLocation.getName());
        mLocationAddress.setText(String.format("%s %s",
                mLocation.getAddress().getStreet(), mLocation.getAddress().getArea()));
        mLocationRating.setText(String.format("Rating %.1f", mLocation.getRating()));
    }


    // handle clicks to the popup layout
    @Override
    public void onClick(View v) {

        Utils.animateViewDown(mLocationPopup);
        mIsPopupVisible = false;
        int id = v.getId();

        if(id == R.id.location_wrapper) {
            launchLocationDetailActivity();

        } else if(id == R.id.image_directions) {
            Utils.launchGoogleNavigation(MainActivity.this, mLocation);
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {
        if(mIsPopupVisible) {
            Utils.animateViewDown(mLocationPopup);
            mIsPopupVisible = false;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // no-op
    }


    @Override
    public void onPageSelected(int position) {
        // no-op
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_license:
                startActivity(new Intent(this, LicenseActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
