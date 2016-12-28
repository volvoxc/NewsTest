package com.news.gemens.newstest.news.presenter;

import com.news.gemens.newstest.bean.CnBetaNewsList;
import com.news.gemens.newstest.bean.TouTiaoList;
import com.news.gemens.newstest.bean.ZhiHuList;
import com.news.gemens.newstest.news.model.NewsModel;
import com.news.gemens.newstest.news.view.NewsListFragment;
import com.news.gemens.newstest.utils.Constant;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Gemens on 2016/12/26/0026.
 */

public class NewsPresenter {

    private static final String TAG = "NewsPresenter";
    private NewsModel newsModel;
    private Map<String,Object> cnBetaNewsListParams;
    private int cnBetaNewsListPage = 1;
    private NewsListFragment view;

    public NewsPresenter (NewsListFragment view) {
        this.view = view;
        newsModel = new NewsModel();
        cnBetaNewsListParams = new HashMap<>();
        cnBetaNewsListParams.put("type", Constant.CNBETA_NEWS_LIST_TYPE);
    }

    public void refreshCnBetaNewsList() {
        cnBetaNewsListParams.put("page",String.valueOf(cnBetaNewsListPage));
        cnBetaNewsListParams.put("_", System.currentTimeMillis() + "");
        getCnBetaNewsList(cnBetaNewsListParams);
    }

    public void getMoreCnBetaNewsList() {
        cnBetaNewsListPage += 1;

        cnBetaNewsListParams.put("page",String.valueOf(cnBetaNewsListPage));
        cnBetaNewsListParams.put("_", System.currentTimeMillis() + "");
        getCnBetaNewsList(cnBetaNewsListParams);
    }

    public void getCnBetaNewsList(Map<String,Object> params) {
        newsModel.getCnBetaNewsList(params, new NewsModel.NewsCallBack<CnBetaNewsList>() {
            @Override
            public void onSuccess(CnBetaNewsList cnBetaNewsList) {
                view.cnBetaNewsListRefreshSucceed(cnBetaNewsList);
            }

            @Override
            public void onFailed(Throwable t) {
                view.cnBetaNewsListRefreshFailed();
            }
        });
    }

    public void getMoreZhiHuList() {

    }

    public void getZhiHuList() {
        newsModel.getZhihuList(Constant.ZHIHU_LIST, new NewsModel.NewsCallBack<ZhiHuList>() {
            @Override
            public void onSuccess(ZhiHuList zhiHuList) {
                view.zhiHuListRefreshSucceed(zhiHuList);
            }

            @Override
            public void onFailed(Throwable t) {
                view.zhiHuListRefreshFailed();
            }
        });
    }

    public void getTouTiaoList() {
        newsModel.getTouTiaoList(Constant.TOU_TIAO_LIST, Constant.TOU_TIAO_PAGE_NUM, new NewsModel.NewsCallBack<TouTiaoList>() {
            @Override
            public void onSuccess(TouTiaoList touTiaoList) {
                view.touTiaoListRefreshSucceed(touTiaoList);
            }

            @Override
            public void onFailed(Throwable t) {
                view.touTiaoListRefreshFailed();
            }
        });
    }
}
