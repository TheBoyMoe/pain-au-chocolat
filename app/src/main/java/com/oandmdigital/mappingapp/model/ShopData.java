package com.oandmdigital.mappingapp.model;

import com.oandmdigital.mappingapp.R;

import java.util.ArrayList;
import java.util.List;

public class ShopData {


    public static int getImageDrawable(int position) {
        switch (position) {

            default:
            case 0:
                return R.drawable.pierre_marcolini;
            case 1:
                return R.drawable.mast_brothers;
            case 2:
                return R.drawable.melt;
            case 3:
                return R.drawable.paul_young;
            case 4:
                return R.drawable.la_maison_du_chocolate;
            case 5:
                return R.drawable.artisan_du_chocolate;
            case 6:
                return R.drawable.melange;
            case 7:
                return R.drawable.philip_neal;

        }
    }


    private static List<OpeningTime> mOpeningTimes = new ArrayList<>();
    {
        mOpeningTimes.add(new OpeningTime("Monday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Tuesday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Wednesday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Thursday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Friday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Saturday", 9.0, 6.0, "pm"));
        mOpeningTimes.add(new OpeningTime("Sunday", 11.0, 5.0, "pm"));
    }

    public static Shop[] list = {
            new Shop("Pierre Marcolini", new Address("37 Marylebone High St", "Marylebone", "W1U"), mOpeningTimes, "https://eu.marcolini.com", "1111111111", "", 5.0, 51.520721, -0.151676),
            new Shop("Mast Brothers", new Address("19-29 Redchurch St", "Shoreditch", "E2 7DJ"), mOpeningTimes, "http://mastbrothers.co.uk/pages/home", "020 7739 1236", "", 4.4, 51.5243849, -0.0775549),
            new Shop("Melt", new Address("59 Ledbury Road", "Notting Hill", "W11 2AA"), mOpeningTimes, "http://www.meltchocolates.com", "020 7727 5030", "", 4.7, 51.5141542, -0.201449),
            new Shop("Paul A Young Fine Chocolates", new Address("143 Wardour Street", "Soho", "W1F 8WA"), mOpeningTimes, "http://www.paulayoung.co.uk", "020 7437 0011", "", 4.6, 51.5141558, -0.1367509),
            new Shop("La Maison Du Chocolat", new Address("46 Piccadilly", "Mayfair", "W1J 0DS"), mOpeningTimes, "http://www.lamaisonduchocolat.co.uk/uk/en/", "020 7287 8500", "", 4.6, 51.508781, -0.1403988),
            new Shop("Artisan du Chocolat", new Address("81 Westbourne Grove", "Noting Hill", "W2 4UL"), mOpeningTimes, "http://www.artisanduchocolat.com", "0845 270 6996", "", 4.5, 51.5087757, -0.1733207),
            new Shop("Melange", new Address("184 Bellenden Road", "Peckham", "SE15 4BW"), mOpeningTimes, "http://www.themelange.com", "07722 650711", "", 4.5, 51.5086867, -0.2788125),
            new Shop("Philip Neal", new Address("43 Turnham Green Terrace", "Chiswick", "W4 1RG"), mOpeningTimes, "http://www.philipnealchocolates.co.uk", "020 8987 3183", "", 3.5, 51.4938531,-0.2576361),
    };

}
