package com.example.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("main")
    @Expose
    private String main;

    @SerializedName("description")
    @Expose
    private String description;

    public String getIcon() {
        return icon;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}
