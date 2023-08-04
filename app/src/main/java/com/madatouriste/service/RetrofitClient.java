package com.madatouriste.service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit;
    //    private static final String BASE_URL = "http://192.168.88.101:3900";
    public static OkHttpClient client;
    public  static final String BASE_URL = "http://192.168.56.1:3900";
//    public static final String BASE_URL = "https://tourism-3f7a.onrender.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
