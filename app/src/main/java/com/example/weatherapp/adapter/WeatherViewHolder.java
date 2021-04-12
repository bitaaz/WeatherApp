package com.example.weatherapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;


public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView location, temperature;
    ImageView imageView;

    OnWeatherItemListener onWeatherItemListener;

    public WeatherViewHolder(@NonNull View itemView, OnWeatherItemListener onWeatherItemListener) {
        super(itemView);

        this.onWeatherItemListener = onWeatherItemListener;

        location = itemView.findViewById(R.id.location);
        temperature = itemView.findViewById(R.id.temperature);
        imageView = itemView.findViewById(R.id.pic);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        onWeatherItemListener.onWeatherItemClickListener(getAdapterPosition());
    }
}
