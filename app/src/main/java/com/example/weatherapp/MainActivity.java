package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.weatherapp.adapter.WeatherRecyclerViewAdapter;
import com.example.weatherapp.model.WeatherModel;
import com.example.weatherapp.viewmodel.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WeatherViewModel weatherViewModel;

    private RecyclerView recyclerView;

    private WeatherRecyclerViewAdapter adapter;

    private List<WeatherModel> weatherModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        weatherModels = new ArrayList<>();

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);


        observer();
        configureRecyclerView();
        weatherApi("Tokyo");




    }

    public void observer(){

        weatherViewModel.getWeather().observe(this, new Observer<WeatherModel>() {
            @Override
            public void onChanged(WeatherModel weatherModel) {

                if (weatherModel != null){

                    Log.v("Tag", "name: " + weatherModel.getName());
                    weatherModels.add(weatherModel);
                    adapter.setWeathers(weatherModels);


                    Log.v("Tag", "count: " + adapter.getItemCount());

                }

            }
        });



    }

    public void weatherApi(String query){

        weatherViewModel.weatherApi(query);
    }

    private void configureRecyclerView(){

        adapter = new WeatherRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}