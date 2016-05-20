package com.hackahealth.kodakesalem.mvp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.mvp.presenter.FragmentContainerPresenter;
import com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface.FragmentContainerPresenterInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.FragmentContainerViewInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.ProcessFragment;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

/**
 * Created by siavash on 5/20/16.
 */
public class FragmentHolderActivity extends MvpActivity<FragmentContainerViewInterface,FragmentContainerPresenterInterface>
        implements FragmentContainerViewInterface {

    private static final String FRAGMENT_ID = "fragmentIdToRun";
    public static final int PROCESS_FRAGMENT = 1;
    public static final int CONTACT_SELECT_FRAGMENT = 2;
    public static final int CONTACT_NEW_CHILD = 3;
    public static final int CHECKLIST_FRAGMENT = 4;
    private int fragmentID;
    public static Intent newInstance(Context context,int fragmentNum){
        Intent intent=new Intent(context,FragmentHolderActivity.class);
        intent.putExtra(FRAGMENT_ID,fragmentNum);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        Bundle bundle=getIntent().getExtras();
        fragmentID= PROCESS_FRAGMENT;
        if(bundle!=null){
            fragmentID=bundle.getInt(FRAGMENT_ID, PROCESS_FRAGMENT);
            ReplaceFragment();
        }

    }

    @NonNull
    @Override
    public FragmentContainerPresenterInterface createPresenter() {
        return new FragmentContainerPresenter();
    }
    public void ReplaceFragment(){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (fragmentID){
            case PROCESS_FRAGMENT:
                fragmentTransaction.replace(R.id.container, ProcessFragment.newInstance()).commit();
                break;
            case CONTACT_SELECT_FRAGMENT:
                fragmentTransaction.replace(R.id.container,ContactSelectFragment.newInstanFragment()).commit();
                break;
            case CONTACT_NEW_CHILD:
//                fragmentTransaction
                break;
            case CHECKLIST_FRAGMENT:
                fragmentTransaction.replace(R.id.container,new CheckListFragment());
                break;
        }
    }
}
