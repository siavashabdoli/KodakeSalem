package com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface;

import com.hackahealth.kodakesalem.mvp.ui.uiInterface.SelectContactViewInterface;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * Created by siavash on 5/19/16.
 */
public interface SelectContactPresenterInterface extends MvpPresenter<SelectContactViewInterface> {
    void onItemSelected(int index);
    void onSearchClicked(String query,boolean pullToRefresh);
    public void loadCountries(final boolean pullToRefresh);
}
