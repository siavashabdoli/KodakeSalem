package com.hackahealth.kodakesalem;

import android.app.Application;
import android.content.Context;

/**
 * Created by siavash on 5/18/16.
 */
public class MyApp extends Application{
    public static volatile Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext=this;
    }
}
