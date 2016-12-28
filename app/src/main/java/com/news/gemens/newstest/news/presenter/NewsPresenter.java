package com.news.gemens.newstest.news.presenter;

import com.news.gemens.newstest.bean.CnBetaNewsList;
import com.news.gemens.newstest.bean.GuokeList;
import com.news.gemens.newstest.bean.TouTiaoList;
import com.news.gemens.newstest.bean.ZhiHuList;
import com.news.gemens.newstest.news.model.NewsModel;
import com.news.gemens.newstest.news.view.NewsListFragment;
import com.news.gemens.newstest.utils.Constant;
import com.news.gemens.newstest.utils.DateUtil;

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
    private int touTiaoListPage = 1;
    private int zhihuListPage = 0;
    private NewsListFragment view;

    public NewsPresenter (NewsListFragment view) {
        this.view = view;
        newsModel = new NewsModel();
        cnBetaNewsListParams = new HashMap<>();
        cnBetaNewsListParams.put("type", Constant.CNBETA_NEWS_LIST_TYPE);
    }

    public void refreshCnBetaNewsList() {
        view.showRefresh();
        cnBetaNewsListParams.put("page",String.valueOf(cnBetaNewsListPage));
        cnBetaNewsListParams.put("_", System.currentTimeMillis() + "");
        getCnBetaNewsList(cnBetaNewsListParams,true);
    }

    public void getMoreCnBetaNewsList() {
        cnBetaNewsListParams.put("page",String.valueOf(cnBetaNewsListPage));
        cnBetaNewsListParams.put("_", System.currentTimeMillis() + "");
        getCnBetaNewsList(cnBetaNewsListParams,false);
    }

    private void getCnBetaNewsList(Map<String,Object> params, final boolean isRefresh) {
        newsModel.getCnBetaNewsList(params, new NewsModel.NewsCallBack<CnBetaNewsList>() {
            @Override
            public void onSuccess(CnBetaNewsList cnBetaNewsList) {
                view.hideRefresh();
                if (isRefresh) {
                    view.cnBetaNewsListRefreshSucceed(cnBetaNewsList);
                }else {
                    view.loadMoreCnBetaNewsListSucceed(cnBetaNewsList);
                    cnBetaNewsListPage ++;
                }
            }

            @Override
            public void onFailed(Throwable t) {
                view.hideRefresh();
                if (isRefresh) {
                    view.cnBetaNewsListRefreshFailed();
                } else {
                    view.loadMoreCnBetaNewsListFailed();
                }
            }
        });
    }

    public void refreshZhiHuList() {
        view.showRefresh();
        getZhiHuList(Constant.ZHIHU_LIST,true);
    }

    public void getMoreZhiHuList() {
        getZhiHuList(Constant.ZHIHU_LIST_BEFORE + DateUtil.getDateBeforeByDay(zhihuListPage++),false);
    }

    private void getZhiHuList(String url, final boolean isRefresh) {
        newsModel.getZhihuList(url, new NewsModel.NewsCallBack<ZhiHuList>() {
            @Override
            public void onSuccess(ZhiHuList zhiHuList) {
                view.hideRefresh();
                if (isRefresh) {
                    view.zhiHuListRefreshSucceed(zhiHuList);
                } else {
                    view.loadMoreZhiHuListSucceed(zhiHuList);
                }
            }

            @Override
            public void onFailed(Throwable t) {
                view.hideRefresh();
                if (isRefresh) {
                    view.zhiHuListRefreshFailed();
                } else {
                    view.loadMoreZhiHuLisFailed();
                }

            }
        });
    }

    public void refreshTouTiaoList() {
        view.showRefresh();
        getTouTiaoList(Constant.TOU_TIAO_LIST,Constant.TOU_TIAO_PAGE_NUM,1,true);
    }

    public void getMoreTouTiaoList() {
        getTouTiaoList(Constant.TOU_TIAO_LIST,Constant.TOU_TIAO_PAGE_NUM,touTiaoListPage,false);
    }

    private void getTouTiaoList(String url, int num, int page, final boolean isResfresh) {
        newsModel.getTouTiaoList(url, num, page,new NewsModel.NewsCallBack<TouTiaoList>() {
            @Override
            public void onSuccess(TouTiaoList touTiaoList) {
                view.hideRefresh();
                if (isResfresh) {
                    view.touTiaoListRefreshSucceed(touTiaoList);
                } else {
                    view.loadMoreTouTiaoListSucceed(touTiaoList);
                    touTiaoListPage ++;
                }
            }

            @Override
            public void onFailed(Throwable t) {
                view.hideRefresh();
                if (isResfresh) {
                    view.touTiaoListRefreshFailed();
                } else {
                    view.loadMoreTouTiaoListFailed();
                }
            }
        });
    }

    public void getGuoKeList() {
        view.showRefresh();
        newsModel.getGuoKeList(new NewsModel.NewsCallBack<GuokeList>() {
            @Override
            public void onSuccess(GuokeList guokeList) {
                view.hideRefresh();
                view.guoKeListRefreshSucceed(guokeList);
            }

            @Override
            public void onFailed(Throwable t) {
                view.hideRefresh();
                view.guoKeListRefreshFailed();
            }
        });
    }
}
