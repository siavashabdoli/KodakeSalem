package com.hackahealth.kodakesalem.mvp.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.mvp.objects.AuthenticationResponseObject;
import com.hackahealth.kodakesalem.mvp.objects.UserLoginObject;
import com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface.LoginPresenterInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.LoginViewInterface;
import com.hackahealth.kodakesalem.network.APIService;
import com.hackahealth.kodakesalem.network.ApiProvider;
import com.hackahealth.kodakesalem.util.AppSharedPreference;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends MvpBasePresenter<LoginViewInterface> implements LoginPresenterInterface {


    private final Context context;
    APIService mService;
    private Call<AuthenticationResponseObject> call;
    private UserLoginObject userLoginObject;

    public LoginPresenter(Context context){
        this.context=context;
        ApiProvider provider= new ApiProvider();
        mService=provider.getTService();
    }
    @Override
    public void attachView(LoginViewInterface view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if(call!=null)
        call.cancel();

    }



    @Override
    public void login(String username, String password) {
        userLoginObject = new UserLoginObject(username, password);

            loginSend();


    }
    private void loginSend() {
        call = mService.loginUser(userLoginObject);
        call.enqueue(new Callback<AuthenticationResponseObject>() {
            @Override
            public void onResponse(Call<AuthenticationResponseObject> call, Response<AuthenticationResponseObject> response) {
                if (response.isSuccess()) {
                    AppSharedPreference appSharedPreference = new AppSharedPreference(context);
                    appSharedPreference.saveUserAuthenticationInfo(response.body());
                    getView().LoginSuccessful();
                } else {
                    getView().LoginFailed();
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponseObject> call, Throwable t) {
                Log.d("LoginPresenter","Unexpected Error: Retrofit failed to convert json");
            }
        });
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }



}