package com.news.gemens.newstest.service;

import com.news.gemens.newstest.bean.CnBetaNewsList;
import com.news.gemens.newstest.bean.TouTiaoList;
import com.news.gemens.newstest.bean.ZhiHuList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Gemens on 2016/12/26/0026.
 */

public interface ApiService {

    //cnBeta首页列表数据
    @Headers({
            "Referer:http://www.cnbeta.com/",
            "Origin:http://www.cnbeta.com",
            "X-Requested-With:XMLHttpRequest"
    })
    @GET("/more")
    Call<CnBetaNewsList> getNewslistByPage(@QueryMap Map<String, Object> params);


    //知乎日报首页列表数据
    @GET()
    Call<ZhiHuList> getZhiHuDailyList(@Url String url);

    //头条新闻列表数据
    @GET()
    Call<TouTiaoList> getTouTiaoData(@Url String url, @Query("key") String key,@Query("num") int num);
}
