package com.hackahealth.kodakesalem.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.mvp.objects.ChildContact;
import com.hackahealth.kodakesalem.mvp.presenter.SelectContactPresenter;
import com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface.SelectContactPresenterInterface;
import com.hackahealth.kodakesalem.mvp.ui.adapter.ChildContactAdapter;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.SelectContactViewInterface;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by siavash on 5/20/16.
 */
public class ContactSelectFragment extends MvpLceFragment<SwipeRefreshLayout,List<ChildContact>,SelectContactViewInterface,SelectContactPresenterInterface> implements  SelectContactViewInterface{

    private static final String CHILD_ID = "childID";
    private ChildContactAdapter childContactAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;


    public static ContactSelectFragment newInstanFragment(){
        ContactSelectFragment fragment=new ContactSelectFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_contact_select, container, false);
        ButterKnife.bind(this, rootview);
        childContactAdapter=new ChildContactAdapter(getActivity(), new ArrayList<ChildContact>(), new CallbackClass() {
            @Override
            public void onItemClick(int position) {
                presenter.onItemSelected(position);
            }
        });
        return rootview;
    }


    @Override
    public SelectContactPresenterInterface createPresenter() {
        return new SelectContactPresenter();
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
//        startActivity(getActivity(),ChildProfile.newInstance(childId));
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
    public interface CallbackClass{
        public void onItemClick(int position);
    }
}
