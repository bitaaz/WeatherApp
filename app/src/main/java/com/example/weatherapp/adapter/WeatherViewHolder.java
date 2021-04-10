package com.example.weatherapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;


public class WeatherViewHolder extends RecyclerView.ViewHolder {

    TextView location, temperature;
    ImageView imageView;

    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);

        location = itemView.findViewById(R.id.location);
        temperature = itemView.findViewById(R.id.temperature);
        imageView = itemView.findViewById(R.id.pic);
    }
}
