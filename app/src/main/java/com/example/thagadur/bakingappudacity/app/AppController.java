package com.example.thagadur.bakingappudacity.app;

import android.app.Application;

import com.example.thagadur.bakingappudacity.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AvenirNextLTPro-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
