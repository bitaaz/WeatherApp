package com.example.weatherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.model.WeatherModel;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WeatherModel> weathers = new ArrayList<>();

    public void setWeathers(List<WeatherModel> weathers) {
        this.weathers = weathers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((WeatherViewHolder)holder).location.setText(weathers.get(position).getName());


        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String temperature = decimalFormat.format(weathers.get(position).getMain().getTemp() - 273.15);


        ((WeatherViewHolder)holder).temperature.setText(temperature + "°C");

        Glide.with(holder.itemView.getContext())
                .load("https://openweathermap.org/img/w/" + weathers.get(position).getWeather().get(0).getIcon() + ".png")
                .into(((WeatherViewHolder) holder).imageView);



    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }
}
