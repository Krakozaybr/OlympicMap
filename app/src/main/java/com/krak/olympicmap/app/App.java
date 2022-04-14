package com.krak.olympicmap.app;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.yandex.mapkit.MapKitFactory;

public class App extends Application {

    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        // Убираем тёмную тему, чтобы она ничего не сломала
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        instance = this;
        initMaps();
    }

    private void initMaps() {
        MapKitFactory.setApiKey("70951948-173f-45f9-a418-44e2989f536e");
        MapKitFactory.initialize(this);
    }

    public static App getInstance() {
        return instance;
    }
}