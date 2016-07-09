package com.hackahealth.kodakesalem.mvp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.mvp.presenter.HomePagePresenter;
import com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface.HomePagePresenterInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.HomePageViewInterface;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by siavash on 5/19/16.
 */
public class HomePageActivity extends MvpActivity<HomePageViewInterface,HomePagePresenterInterface> implements HomePageViewInterface {

//    @Bind(R.id.home_page_name)
//    TextView tvUserName;

    @NonNull
    @Override
    public HomePagePresenterInterface createPresenter() {
        return new HomePagePresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        ButterKnife.bind(this);
        Window window=getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.blue_statusbar));
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @OnClick(R.id.home_page_createnew)
    @Override
    public void startNewChildCreate() {
        startActivity(new Intent(HomePageActivity.this,NewChildActivity.class));
    }

    @OnClick(R.id.home_page_revisit)
    @Override
    public void startChildReVisit() {
        startActivity(FragmentHolderActivity
                .newInstance(HomePageActivity.this, FragmentHolderActivity.CONTACT_SELECT_FRAGMENT));
    }

    @OnClick(R.id.home_page_process)
    public void startProccessPage(){
        Log.d("HomePageActivity","Process Clicked");
        startActivity(FragmentHolderActivity
                .newInstance(HomePageActivity.this, FragmentHolderActivity.PROCESS_FRAGMENT));
    }

    @Override
    public void setUserName(String userName) {
//        tvUserName.setText(userName);
        Log.d("test","only for testing");
    }
}
