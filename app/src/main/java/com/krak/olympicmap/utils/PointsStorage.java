package com.krak.olympicmap.utils;

import android.app.Activity;

import com.krak.olympicmap.entities.Country;
import com.yandex.mapkit.geometry.Point;

import java.util.ArrayList;
import java.util.HashMap;

// Хранилище точек на карте
public class PointsStorage {
    private static Point chanzoi;
    private static Point beijing;
    private static ArrayList<Country> countries;

    public static void loadData(Activity activity){
        DataManager manager = new DataManager(activity);
        countries = manager.loadCountries();
    }

    public static ArrayList<Country> getCountries() {
        return countries;
    }

    // Для корректной работы лучше вызвать метод инициализации,
    // чем написать простое присваивание
    public static void init(){
        chanzoi = new Point(40.766512, 114.879789);
        beijing = new Point(39.901850, 116.391441);
        countries = new ArrayList<>();
    }

    public static Point getChanzoi() {
        return chanzoi;
    }

    public static Point getBeijing() {
        return beijing;
    }
}
