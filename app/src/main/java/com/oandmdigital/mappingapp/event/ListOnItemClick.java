package com.oandmdigital.mappingapp.event;

import com.oandmdigital.mappingapp.model.Shop;

public class ListOnItemClick {

    private Shop mShop;

    public ListOnItemClick(Shop shop) {
        mShop = shop;
    }

    public Shop getShop() {
        return mShop;
    }


}
