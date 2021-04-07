package com.example.weatherapp.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.AppExecutor;
import com.example.weatherapp.model.WeatherModel;
import com.example.weatherapp.utils.Constants;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class RemoteDataSourceApi {

    private MutableLiveData<WeatherModel> weatherModelMutableLiveData;

    private WeatherRunnable weatherRunnable;

    private static RemoteDataSourceApi instance;

    public static RemoteDataSourceApi getInstance(){

        if (instance == null){

            instance= new RemoteDataSourceApi();
        }

        return instance;
    }

    public RemoteDataSourceApi(){

        weatherModelMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<WeatherModel> getWeatherModelMutableLiveData() {
        return weatherModelMutableLiveData;
    }

    private class WeatherRunnable implements Runnable{

        private String query;
        boolean cancelRequest;

        public WeatherRunnable(String query) {
            this.query = query;
            this.cancelRequest = false;
        }

        private Call<WeatherModel> getWeather(String query){

            return RetrofitBuilder.getRetrofitInterface().getWeather(query, Constants.API_KEY);
        }

        @Override
        public void run() {

            try{

                Response response = getWeather(query).execute();

                if (cancelRequest){
                    return;
                }
                if (response.code() == 200){


                    WeatherModel weatherModel = (((WeatherModel)response.body()));
                    weatherModelMutableLiveData.postValue(weatherModel);
                }
                else{

                    Log.v("Tag", "Error: " + response.errorBody());
                    weatherModelMutableLiveData.postValue(null);
                }



            } catch (Exception e) {
                e.printStackTrace();
                weatherModelMutableLiveData.postValue(null);
            }

        }
    }

    public void weatherApi(String query){

        if (weatherRunnable != null){

            weatherRunnable = null;
        }

        weatherRunnable = new WeatherRunnable(query);

        final Future handler = AppExecutor.getInstance().getThreads().submit(weatherRunnable);

        AppExecutor.getInstance().getThreads().schedule(new Runnable() {
            @Override
            public void run() {

                handler.cancel(true);

            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

}
