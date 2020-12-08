package com.example.android.logintask.Services;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Api mInstance;
    private Retrofit retrofit;
    private static final String BASE_URL = "https//databaseUrl";
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    private Api() {

        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    public static synchronized Api getInstance() {
        if (mInstance == null) {
            mInstance = new Api();
        }
        return mInstance;
    }

    public Interface apiInterface() {
        return retrofit.create(Interface.class);
    }

}

