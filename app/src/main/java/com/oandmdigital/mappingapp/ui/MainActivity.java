package com.oandmdigital.mappingapp.ui;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.event.LocationClickEvent;
import com.oandmdigital.mappingapp.pager.CustomPagerAdapter;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout mCoordinatorLayout;
    private ViewPager mViewPager;
    private TabLayout mPagerTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        // cache the req'd elements
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mPagerTabs = (TabLayout) findViewById(R.id.tab_layout);


        // set the OnCLickListeners


        // set the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.app_name));
        }


        // set the ViewPager and TabLayout
        mViewPager.setAdapter(new CustomPagerAdapter(this, getFragmentManager()));
        mPagerTabs.setupWithViewPager(mViewPager);

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

//        Intent intent = new Intent(this, LocationDetailActivity.class);
//        intent.putExtra(LocationDetailActivity.SHOP_PARCELABLE, event.getShop());
//        intent.putExtra(LocationDetailActivity.ITEM_POSITION, event.getPosition());
//        startActivity(intent);

        Toast.makeText(this, "Clicked on list item", Toast.LENGTH_LONG).show();

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
