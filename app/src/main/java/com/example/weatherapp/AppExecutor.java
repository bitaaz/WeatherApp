package com.example.weatherapp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutor {

    private static AppExecutor instance;

    public static AppExecutor getInstance(){

        if (instance == null){
            instance = new AppExecutor();
        }

        return instance;
    }

    private final ScheduledExecutorService threads = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService getThreads() {
        return threads;
    }
}
