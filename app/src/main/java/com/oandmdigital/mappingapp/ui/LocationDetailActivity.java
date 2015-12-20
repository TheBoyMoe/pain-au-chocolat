package com.oandmdigital.mappingapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.common.Utils;
import com.oandmdigital.mappingapp.model.Shop;
import com.oandmdigital.mappingapp.model.ShopData;

/**
 * References
 * [1] http://stackoverflow.com/questions/31662416/show-collapsingtoolbarlayout-title-only-when-collapsed
 * [2] http://blog.grafixartist.com/add-a-toolbar-elevation-on-pre-lollipop/
 */

@SuppressWarnings("FieldCanBeLocal")
public class LocationDetailActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String SHOP_PARCELABLE = "shop";
    private Shop mLocation;
    private TextView mLocationAddress;
    private TextView mLocationTelephone;
    private TextView mLocationUrl;
    private TextView mLocationOpeningTimes;
    private TextView mLocationRating;
    private TextView mLocationDistance;
    private View mOpeningTimes;
    private boolean mShowTimes;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // fetch the shop object
        mLocation = getIntent().getParcelableExtra(SHOP_PARCELABLE);

        // TODO use Glide/Picasso to load image
        // use the google street view image as a backdrop for the toolbar
        ImageView toolbarBackdrop = (ImageView) findViewById(R.id.backdrop);
        toolbarBackdrop.setImageResource(ShopData.getImageDrawable(mLocation.getImage()));

        // load the shop details
        final TextView locationTitle = (TextView) findViewById(R.id.location_title);
        locationTitle.setText(mLocation.getName());

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mLocationAddress = (TextView) findViewById(R.id.location_address);
        mLocationAddress.setOnClickListener(this);
        mLocationTelephone = (TextView) findViewById(R.id.location_telephone);
        mLocationTelephone.setOnClickListener(this);
        mLocationUrl = (TextView) findViewById(R.id.location_url);
        mLocationUrl.setOnClickListener(this);
        mLocationOpeningTimes = (TextView) findViewById(R.id.location_opening_times);
        mOpeningTimes = findViewById(R.id.opening_times);
        mOpeningTimes.setVisibility(View.GONE);
        mLocationOpeningTimes.setOnClickListener(this);
        mLocationRating = (TextView) findViewById(R.id.location_rating);
        mLocationDistance = (TextView) findViewById(R.id.location_distance);

        mFab.setOnClickListener(this);
        mLocationAddress.setText(String.format("%s, %s, %s",
                mLocation.getAddress().getStreet(), mLocation.getAddress().getArea(), mLocation.getAddress().getPostalCode()));
        mLocationTelephone.setText(mLocation.getTelephone());
        mLocationUrl.setText(mLocation.getUrl());
        mLocationDistance.setText(String.format("Distance: %s %s", String.valueOf(mLocation.getDistance()), " miles"));
        mLocationRating.setText(String.format("Rating: %.1f", mLocation.getRating()));


        // set the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // show the toolbar's title when fully collapsed
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(mLocation.getName());
                    locationTitle.setText("");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle("");
                    locationTitle.setText(mLocation.getName());
                    isShow = false;
                }
            }
        });

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fab:
            case R.id.location_address:
                Utils.launchGoogleNavigation(this, mLocation);
                break;

            case R.id.location_opening_times:
                // TODO remove the hardcoded strings, pull data from location object
                // show hide the opening times onClick
                if(!mShowTimes) {
                    mShowTimes = true;
                    mOpeningTimes.setVisibility(View.VISIBLE);
                }
                else {
                    mShowTimes = false;
                    mOpeningTimes.setVisibility(View.GONE);
                }
                break;

            case R.id.location_telephone:
                // TODO check for devices with no phone, eg tablets
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse(String.format("tel:%s", mLocation.getTelephone())));
                startActivity(phoneIntent);
                break;

            case R.id.location_url:
                // TODO check program available
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mLocation.getUrl()));
                startActivity(webIntent);
                break;
        }

    }
}
