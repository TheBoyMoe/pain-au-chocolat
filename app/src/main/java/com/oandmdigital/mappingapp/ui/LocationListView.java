package com.oandmdigital.mappingapp.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.oandmdigital.mappingapp.model.Address;
import com.oandmdigital.mappingapp.model.OpeningTime;
import com.oandmdigital.mappingapp.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class LocationListView extends ListFragment{

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

        new Shop("Pierre Marcolini", new Address("37 Marylebone High St", "London", "W1U"), mOpeningTimes, "https://eu.marcolini.com", "1111111111", "imageurl", 5.0, 51.520721, -0.151676),
        new Shop("Master Brothers", new Address("19-29 Redchurch St", "London", "E2 7DJ"), mOpeningTimes, "http://mastbrothers.co.uk/pages/home", "020 7739 1236", "imageurl", 4.4, 51.5243849, -0.0775549),
        new Shop("Melt", new Address("59 Ledbury Road", "Notting Hill", "W11 2AA"), mOpeningTimes, "http://www.meltchocolates.com", "020 7727 5030", "imageurl", 4.7, 51.5141542, -0.201449),
        new Shop("Paul A Young Fine Chocolates", new Address("143 Wardour Street", "Soho", "W1F 8WA"), mOpeningTimes, "http://www.paulayoung.co.uk", "020 7437 0011", "imageurl", 4.6, 51.5141558, -0.1367509),
        new Shop("La Maison Du Chocolat", new Address("46 Piccadilly", "Mayfair", "W1J 0DS"), mOpeningTimes, "http://www.lamaisonduchocolat.co.uk/uk/en/", "020 7287 8500", "imageurl", 4.6, 51.508781, -0.1403988),
        new Shop("Artisan du Chocolat", new Address("81 Westbourne Grove", "Noting Hill", "W2 4UL"), mOpeningTimes, "http://www.artisanduchocolat.com", "0845 270 6996", "imageurl", 4.5, 51.5087757, -0.1733207),
        new Shop("Melange", new Address("184 Bellenden Road", "Peckham", "SE15 4BW"), mOpeningTimes, "http://www.themelange.com", "07722 650711", "imageurl", 4.5, 51.5086867, -0.2788125),
        new Shop("Philip Neal", new Address("43 Turnham Green Terrace", "Chiswick", "W4 1RG"), mOpeningTimes, "http://www.philipnealchocolates.co.uk", "020 8987 3183", "imageurl", 3.5, 51.4938531,-0.2576361),

    };

    public LocationListView newInstance() {
        return new LocationListView();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
