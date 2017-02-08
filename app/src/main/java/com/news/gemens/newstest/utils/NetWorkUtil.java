package com.news.gemens.newstest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.news.gemens.newstest.main.MyApplication;
import com.news.gemens.newstest.service.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gemens on 2016/12/26/0026.
 */

public class NetWorkUtil {

    private static NetWorkUtil netWorkUtil;
    private Retrofit retrofit;

    private NetWorkUtil(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetWorkUtil getNetWorkUtil() {

        if (netWorkUtil == null) {
            synchronized (NetWorkUtil.class) {
                if (netWorkUtil == null) {
                    netWorkUtil = new NetWorkUtil();
                }
            }
        }
        return netWorkUtil;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }


    public static boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        return networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI;
    }
}
