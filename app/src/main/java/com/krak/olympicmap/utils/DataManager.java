package com.krak.olympicmap.utils;

import android.app.Activity;
import android.util.Log;

import com.krak.olympicmap.activity_main.fragments.info.Medal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class DataManager {

    private static final String TAG = "DataManager";
    private Activity activity;

    public DataManager(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Medal> loadMedals(){
        BufferedReader reader = null;
        ArrayList<String> lines = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(activity.getAssets().open("medals.csv")));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                lines.add(mLine);
            }
        } catch (IOException e) {
            Log.e(TAG, "Exception while reading medals.csv");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {}
            }
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
}
