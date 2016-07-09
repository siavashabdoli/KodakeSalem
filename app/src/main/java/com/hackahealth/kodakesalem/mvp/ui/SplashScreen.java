package com.hackahealth.kodakesalem.mvp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.util.AppSharedPreference;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test","master test");
        AppSharedPreference appSharedPreference=new AppSharedPreference(this);
        Log.d("SplashScreen","Token:"+appSharedPreference.isAuthorized());
//        if(appSharedPreference.getAccessToken()==null || appSharedPreference.getAccessToken().equals(""))
            startActivity(new Intent(SplashScreen.this,LoginActivity.class));
//        else
//            startActivity(new Intent(SplashScreen.this,HomePageActivity.class));
    }
}
