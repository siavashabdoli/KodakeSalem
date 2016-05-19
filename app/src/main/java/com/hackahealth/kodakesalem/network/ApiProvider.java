package com.hackahealth.kodakesalem.network;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hackahealth.kodakesalem.MyApp;
import com.hackahealth.kodakesalem.util.AppSharedPreference;
import com.hackahealth.kodakesalem.util.ServerConfig;

import java.io.IOException;
import java.util.Date;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by siavash on 5/18/16.
 */
public class ApiProvider {


    private APIService mTService;
    private Retrofit mRetrofitClient;
    private AppSharedPreference mAppPreferenceTools;

    public ApiProvider(){
        this.mAppPreferenceTools = new AppSharedPreference(MyApp.applicationContext);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //add http interceptor to add headers to each request
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                String originalPath = original.url().url().getPath();

                    Request.Builder requestBuilder = original.newBuilder();

                    requestBuilder.addHeader("Accept", "application/json");

                    if (mAppPreferenceTools.isAuthorized()) {
                        requestBuilder.addHeader("Authorization", mAppPreferenceTools.getAccessToken());
                    }
                    requestBuilder.method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);

            }
        });

        Gson gson = new GsonBuilder()
                .create();

        mRetrofitClient = new Retrofit.Builder()
                .baseUrl(ServerConfig.REST_API_BASE_URL) // set Base URL , should end with '/'
                .client(httpClient.build()) // add http client
                .addConverterFactory(GsonConverterFactory.create(gson))//add gson converter
                .build();
        mTService = mRetrofitClient.create(APIService.class);
    }
    /**
     * can get Retrofit Service
     *
     * @return
     */
    public APIService getTService() {
        return mTService;
    }

    /**
     * get Retrofit client
     * used in ErrorUtil class
     *
     * @return
     */
    public Retrofit getRetrofitClient() {
        return mRetrofitClient;
    }
}
