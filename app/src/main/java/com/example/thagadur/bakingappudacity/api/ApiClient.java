package com.example.thagadur.bakingappudacity.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/";
    // public static final String BASE_URL = "http://192.168.1.120:8088/";
    private static Retrofit sRetrofit = null;

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
