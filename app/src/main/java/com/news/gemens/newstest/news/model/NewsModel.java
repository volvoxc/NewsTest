package com.news.gemens.newstest.news.model;

import com.news.gemens.newstest.bean.CnBetaNewsList;
import com.news.gemens.newstest.bean.GuokeList;
import com.news.gemens.newstest.bean.TouTiaoList;
import com.news.gemens.newstest.bean.ZhiHuList;
import com.news.gemens.newstest.utils.Constant;
import com.news.gemens.newstest.utils.NetWorkUtil;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gemens on 2016/12/27/0027.
 */

public class NewsModel {
    public interface NewsCallBack<T> {
         void onSuccess(T t);
         void onFailed(Throwable t);
    }

    private static final String TAG = "NewsModel";

    private Call<CnBetaNewsList> cnBetaNewsListCall;
    private Call<ZhiHuList> zhiHuListCall;
    private Call<TouTiaoList> touTiaoListCall;
    private Call<GuokeList> guoKeListCall;

    public void getCnBetaNewsList(Map<String,Object> params, final NewsCallBack<CnBetaNewsList> callback) {
        cnBetaNewsListCall = NetWorkUtil.getNetWorkUtil().getApiService().getCnBetaList(params);
        cnBetaNewsListCall.enqueue(new Callback<CnBetaNewsList>() {
            @Override
            public void onResponse(Call<CnBetaNewsList> call, Response<CnBetaNewsList> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CnBetaNewsList> call, Throwable t) {
                callback.onFailed(t);
            }
        });
    }

    public void getZhihuList(String url,final NewsCallBack<ZhiHuList> callBack) {
        zhiHuListCall = NetWorkUtil.getNetWorkUtil().getApiService().getZhiHuDailyList(url);
        zhiHuListCall.enqueue(new Callback<ZhiHuList>() {
            @Override
            public void onResponse(Call<ZhiHuList> call, Response<ZhiHuList> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ZhiHuList> call, Throwable t) {
                callBack.onFailed(t);
            }
        });
    }

    public void getTouTiaoList(String url,final int num, final int page,final NewsCallBack<TouTiaoList> callBack) {
        touTiaoListCall = NetWorkUtil.getNetWorkUtil().getApiService().getTouTiaoData(url, Constant.TOU_TIAO_KEY,num,page);
        touTiaoListCall.enqueue(new Callback<TouTiaoList>() {
            @Override
            public void onResponse(Call<TouTiaoList> call, Response<TouTiaoList> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TouTiaoList> call, Throwable t) {
                callBack.onFailed(t);
            }
        });
    }

    public void getGuoKeList(final NewsCallBack<GuokeList> callBack) {
        guoKeListCall = NetWorkUtil.getNetWorkUtil().getApiService().getGuoKeList(Constant.GUO_KE_LIST);
        guoKeListCall.enqueue(new Callback<GuokeList>() {
            @Override
            public void onResponse(Call<GuokeList> call, Response<GuokeList> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<GuokeList> call, Throwable t) {
                callBack.onFailed(t);
            }
        });

    }
}
