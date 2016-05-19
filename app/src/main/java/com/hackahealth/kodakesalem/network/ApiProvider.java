package com.hackahealth.kodakesalem.network;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hackahealth.kodakesalem.MyApp;
import com.hackahealth.kodakesalem.util.AppSharedPreference;
import com.hackahealth.kodakesalem.util.ServerConfig;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by siavash on 5/18/16.
 */

public class ApiProvider {


    private static APIService mTService;
    private Retrofit mRetrofitClient;
    private AppSharedPreference mAppPreferenceTools;

    private ApiProvider(){
        this.mAppPreferenceTools = new AppSharedPreference(MyApp.applicationContext);

        //add http interceptor to add headers to each request
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        String originalPath = original.url().url().getPath();

                        Request.Builder requestBuilder = original.newBuilder();

                        requestBuilder.addHeader("Accept", "application/json");

                        if (mAppPreferenceTools.isAuthorized()) {
                            requestBuilder.addHeader("Api-Token", mAppPreferenceTools.getAccessToken());
                        }
                        requestBuilder.method(original.method(), original.body());
                        Request request = requestBuilder.build();
                        return chain.proceed(request);

                    }
                })
                .build();

        Gson gson = new GsonBuilder()
                .create();

        mRetrofitClient = new Retrofit.Builder()
                .baseUrl(ServerConfig.REST_API_BASE_URL) // set Base URL , should end with '/'
                .client(httpClient) // add http client
                .addConverterFactory(GsonConverterFactory.create(gson))//add gson converter
                .build();
        mTService = mRetrofitClient.create(APIService.class);
    }
    /**
     * can get Retrofit Service
     *
     * @return
     */
    public static APIService getTService() {
        if(mTService!=null)
        return mTService;

        new ApiProvider();

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
