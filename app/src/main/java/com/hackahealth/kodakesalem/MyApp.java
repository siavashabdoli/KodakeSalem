package com.hackahealth.kodakesalem;

import android.app.Application;
import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by siavash on 5/18/16.
 */
public class MyApp extends Application{
    public static volatile Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext=this;
        initFont();
    }
    private void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.iran_sans_regular))
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

}
