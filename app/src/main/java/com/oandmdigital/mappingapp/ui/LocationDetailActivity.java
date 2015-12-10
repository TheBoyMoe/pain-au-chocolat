package com.oandmdigital.mappingapp.ui;

import android.content.DialogInterface;
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
import android.widget.Toast;

import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.model.Shop;
import com.oandmdigital.mappingapp.model.ShopData;

/**
 * References
 * [1] http://stackoverflow.com/questions/31662416/show-collapsingtoolbarlayout-title-only-when-collapsed
 * [2] http://blog.grafixartist.com/add-a-toolbar-elevation-on-pre-lollipop/
 */

public class LocationDetailActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String SHOP_PARCELABLE = "shop";
    public static final String ITEM_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // fetch the shop object
        final Shop location = getIntent().getParcelableExtra(SHOP_PARCELABLE);
        int position = getIntent().getIntExtra(ITEM_POSITION, 0);




        // TODO use Glide/Picasso to load image
        // use the google street view image as a backdrop for the toolbar
        ImageView toolbarBackdrop = (ImageView) findViewById(R.id.backdrop);
        toolbarBackdrop.setImageResource(ShopData.getImageDrawable(position));


        // load the shop details
        // content title
        final TextView locationTitle = (TextView) findViewById(R.id.location_title);

        locationTitle.setText(location.getName());

        // content detail
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        TextView locationAddress = (TextView) findViewById(R.id.location_address);
        TextView locationTelephone = (TextView) findViewById(R.id.location_telephone);
        locationTelephone.setOnClickListener(this);
        TextView locationUrl = (TextView) findViewById(R.id.location_url);
        locationUrl.setOnClickListener(this);
        TextView locationOpeningTimes = (TextView) findViewById(R.id.location_opening_times);
        locationOpeningTimes.setOnClickListener(this);
        TextView locationRating = (TextView) findViewById(R.id.location_rating);
        TextView locationDistance = (TextView) findViewById(R.id.location_distance);

        fab.setOnClickListener(this);
        locationAddress.setText(String.format("%s, %s, %s",
                location.getAddress().getStreet(), location.getAddress().getArea(), location.getAddress().getPostalCode()));
        locationTelephone.setText(location.getTelephone());
        locationUrl.setText(location.getUrl());
        locationDistance.setText(String.format("Distance: %s %s", String.valueOf(location.getDistance()), " miles"));
        locationRating.setText(String.format("Rating: %.1f", location.getRating()));


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
                    collapsingToolbarLayout.setTitle(location.getName());
                    locationTitle.setText("");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle("");
                    locationTitle.setText(location.getName());
                    isShow = false;
                }
            }
        });

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Launch Google maps", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
            case R.id.location_opening_times:
                Snackbar.make(view, "Open opening times drop down", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
            case R.id.location_telephone:
                Snackbar.make(view, "Dial the phone number", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
            case R.id.location_url:
                Snackbar.make(view, "Open the link in the browser", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
        }

    }
}
