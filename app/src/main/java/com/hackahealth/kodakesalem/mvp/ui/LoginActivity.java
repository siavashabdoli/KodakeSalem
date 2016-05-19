package com.hackahealth.kodakesalem.mvp.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Toast;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.mvp.presenter.LoginPresenter;
import com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface.LoginPresenterInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.LoginViewInterface;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends MvpActivity<LoginViewInterface, LoginPresenterInterface> implements LoginViewInterface,EasyPermissions.PermissionCallbacks  {

    private static String TAG;
    private static final int INTERNET_PERMISSION_REQUEST = 953;

    @Bind(R.id.activity_login_username)
    AppCompatEditText etUserName;

    @Bind(R.id.activity_login_password)
    AppCompatEditText etPassword;

    @Bind(R.id.activity_login_submit)
    AppCompatButton btnSubmit;

    @NonNull
    @Override
    public LoginPresenterInterface createPresenter() {
        return new LoginPresenter(LoginActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void LoginSuccessful() {
        Toast.makeText(this, R.string.login_successful,Toast.LENGTH_LONG).show();
        startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
        finish();
    }

    @Override
    public void LoginFailed() {
        Toast.makeText(this, R.string.login_failed,Toast.LENGTH_LONG).show();
    }
    @OnClick(R.id.activity_login_submit)
    public void onSubmit(){

        String perms[] = { Manifest.permission.INTERNET,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            getPresenter().login(etUserName.getText().toString(),etPassword.getText().toString());
        }
        else {
            EasyPermissions.requestPermissions(this,
                    getResources().getString(R.string.app_need_permission),
                    R.string.give_permission,
                    R.string.cansel,
                    INTERNET_PERMISSION_REQUEST, perms);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        getPresenter().login(etUserName.getText().toString(),etPassword.getText().toString());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}