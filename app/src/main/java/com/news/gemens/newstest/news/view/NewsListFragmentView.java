package com.news.gemens.newstest.news.view;

import com.news.gemens.newstest.bean.CnBetaNewsList;
import com.news.gemens.newstest.bean.GuokeList;
import com.news.gemens.newstest.bean.TouTiaoList;
import com.news.gemens.newstest.bean.ZhiHuList;

/**
 * Created by Gemens on 2016/12/27/0027.
 */

public interface NewsListFragmentView {

    void showRefresh();
    void hideRefresh();

    void cnBetaNewsListRefreshSucceed(CnBetaNewsList cnBetaNewsList);
    void cnBetaNewsListRefreshFailed();

    void loadMoreCnBetaNewsListSucceed(CnBetaNewsList cnBetaNewsList);
    void loadMoreCnBetaNewsListFailed();


    void zhiHuListRefreshSucceed(ZhiHuList zhiHuList);
    void zhiHuListRefreshFailed();

    void loadMoreZhiHuListSucceed(ZhiHuList zhiHuList);
    void loadMoreZhiHuLisFailed();

    void touTiaoListRefreshSucceed(TouTiaoList touTiaoList);
    void touTiaoListRefreshFailed();

    void loadMoreTouTiaoListSucceed(TouTiaoList touTiaoList);
    void loadMoreTouTiaoListFailed();

    void guoKeListRefreshSucceed(GuokeList guokeList);
    void guoKeListRefreshFailed();
}
