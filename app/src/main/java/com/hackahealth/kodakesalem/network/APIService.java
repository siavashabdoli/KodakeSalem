package com.hackahealth.kodakesalem.network;

import com.hackahealth.kodakesalem.mvp.objects.FormItemObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by siavash on 5/18/16.
 */
public interface APIService {
    @GET("question/{id}")
    Call<List<FormItemObject>> getFormById(@Path("id") String questionId);
}
