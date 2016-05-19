package com.hackahealth.kodakesalem.mvp.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by siavash on 5/18/16.
 */
public class AuthenticationResponseObject {
    @SerializedName("userProfile")
    public UserProfile userProfile;

    @SerializedName("token")
    public String token;
}
