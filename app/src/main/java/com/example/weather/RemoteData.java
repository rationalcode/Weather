package com.example.weather;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteData {


    private static final String TAG = "logs";

    //48512873de7e9ca06cf3ddbe456eea3a

    private static final String OPEN_WEATHER_MAP_API =

            //"http://api.openweathermap.org/data/2.5/weather?q=London";

            "http://api.openweathermap.org/data/2.5/weather?q=London&appid=48512873de7e9ca06cf3ddbe456eea3a&units=metric";

    public static JSONObject getJSON(Context context){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            //connection.addRequestProperty("x-api-key",
              //      context.getString(R.string.open_weather_maps_app_id));

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp;
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());


            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){


                return null;
            }



            return data;
        }catch(Exception e){
            return null;
        }
    }




}
