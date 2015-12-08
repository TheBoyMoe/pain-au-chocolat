package com.oandmdigital.mappingapp.model;

public class OpeningTime {

    private String mDay;
    private double mOpening;
    private double mClosing;
    private String mPeriod;


    public OpeningTime(String day, double opening, double closing, String period) {
        mDay = day;
        mOpening = opening;
        mClosing = closing;
        mPeriod = period;
    }


    public String getPeriod() {
        return mPeriod;
    }

    public String getDay() {
        return mDay;
    }

    public double getOpening() {
        return mOpening;
    }

    public double getClosing() {
        return mClosing;
    }
}
