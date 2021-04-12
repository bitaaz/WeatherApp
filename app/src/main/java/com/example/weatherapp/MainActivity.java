package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.adapter.OnWeatherItemListener;
import com.example.weatherapp.adapter.WeatherRecyclerViewAdapter;
import com.example.weatherapp.model.WeatherModel;
import com.example.weatherapp.viewmodel.WeatherViewModel;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnWeatherItemListener {

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
        weatherApi("Tehran");






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

        adapter = new WeatherRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onWeatherItemClickListener(int position) {

        Intent intent = new Intent(MainActivity.this, WeatherDetailsActivity.class);

        intent.putExtra("weather", adapter.getSelectedWeather(position));


        WeatherModel weatherModel = adapter.getSelectedWeather(position);

        intent.putExtra("name", weatherModel.getName());

        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String temperature = decimalFormat.format(weatherModel.getMain().getTemp() - 273.15);
        String maxTemp = decimalFormat.format(weatherModel.getMain().getTemp_max() - 273.15);
        String minTemp = decimalFormat.format(weatherModel.getMain().getTemp_min() - 273.15);

        intent.putExtra("temp", temperature);
        intent.putExtra("maxTemp", maxTemp);
        intent.putExtra("minTemp", minTemp);

        intent.putExtra("wind", weatherModel.getWind().getSpeed());
        intent.putExtra("condition", weatherModel.getWeather().get(0).getMain());
        intent.putExtra("desc", weatherModel.getWeather().get(0).getDescription());
        intent.putExtra("pressure", weatherModel.getMain().getPressure());
        intent.putExtra("humid", weatherModel.getMain().getHumidity());

        DateFormat dateFormat = DateFormat.getTimeInstance();
        String sunriseTime = dateFormat.format(new Date(weatherModel.getSys().getSunrise()));
        String sunsetTime = dateFormat.format(new Date(weatherModel.getSys().getSunset()));
        String lastUpdated = dateFormat.format(new Date(weatherModel.getDt()));

        intent.putExtra("sunrise", sunriseTime);
        intent.putExtra("sunset", sunsetTime);
        intent.putExtra("updated", lastUpdated);

        intent.putExtra("icon", weatherModel.getWeather().get(0).getIcon());






        startActivity(intent);



    }
}