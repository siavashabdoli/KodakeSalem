package com.hackahealth.kodakesalem.network;

import com.hackahealth.kodakesalem.mvp.objects.AuthenticationResponseObject;
import com.hackahealth.kodakesalem.mvp.objects.ChildContact;
import com.hackahealth.kodakesalem.mvp.objects.FormItemObject;
import com.hackahealth.kodakesalem.mvp.objects.ResponseResultFormObject;
import com.hackahealth.kodakesalem.mvp.objects.ResultFormItemObject;
import com.hackahealth.kodakesalem.mvp.objects.UserLoginObject;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by siavash on 5/18/16.
 */
public interface APIService {
    @GET("question/{id}")
    Call<List<FormItemObject>> getFormById(@Path("id") String questionId);

    @POST("checklist/child/{childId}/day/{dayId}")
    Call<ResponseResultFormObject> sendProject(@Path("childIdTested") String childId,@Path("dayId") String CheckListId,@Body List<ResultFormItemObject> resultFormObjects);

    @POST("authentication/")
    Call<AuthenticationResponseObject> loginUser(@Body UserLoginObject userLoginObject);

    @GET("child/list/")
    Call<List<ChildContact>> getAllChildContact();

    @GET("child/list/{queryTerm}")
    Call<List<ChildContact>> queryChildContact(@Path("queryTerm")String queryTerm);

    @GET("doctor/list")
    Call<List<Objects>> getDoctors();

    @PUT("doctor/list")
    Call<List<Objects>> getDoctorById(@Path("tested") int doctorId);
}
