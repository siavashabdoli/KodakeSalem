package com.hackahealth.kodakesalem.mvp.ui.uiInterface;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by siavash on 5/19/16.
 */
public interface HomePageViewInterface extends MvpView {
    void startNewChildCreate();
    void startChildReVisit();
    void setUserName(String userName);
}
