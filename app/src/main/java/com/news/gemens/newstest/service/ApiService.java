package com.news.gemens.newstest.service;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by Gemens on 2016/12/26/0026.
 */

public interface ApiService {

    @Headers({
            "Referer:http://www.cnbeta.com/",
            "Origin:http://www.cnbeta.com",
            "X-Requested-With:XMLHttpRequest"
    })
    @GET("/more")
    Call<Object> getNewslistByPage(@QueryMap Map<String, Object> params);
}
