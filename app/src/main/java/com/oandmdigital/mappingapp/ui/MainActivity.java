package com.oandmdigital.mappingapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        startActivity(intent);
    }


}
