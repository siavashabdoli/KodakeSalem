package com.hackahealth.kodakesalem.mvp.presenter;

import android.content.Context;

import com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface.HomePagePresenterInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.HomePageViewInterface;
import com.hackahealth.kodakesalem.util.AppSharedPreference;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

/**
 * Created by siavash on 5/19/16.
 */
public class HomePagePresenter extends MvpBasePresenter<HomePageViewInterface> implements HomePagePresenterInterface {

    private final Context context;
    private final AppSharedPreference appSharedPreference;

    public HomePagePresenter(Context context){
        this.context=context;
        appSharedPreference=new AppSharedPreference(context);
    }

    @Override
    public void attachView(HomePageViewInterface view) {
        super.attachView(view);
        String userNameText=appSharedPreference.getUserName();
        getView().setUserName(userNameText);
    }

    @Override
    public void onNewChildClicked() {
        getView().startNewChildCreate();
    }

    @Override
    public void onReVisitChildClicked() {
        getView().startChildReVisit();
    }


}
