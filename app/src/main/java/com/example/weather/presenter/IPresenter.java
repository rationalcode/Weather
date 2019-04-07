package com.example.weather.presenter;

import android.content.Context;

public interface IPresenter {

   void getRemoteData(Context context, String ufa);
   void getLocation ();
}
