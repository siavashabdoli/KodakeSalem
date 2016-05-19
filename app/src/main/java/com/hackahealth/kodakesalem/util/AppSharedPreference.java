package com.hackahealth.kodakesalem.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.mvp.objects.AuthenticationResponseObject;
import com.hackahealth.kodakesalem.mvp.objects.UserProfile;

/**
 * Created by siavash on 5/18/16.
 */
public class AppSharedPreference {

    private SharedPreferences mPreference;
    private Context mContext;
    public static final String STRING_PREF_UNAVAILABLE = "string preference unavailable";

    public AppSharedPreference(Context context) {
        this.mContext = context;
        this.mPreference = this.mContext.getSharedPreferences("app_preference", Context.MODE_PRIVATE);
    }

    /**
     * save the user authentication model to pref at sing up || sign in
     *
     * @param authModel
     */
    public void saveUserAuthenticationInfo(AuthenticationResponseObject authModel) {
        mPreference.edit()
                .putString(this.mContext.getString(R.string.app_pref_access_token), authModel.token)
                .putString(this.mContext.getString(R.string.app_pref_user_id), authModel.userProfile.id+"")
                .putString(this.mContext.getString(R.string.app_pref_user_name), authModel.userProfile.name)
                .apply();
    }

    /**
     * save the user model when user profile updated
     *
     * @param userModel
     */
    public void saveUserModel(UserProfile userModel) {
        mPreference.edit()
                .putString(this.mContext.getString(R.string.app_pref_user_id), userModel.id)
                .putString(this.mContext.getString(R.string.app_pref_user_name), userModel.name)
                .apply();
    }


    /**
     * get access token
     *
     * @return
     */
    public String getAccessToken() {
        return mPreference.getString(this.mContext.getString(R.string.app_pref_access_token), STRING_PREF_UNAVAILABLE);
    }

    /**
     * detect is user sign in
     *
     * @return
     */
    public boolean isAuthorized() {
        return !getAccessToken().equals(STRING_PREF_UNAVAILABLE);
    }


    /**
     * get user name
     *
     * @return
     */
    public String getUserName() {
        return mPreference.getString(this.mContext.getString(R.string.app_pref_user_name), "");
    }

    public String getUserId() {
        return mPreference.getString(this.mContext.getString(R.string.app_pref_user_id), "");
    }


    /**
     * remove all prefs in logout
     */
    public void removeAllPrefs() {
        mPreference.edit().clear().apply();
    }
}