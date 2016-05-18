package com.hackahealth.kodakesalem.mvp.ui.uiInterface;

import com.hackahealth.kodakesalem.mvp.objects.FormItemObject;
import com.hackahealth.kodakesalem.mvp.objects.ResultFormItemObject;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by siavash on 5/18/16.
 */
public interface FormViewInterface extends MvpView {
    public void clearLastData();

    public void addViewForm(List<FormItemObject> list);

    public List<ResultFormItemObject> getData();

    public void showSuccessfulMessage(String message);

    public void exitView();

    public void sendError();
}