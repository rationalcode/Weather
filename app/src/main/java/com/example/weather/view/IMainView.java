package com.example.weather.view;

import android.content.Context;

import org.json.JSONObject;

public interface IMainView {


    void updateData(Context context, String s);
    void renderWeather(JSONObject json);

}
