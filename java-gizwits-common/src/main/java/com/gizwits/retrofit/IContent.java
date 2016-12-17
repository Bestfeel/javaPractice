package com.gizwits.retrofit;

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
