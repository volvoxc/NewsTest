package com.news.gemens.newstest.news.view;

import com.news.gemens.newstest.bean.CnBetaNewsList;
import com.news.gemens.newstest.bean.ZhiHuList;

/**
 * Created by Gemens on 2016/12/27/0027.
 */

public interface NewsListFragmentView {

    void cnBetaNewsListRefreshSucceed(CnBetaNewsList cnBetaNewsList);
    void cnBetaNewsListRefreshFailed();

    void loadMoreCnBetaNewsListSucceed(CnBetaNewsList cnBetaNewsList);
    void loadMoreCnBetaNewsListFailed();


    void zhiHuListRefreshSucceed(ZhiHuList zhiHuList);
    void zhiHuListRefreshFailed();

    void loadMoreZhiHuListSucceed(ZhiHuList zhiHuList);
    void loadMoreZhiHuLisFailed();

}