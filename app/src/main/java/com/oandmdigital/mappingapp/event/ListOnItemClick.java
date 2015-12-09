package com.oandmdigital.mappingapp.event;

import com.oandmdigital.mappingapp.model.Shop;

public class ListOnItemClick {

    private Shop mShop;
    private int mPosition;

    public ListOnItemClick(Shop shop, int position) {
        mShop = shop;
        mPosition = position;
    }

    public Shop getShop() {
        return mShop;
    }

    public int getPosition() {
        return mPosition;
    }


}
