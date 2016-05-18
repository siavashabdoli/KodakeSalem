package com.hackahealth.kodakesalem.mvp.presenter;

import com.hackahealth.kodakesalem.mvp.presenter.PresenterInterface.LoginPresenterInterface;
import com.hackahealth.kodakesalem.mvp.ui.uiInterface.LoginViewInterface;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPresenter extends MvpBasePresenter<LoginViewInterface> implements LoginPresenterInterface {

    @Override
    public void attachView(LoginViewInterface view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }



    @Override
    public void login(String username, String password) {

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