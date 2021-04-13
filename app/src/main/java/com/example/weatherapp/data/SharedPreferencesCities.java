package com.example.weatherapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.weatherapp.model.WeatherModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SharedPreferencesCities {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferencesCities(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveWeather(List<WeatherModel> cities){

        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson = new Gson();

        String json = gson.toJson(cities);

        editor.putString("cities", json);
        editor.commit();

    }

    public List<WeatherModel> getWeather(){

        Gson gson = new Gson();

        String json = sharedPreferences.getString("cities", "");

        Type type = new TypeToken<List<WeatherModel>>() {}.getType();

        List<WeatherModel> cities = gson.fromJson(json, type);

        return cities;
    }
}
