package com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface;

import com.hackahealth.kodakesalem.mvp.ui.uiInterface.HomePageViewInterface;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * Created by siavash on 5/19/16.
 */
public interface HomePagePresenterInterface extends MvpPresenter<HomePageViewInterface> {
    void onNewChildClicked();
    void onReVisitChildClicked();
}
