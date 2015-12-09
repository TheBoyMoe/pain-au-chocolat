package com.oandmdigital.mappingapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.oandmdigital.mappingapp.R;
import com.oandmdigital.mappingapp.event.ListOnItemClick;
import com.oandmdigital.mappingapp.model.Shop;
import com.oandmdigital.mappingapp.model.ShopData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;

public class LocationListViewFragment extends Fragment{


    public static LocationListViewFragment newInstance() {
        return new LocationListViewFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView view = (ListView) inflater.inflate(R.layout.list_view, container, false);

        // populate the array adapter with the data set & bind it to the listview
        List<Shop> items = new ArrayList<>(Arrays.asList(ShopData.list));
        LocationArrayAdapter adapter = new LocationArrayAdapter(items);
        view.setAdapter(adapter);

        // TODO setOnItemClickListener - launch the LocationActivity scroll activity
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // post the selected item to the bus, allowing the activity to deal with click event
                EventBus.getDefault().post(new ListOnItemClick((Shop) parent.getItemAtPosition(position), position));
            }
        });

        return view;
    }


    private class LocationArrayAdapter extends ArrayAdapter<Shop> {

        public LocationArrayAdapter(List<Shop> items) {
            super(getActivity(), 0, items);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item, parent, false);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if(holder == null) {
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }

            // populate the holder elements
            Shop item = getItem(position);
            holder.shopName.setText(item.getName());
            holder.shopStreet.setText(item.getAddress().getStreet());
            holder.shopRating.setText(String.format("Rating: %.1f", item.getRating()));

            // set the imageview
            holder.shopThumbnail.setImageResource(ShopData.getImageDrawable(position));

            return convertView;
        }
    }


    private class ViewHolder {
        ImageView shopThumbnail;
        TextView shopName;
        TextView shopStreet;
        TextView shopRating;

        public ViewHolder(View row) {
            shopThumbnail = (ImageView) row.findViewById(R.id.shop_image);
            shopName = (TextView) row.findViewById(R.id.shop_name);
            shopStreet = (TextView) row.findViewById(R.id.shop_street);
            shopRating = (TextView) row.findViewById(R.id.shop_rating);
        }
    }



}
