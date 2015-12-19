package com.oandmdigital.mappingapp.event;

import com.oandmdigital.mappingapp.model.Shop;

public class LocationClickEvent {

    public static final int MAP_MARKER_CLICK_EVENT = 1;
    public static final int LIST_ITEM_CLICK_EVENT = 2;
    public static final int MAP_CLICK_EVENT = 3;
    private Shop mLocation;
    private int mEventType;

    public LocationClickEvent(Shop location, int eventType) {
        mLocation = location;
        mEventType = eventType;
    }

    public Shop getLocation() {
        return mLocation;
    }

    public int getEventType() {
        return mEventType;
    }

}
