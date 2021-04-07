package com.example.weatherapp.utils;

import com.example.weatherapp.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("/data/2.5/weather")
    Call<WeatherModel> getWeather(
            @Query("q") String query,
            @Query("appid") String key
    );
}
