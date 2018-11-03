package com.github.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IContent {

    /**
     * 定义一个请求接口
     *
     * @return
     */
    @GET("site/js/list.json")
    Call<FileContent> getContent();
}
