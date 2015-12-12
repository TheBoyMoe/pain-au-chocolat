package com.oandmdigital.mappingapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.event.ListOnItemClick;
import com.oandmdigital.mappingapp.model.Shop;

import de.greenrobot.event.EventBus;

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
    public void onEventMainThread(ListOnItemClick event) {

        Intent intent = new Intent(this, LocationDetailActivity.class);
        intent.putExtra(LocationDetailActivity.SHOP_PARCELABLE, event.getShop());
        intent.putExtra(LocationDetailActivity.ITEM_POSITION, event.getPosition());
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_map:
                startActivity(new Intent(this, LocationMapActivity.class));
                return true;

            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
