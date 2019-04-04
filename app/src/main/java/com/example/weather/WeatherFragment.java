package com.example.weather;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherFragment extends Fragment implements View.OnClickListener {


    static TextView city;
    static TextView currentTemperature;
    FloatingActionButton fab;


    Handler handler;

    public WeatherFragment(){
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        city = rootView.findViewById(R.id.city);
        currentTemperature = rootView.findViewById(R.id.current_temperature);
        fab = rootView.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                DialogFragment dialogFragment = new DialogFragment();
                dialogFragment.show(fm,"dialog");

            }
        });

        return rootView;
    }


    @Override
    public void onClick(View v) {

    }
}
