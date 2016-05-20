package com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.mvp.objects.ChildContact;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.SelectContactViewInterface;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by siavash on 5/20/16.
 */
public class ContactSelectFragment extends MvpLceFragment<SwipeRefreshLayout,List<ChildContact>,SelectContactViewInterface,SelectContactPresenterInterface> implements  SelectContactViewInterface{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_contact_select, container, false);
        ButterKnife.bind(this, rootview);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public SelectContactPresenterInterface createPresenter() {
        return null;
    }

    @Override
    public void setData(List<ChildContact> childContacts) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadCountries(pullToRefresh);
    }

    @Override
    public void clearData() {

    }

    @Override
    public void openChildProfile(int childId) {

    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return getActivity().getString(R.string.error_try_again);
    }
}
