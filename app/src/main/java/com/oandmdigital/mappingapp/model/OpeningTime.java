package com.oandmdigital.mappingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class OpeningTime implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDay);
        dest.writeDouble(this.mOpening);
        dest.writeDouble(this.mClosing);
        dest.writeString(this.mPeriod);
    }

    protected OpeningTime(Parcel in) {
        this.mDay = in.readString();
        this.mOpening = in.readDouble();
        this.mClosing = in.readDouble();
        this.mPeriod = in.readString();
    }

    public static final Parcelable.Creator<OpeningTime> CREATOR = new Parcelable.Creator<OpeningTime>() {
        public OpeningTime createFromParcel(Parcel source) {
            return new OpeningTime(source);
        }

        public OpeningTime[] newArray(int size) {
            return new OpeningTime[size];
        }
    };
}
