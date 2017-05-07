package com.example.android.quakereport;

/**
 * Created by Thoma on 24.04.2017.
 */

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private Long mTimeInMilliseconds;
    private String mUrl;

    /**
     * Makes a new Earthquake object containing magnitude, location and time.
     *
     * @param magnitude Size of the earthquake
     * @param location Location of the earthquake
     * @param time The time of when the earthquake happend
     */
    public Earthquake (double magnitude, String location, Long time, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = time;
        mUrl = url;
    }

    public double getMagnitude(){
        return mMagnitude;
    }

    public String getLocation(){
        return mLocation;
    }

    public Long getTimeInMilliseconds(){
        return mTimeInMilliseconds;
    }

    public String getUrl(){
        return mUrl;
    }
}
