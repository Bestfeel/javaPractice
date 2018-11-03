/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.github.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by feel on 2016/12/5.
 */
public interface IContent {

    //  定义一个请求接口
    @GET("site/js/list.json")
    Call<FileContent> getContent();
}
