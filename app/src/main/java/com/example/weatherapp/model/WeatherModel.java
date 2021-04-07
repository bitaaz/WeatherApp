package com.example.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherModel  {

    private long timezone;
    private String name;

    @SerializedName("main")
    @Expose
    private Main main;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather;



    public String getName() {
        return name;
    }

    public long getTimezone() {
        return timezone;
    }

    public Main getMain(){

        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }
}
