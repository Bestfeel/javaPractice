/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.github.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by feel on 2016/12/5.
 * <p>
 * link:https://github.com/hongyangAndroid/okhttputils
 */
public class RetrofitHelloWorld {

    private static final String URL = "https://www.feel88.cn/";

    /**
     * 获取Retrofit适配器。
     *
     * @return 网络适配器
     */
    public static Retrofit newRetrofit() throws Exception {
        return new Retrofit.Builder().baseUrl(URL)
                .client(getClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * 获取 OkHttpClient
     *
     * @return OkHttpClient
     */
    public static OkHttpClient.Builder getClient() throws Exception {


        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);


        return new OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor());//日志


    }


    public static void main(String[] args) throws Exception {


        Retrofit retrofit = newRetrofit();


        IContent iContent = retrofit.create(IContent.class);


        Call<FileContent> content = iContent.getContent();


        content.enqueue(new Callback<FileContent>() {
            @Override
            public void onResponse(Call<FileContent> call, Response<FileContent> response) {

                if (response != null) {

                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<FileContent> call, Throwable throwable) {

                throwable.getStackTrace();

                System.out.println(throwable.getMessage());
            }
        });


        // content.cancel();


        // end main
    }
}
