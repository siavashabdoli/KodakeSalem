package com.hackahealth.kodakesalem.mvp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.mvp.presenter.LoginPresenter;
import com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface.LoginPresenterInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.LoginViewInterface;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.ButterKnife;

public class LoginActivity extends MvpActivity<LoginViewInterface, LoginPresenterInterface> implements LoginViewInterface  {

    private static String TAG;



    @NonNull
    @Override
    public LoginPresenterInterface createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.);
        ButterKnife.bind(this);
    }


    @Override
    public void LoginSuccesfull() {

    }
}