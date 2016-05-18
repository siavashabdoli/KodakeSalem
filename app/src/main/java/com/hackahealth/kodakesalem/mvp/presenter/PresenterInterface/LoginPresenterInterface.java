package com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface;

import com.hackahealth.kodakesalem.mvp.ui.uiInterface.LoginViewInterface;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * Created by siavash on 4/28/16.
 */
public interface LoginPresenterInterface extends MvpPresenter<LoginViewInterface> {
    public void login(String username,String password);
}