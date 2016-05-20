package com.hackahealth.kodakesalem.mvp.ui.uiInterface;

import com.hackahealth.kodakesalem.mvp.objects.ChildContact;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

/**
 * Created by siavash on 5/19/16.
 */
public interface SelectContactViewInterface extends MvpView, MvpLceView<List<ChildContact>> {
    void setData(List<ChildContact> childContacts);
    void clearData();
    void openChildProfile(int childId);
    void showLoading(boolean pullToRefresh);
    void hideLoading();
}
