package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.weatherapp.model.WeatherModel;
import com.example.weatherapp.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    private WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);


        observer();
        weatherApi("Tehran");




    }

    public void observer(){

        weatherViewModel.getWeather().observe(this, new Observer<WeatherModel>() {
            @Override
            public void onChanged(WeatherModel weatherModel) {

                if (weatherModel != null){

                    Log.v("Tag", "name: " + weatherModel.getMain().getTemp());
                }

            }
        });



    }

    public void weatherApi(String query){

        weatherViewModel.weatherApi(query);
    }
}