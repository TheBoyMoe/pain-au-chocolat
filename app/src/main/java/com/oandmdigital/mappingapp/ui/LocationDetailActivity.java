package com.oandmdigital.mappingapp.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class LocationDetailActivity extends AppCompatActivity {

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
        final TextView locationDistance = (TextView) findViewById(R.id.location_distance);

        locationTitle.setText(location.getName());
        locationDistance.setText(String.format("%s %s", String.valueOf(location.getDistance()), "mi"));

        // content detail
        TextView locationAddress = (TextView) findViewById(R.id.location_address);
        TextView locationPostalCode = (TextView) findViewById(R.id.location_postal_code);
        TextView locationTelephone = (TextView) findViewById(R.id.location_telephone);
        TextView locationUrl = (TextView) findViewById(R.id.location_url);
        TextView locationOpeningTimes = (TextView) findViewById(R.id.location_opening_times);
        TextView locationRating = (TextView) findViewById(R.id.location_rating);

        locationAddress.setText(String.format("%s, %s",
                location.getAddress().getStreet(), location.getAddress().getArea()));
        locationPostalCode.setText(location.getAddress().getPostalCode());
        locationTelephone.setText(location.getTelephone());
        locationUrl.setText(location.getUrl());
        locationRating.setText(String.format("Rating: %.1f", location.getRating()));


        // TODO open drop down displaying opening times
        locationOpeningTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LocationDetailActivity.this, "Display opening times", Toast.LENGTH_SHORT).show();
            }
        });

        // TODO display star ratings


        // TODO launch Google Navigation from the FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



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
                    locationDistance.setText("");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle("");
                    locationTitle.setText(location.getName());
                    locationDistance.setText(String.format("%s %s", String.valueOf(location.getDistance()), "mi"));
                    isShow = false;
                }
            }
        });

    }




}
