package com.hackahealth.kodakesalem.mvp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.mvp.presenter.FragmentContainerPresenter;
import com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface.FragmentContainerPresenterInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.FragmentContainerViewInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.ProcessFragment;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.back_btn)
    ImageButton btnBack;

    public static Intent newInstance(Context context,int fragmentNum){
        Intent intent=new Intent(context,FragmentHolderActivity.class);
        intent.putExtra(FRAGMENT_ID,fragmentNum);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        ButterKnife.bind(this);
        Bundle bundle=getIntent().getExtras();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        fragmentID= PROCESS_FRAGMENT;
        if(bundle!=null){
            fragmentID=bundle.getInt(FRAGMENT_ID, PROCESS_FRAGMENT);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        ReplaceFragment();
    }

    @NonNull
    @Override
    public FragmentContainerPresenterInterface createPresenter() {
        return new FragmentContainerPresenter();
    }

    @OnClick(R.id.back_btn)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
                startActivity(new Intent(FragmentHolderActivity.this,NewChildActivity.class));
                break;
            case CHECKLIST_FRAGMENT:
                fragmentTransaction.replace(R.id.container,new CheckListFragment());
                break;
        }
    }

}
