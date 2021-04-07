package com.example.weatherapp.repository;

import androidx.lifecycle.LiveData;

import com.example.weatherapp.model.WeatherModel;
import com.example.weatherapp.request.RemoteDataSourceApi;

public class WeatherRepository {

    private RemoteDataSourceApi remoteDataSourceApi;

    private static WeatherRepository instance;

    public static WeatherRepository getInstance(){

        if (instance == null){
            instance = new WeatherRepository();
        }

        return instance;
    }

    private WeatherRepository(){

        remoteDataSourceApi = RemoteDataSourceApi.getInstance();
    }

    public LiveData<WeatherModel> getWeather(){

        return remoteDataSourceApi.getWeatherModelMutableLiveData();
    }

    public void weatherApi(String query){

        remoteDataSourceApi.weatherApi(query);
    }
}
