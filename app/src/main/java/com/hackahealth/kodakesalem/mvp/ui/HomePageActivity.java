package com.hackahealth.kodakesalem.mvp.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.mvp.presenter.HomePagePresenter;
import com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface.HomePagePresenterInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.HomePageViewInterface;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by siavash on 5/19/16.
 */
public class HomePageActivity extends MvpActivity<HomePageViewInterface,HomePagePresenterInterface> implements HomePageViewInterface {

//    @Bind(R.id.home_page_name)
//    TextView tvUserName;

    @NonNull
    @Override
    public HomePagePresenterInterface createPresenter() {
        return new HomePagePresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

    }
    @OnClick(R.id.home_page_createnew)
    @Override
    public void startNewChildCreate() {

    }

    @OnClick(R.id.home_page_revisit)
    @Override
    public void startChildReVisit() {

    }

    @Override
    public void setUserName(String userName) {
//        tvUserName.setText(userName);
    }
}
