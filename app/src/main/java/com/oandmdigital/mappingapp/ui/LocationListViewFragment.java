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
import com.oandmdigital.mappingapp.model.Address;
import com.oandmdigital.mappingapp.model.OpeningTime;
import com.oandmdigital.mappingapp.model.Shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationListViewFragment extends Fragment{

    private List<OpeningTime> mOpeningTimes = new ArrayList<>();
    {
        mOpeningTimes.add(new OpeningTime("Monday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Tuesday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Wednesday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Thursday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Friday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Saturday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Sunday", 11.0, 5.0, "pm"));
    }

    private Shop[] list = {

        new Shop("Pierre Marcolini", new Address("37 Marylebone High St", "Marylebone", "W1U"), mOpeningTimes, "https://eu.marcolini.com", "1111111111", "res/drawable/pierre_marcolini.jpg", 5.0, 51.520721, -0.151676),
        new Shop("Master Brothers", new Address("19-29 Redchurch St", "Shoreditch", "E2 7DJ"), mOpeningTimes, "http://mastbrothers.co.uk/pages/home", "020 7739 1236", "res/drawable/pierre_marcolini.jpg", 4.4, 51.5243849, -0.0775549),
        new Shop("Melt", new Address("59 Ledbury Road", "Notting Hill", "W11 2AA"), mOpeningTimes, "http://www.meltchocolates.com", "020 7727 5030", "res/drawable/pierre_marcolini.jpg", 4.7, 51.5141542, -0.201449),
        new Shop("Paul A Young Fine Chocolates", new Address("143 Wardour Street", "Soho", "W1F 8WA"), mOpeningTimes, "http://www.paulayoung.co.uk", "020 7437 0011", "res/drawable/pierre_marcolini.jpg", 4.6, 51.5141558, -0.1367509),
        new Shop("La Maison Du Chocolat", new Address("46 Piccadilly", "Mayfair", "W1J 0DS"), mOpeningTimes, "http://www.lamaisonduchocolat.co.uk/uk/en/", "020 7287 8500", "res/drawable/pierre_marcolini.jpg", 4.6, 51.508781, -0.1403988),
        new Shop("Artisan du Chocolat", new Address("81 Westbourne Grove", "Noting Hill", "W2 4UL"), mOpeningTimes, "http://www.artisanduchocolat.com", "0845 270 6996", "res/drawable/pierre_marcolini.jpg", 4.5, 51.5087757, -0.1733207),
        new Shop("Melange", new Address("184 Bellenden Road", "Peckham", "SE15 4BW"), mOpeningTimes, "http://www.themelange.com", "07722 650711", "res/drawable/pierre_marcolini.jpg", 4.5, 51.5086867, -0.2788125),
        new Shop("Philip Neal", new Address("43 Turnham Green Terrace", "Chiswick", "W4 1RG"), mOpeningTimes, "http://www.philipnealchocolates.co.uk", "020 8987 3183", "res/drawable/pierre_marcolini.jpg", 3.5, 51.4938531,-0.2576361),

    };

    private List<Shop> mItems;
    private LocationArrayAdapter mAdapter;

    public static LocationListViewFragment newInstance() {
        return new LocationListViewFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView view = (ListView) inflater.inflate(R.layout.list_view, container, false);

        // populate the array adapter with the data set & bind it to the listview
        mItems = new ArrayList<>(Arrays.asList(list));
        mAdapter = new LocationArrayAdapter(mItems);
        view.setAdapter(mAdapter);

        // TODO setOnItemClickListener - launch the LocationActivity scroll activity
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
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
            holder.shopRating.setText(String.format("Rating: %.1f", item.getReview()));

            // TODO set the imageview

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
