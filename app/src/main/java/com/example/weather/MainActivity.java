package com.example.weather;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.weather.model.FetchAddressIntentService;
import com.example.weather.model.GPSTracker;
import com.example.weather.presenter.IPresenter;
import com.example.weather.presenter.Presenter;
import com.example.weather.view.IMainView;
import com.example.weather.view.WeatherFragment;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements IMainView {

    private Handler handler;
    private WeatherFragment weatherFragment;

    private static final String TAG = "logs";
    IPresenter presenter;

    protected String message;
    private ResultReceiver resultCode;
    public static Location location;
    public GPSTracker tracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();


        if (savedInstanceState == null) {

            weatherFragment = new WeatherFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, weatherFragment)
                    .commit();


            presenter = new Presenter(this);




            startIntentService();


            updateData(this, "Ufa");


            //updateData(this, savedInstanceState.getString(message));


        }


    }


    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        startService(intent);
    }

    @Override
    public void updateData(Context context, String city) {


        presenter.getRemoteData(this, city);
    }


    //selected data from get json
    public void renderWeather(JSONObject json) {
        try {
            String country = json.get("name").toString();
            weatherFragment.city.setText(country);
            //json.getJSONObject("sys").getString("country"));

            JSONObject main = json.getJSONObject("main");
            String temperature = main.getDouble("temp") + " ℃";
            weatherFragment.currentTemperature.setText(temperature);


        } catch (Exception e) {
            Log.e("logs", json.toString());
        }
    }

    // updated view. selected city from fragmentDialog
    public void onUserSelectValue(int which) {

        switch (which) {
            case 0:
                updateData(this, "London");
                break;
            case 1:
                updateData(this, "Paris");
                break;
            case 2:
                updateData(this, "Tokyo");
                break;

            case 3:
                updateData(this, "New York");
                break;
            default:
                break;
        }
    }






}
