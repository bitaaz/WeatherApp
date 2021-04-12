package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.model.WeatherModel;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Date;

public class WeatherDetailsActivity extends AppCompatActivity {

    private TextView city, temp, wind, condition, pressure, humidity, sunrise, sunset, lastUpdated
            , highTemp, lowTemp;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        city = findViewById(R.id.cityName);
        temp = findViewById(R.id.weatherDetailTemp);
        highTemp = findViewById(R.id.highTemp);
        lowTemp = findViewById(R.id.lowTemp);
        wind = findViewById(R.id.wind);
        condition = findViewById(R.id.condition);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        sunrise = findViewById(R.id.sunrise);
        sunset = findViewById(R.id.sunset);
        lastUpdated = findViewById(R.id.lastUpdate);
        imageView = findViewById(R.id.weatherDetailImage);

        getDataFromIntent();


    }

    @SuppressLint("SetTextI18n")
    private void getDataFromIntent(){

        if (getIntent().hasExtra("weather")){


            String cityName = getIntent().getStringExtra("name");
            city.setText(cityName);

            String tempString = getIntent().getStringExtra("temp");
            temp.setText(tempString + "°C");

            String maxTempString = getIntent().getStringExtra("maxTemp");
            highTemp.setText("High: " + maxTempString + "°C");

            String minTempString = getIntent().getStringExtra("minTemp");
            lowTemp.setText("Low: " + minTempString + "°C");

            Float windFloat = getIntent().getFloatExtra("wind", 0);
            wind.setText("Wind: " + Float.toString(windFloat) + " mps");

            String conditionString = getIntent().getStringExtra("condition");
            String description = getIntent().getStringExtra("desc");
            condition.setText("Condition: " + conditionString + " ( " + description + " )");

            Float pressureFloat = getIntent().getFloatExtra("pressure", 0);
            pressure.setText("Pressure: " + Float.toString(pressureFloat) + " hPa");

            Float humidFloat = getIntent().getFloatExtra("humid", 0);
            humidity.setText("Humidity: " + Float.toString(humidFloat) + "%");

            String sunriseString = getIntent().getStringExtra("sunrise");
            sunrise.setText("Sunrise: " + sunriseString);

            String sunsetString = getIntent().getStringExtra("sunset");
            sunset.setText("Sunset: " + sunsetString);

            String lastUpdateTime = getIntent().getStringExtra("updated");
            lastUpdated.setText("Last Updated: " + lastUpdateTime);

            String iconString = getIntent().getStringExtra("icon");

            Glide.with(this)
                    .load("https://openweathermap.org/img/w/" + iconString +  ".png")
                    .into(imageView);
        }


    }
}