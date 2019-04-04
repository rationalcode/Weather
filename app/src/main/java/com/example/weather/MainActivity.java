package com.example.weather;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Locale;

import static com.example.weather.WeatherFragment.city;
import static com.example.weather.WeatherFragment.currentTemperature;


public class MainActivity extends AppCompatActivity {

    private Handler handler;

    private static final String TAG = "logs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();


        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new WeatherFragment())
                    .commit();

            updateWeatherData();

        }


    }


  //  private void updateWeatherData(final String city){

    private void updateWeatherData(){
        new Thread(){
            public void run(){
                final JSONObject json = RemoteData.getJSON(getApplicationContext());
                if(json == null){
                    handler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(getApplicationContext(),
                                    getApplicationContext().getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable(){
                        public void run(){
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject json){
        try {
            String country = json.get("name").toString();
            city.setText(country);
                    //json.getJSONObject("sys").getString("country"));

            JSONObject main = json.getJSONObject("main");
            String temperature = main.getDouble("temp")+ " ℃";
            currentTemperature.setText(temperature);



        }catch(Exception e){
            Log.e("logs", json.toString());
        }
    }

}