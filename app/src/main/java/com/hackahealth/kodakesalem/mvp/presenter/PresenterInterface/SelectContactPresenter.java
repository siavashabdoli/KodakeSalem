package com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface;

import com.hackahealth.kodakesalem.mvp.objects.ChildContact;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.SelectContactViewInterface;
import com.hackahealth.kodakesalem.network.APIService;
import com.hackahealth.kodakesalem.network.ApiProvider;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by siavash on 5/20/16.
 */
public class SelectContactPresenter extends MvpBasePresenter<SelectContactViewInterface> implements SelectContactPresenterInterface {
    APIService mService;
    List<ChildContact> childContacts;
    Call<List<ChildContact>> contactGetCall;
    public SelectContactPresenter(){
        mService= ApiProvider.getTService();

    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(contactGetCall!=null && !contactGetCall.isCanceled())
        contactGetCall.cancel();
    }

    @Override
    public void onItemSelected(int index) {
        ChildContact childContact=childContacts.get(index);
        getView().openChildProfile(childContact.getId());
    }

    @Override
    public void onSearchClicked(String query) {
        if(contactGetCall!=null&& !contactGetCall.isCanceled())
            contactGetCall.cancel();

        if (query!=null&&!query.equals(""))
            contactGetCall =mService.queryChildContact(query);
        else
            contactGetCall =mService.getAllChildContact();
        contactGetCall.enqueue(new Callback<List<ChildContact>>() {
            @Override
            public void onResponse(Call<List<ChildContact>> call, Response<List<ChildContact>> response) {
                if (response.isSuccessful()) {
                    getView().setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ChildContact>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadCountries(boolean pullToRefresh) {
        onSearchClicked("");
    }
}
