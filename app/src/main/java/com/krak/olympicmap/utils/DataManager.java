package com.krak.olympicmap.utils;

import android.app.Activity;
import android.util.Log;

import com.krak.olympicmap.entities.Country;
import com.krak.olympicmap.entities.Medal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

// Класс для работы с csv файлами. Решение не идеальное, но работающее
public class DataManager {

    private static final String TAG = "DataManager";
    private Activity activity;

    public DataManager(Activity activity) {
        this.activity = activity;
    }

    private ArrayList<String> readAllLines(InputStreamReader is) throws IOException{
        ArrayList<String> strings = new ArrayList<>();
        BufferedReader reader = new BufferedReader(is);
        String mLine;
        while ((mLine = reader.readLine()) != null) {
            strings.add(mLine);
        }
        reader.close();
        return strings;
    }

    // Загружаем все Medal из medals.csv
    public ArrayList<Medal> loadMedals(){
        ArrayList<String> lines = new ArrayList<>();
        try {
            lines = readAllLines(new InputStreamReader(activity.getAssets().open("medals.csv")));
        } catch (IOException e) {
            Log.e(TAG, "Exception while reading medals.csv");
        }
        ArrayList<Medal> result = new ArrayList<>();
        int countryIndex = Arrays.asList(lines.get(0).split(";")).indexOf("country");
        int goldIndex = Arrays.asList(lines.get(0).split(";")).indexOf("gold");
        int silverIndex = Arrays.asList(lines.get(0).split(";")).indexOf("silver");
        int bronzeIndex = Arrays.asList(lines.get(0).split(";")).indexOf("bronze");
        int totalIndex = Arrays.asList(lines.get(0).split(";")).indexOf("total");
        for (int i = 1; i < lines.size(); i++) {
            String[] data = lines.get(i).split(";");
            result.add(new Medal(
                    data[countryIndex],
                    Integer.parseInt(data[goldIndex]),
                    Integer.parseInt(data[silverIndex]),
                    Integer.parseInt(data[bronzeIndex]),
                    Integer.parseInt(data[totalIndex])
            ));
        }
        return result;
    }

    // Загружаем все Country из countries.csv
    public ArrayList<Country> loadCountries(){
        ArrayList<String> lines = new ArrayList<>();
        try {
            lines = readAllLines(new InputStreamReader(activity.getAssets().open("countries.csv")));
        } catch (IOException e) {
            Log.e(TAG, "Exception while reading medals.csv");
        }
        ArrayList<Country> result = new ArrayList<>();
        int countryIndex = Arrays.asList(lines.get(0).split(";")).indexOf("country");
        int latitudeIndex = Arrays.asList(lines.get(0).split(";")).indexOf("latitude");
        int longitudeIndex = Arrays.asList(lines.get(0).split(";")).indexOf("longitude");
        int russianNameIndex = Arrays.asList(lines.get(0).split(";")).indexOf("russianName");
        int zoomIndex = Arrays.asList(lines.get(0).split(";")).indexOf("zoom");
        for (int i = 1; i < lines.size(); i++) {
            String[] data = lines.get(i).split(";");
            result.add(new Country(
                    data[countryIndex],
                    data[russianNameIndex],
                    Float.parseFloat(data[latitudeIndex]),
                    Float.parseFloat(data[longitudeIndex]),
                    Float.parseFloat(data[zoomIndex])
            ));
        }
        return result;
    }
}
