package com.oandmdigital.mappingapp.model;


public class Address {

    private String mStreet;
    private String mTown;
    private String mPostalCode;


    public Address(String street, String town, String postalCode) {
        mStreet = street;
        mTown = town;
        mPostalCode = postalCode;
    }


    public String getStreet() {
        return mStreet;
    }

    public String getTown() {
        return mTown;
    }

    public String getPostalCode() {
        return mPostalCode;
    }
}
