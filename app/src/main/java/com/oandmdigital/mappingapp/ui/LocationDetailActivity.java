package com.oandmdigital.mappingapp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.model.Shop;
import com.oandmdigital.mappingapp.model.ShopData;

public class LocationDetailActivity extends AppCompatActivity {

    public static final String SHOP_PARCELABLE = "shop";
    public static final String ITEM_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // fetch the shop object
        Shop shop = getIntent().getParcelableExtra(SHOP_PARCELABLE);
        int position = getIntent().getIntExtra(ITEM_POSITION, 0);

        // set the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set the toolbar's title
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(shop.getName());
        }



        // TODO use Glide/Picasso to load image
        // use the google street view image as a backdrop for the toolbar
        ImageView toolbarBackdrop = (ImageView) findViewById(R.id.backdrop);
        toolbarBackdrop.setImageResource(ShopData.getImageDrawable(position));


        // TODO load the shop details


        // TODO launch Google Navigation from the FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }




}
