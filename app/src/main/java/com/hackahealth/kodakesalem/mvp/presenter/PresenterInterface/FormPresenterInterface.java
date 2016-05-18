package com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface;

import com.hackahealth.kodakesalem.mvp.objects.FormItemObject;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.FormViewInterface;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

/**
 * Created by siavash on 5/18/16.
 */
public interface FormPresenterInterface extends MvpPresenter<FormViewInterface>{
    public void FormSubmit();
    public void backPressed();
    public void initData(int id);

}
