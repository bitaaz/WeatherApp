package com.example.weatherapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.model.WeatherModel;
import com.example.weatherapp.repository.WeatherRepository;

public class WeatherViewModel extends ViewModel {

    private WeatherRepository weatherRepository;

    public WeatherViewModel(){

        weatherRepository = WeatherRepository.getInstance();
    }

    public LiveData<WeatherModel> getWeather(){

        return weatherRepository.getWeather();
    }

    public void weatherApi(String query){

        weatherRepository.weatherApi(query);
    }
}
