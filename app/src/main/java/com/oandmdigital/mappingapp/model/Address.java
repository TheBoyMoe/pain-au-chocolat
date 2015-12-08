package com.oandmdigital.mappingapp.model;


public class Address {

    private String mStreet;
    private String mArea;
    private String mPostalCode;


    public Address(String street, String area, String postalCode) {
        mStreet = street;
        mArea = area;
        mPostalCode = postalCode;
    }


    public String getStreet() {
        return mStreet;
    }

    public String getArea() {
        return mArea;
    }

    public String getPostalCode() {
        return mPostalCode;
    }
}
