package com.example.weather.presenter;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.widget.Toast;

import com.example.weather.R;
import com.example.weather.model.FetchAddressIntentService;
import com.example.weather.model.RemoteData;
import com.example.weather.view.IMainView;
import android.os.ResultReceiver;

import org.json.JSONObject;

public class Presenter implements IPresenter {


    private RemoteData remoteData;
    private IMainView iMainView;
    private Handler handler = new Handler();




    public Presenter(IMainView iMainView) {
        this.remoteData = new RemoteData();
        this.iMainView = iMainView;
    }

    //@Override
    public void getRemoteData(final Context context, final String city) {


        new Thread(){
            public void run(){
                final JSONObject json = RemoteData.getJSON(context,city);
                if(json == null){
                    handler.post(new Runnable(){
                        public void run(){
                            Toast.makeText(context,
                                    context.getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable(){
                        public void run(){
                            iMainView.renderWeather(json);
                        }
                    });
                }
            }
        }.start();


    }

    @Override
    public void getLocation() {

    }
}
