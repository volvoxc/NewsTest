package com.news.gemens.newstest.main;

import android.app.Application;

/**
 * Created by Gemens on 2017/2/8/0008.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance () {
        return instance;
    }
}
