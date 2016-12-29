package com.news.gemens.newstest.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.gemens.newstest.R;
import com.news.gemens.newstest.bean.CnBetaNewsItem;
import com.news.gemens.newstest.bean.CnBetaNewsList;
import com.news.gemens.newstest.bean.GuoKeItem;
import com.news.gemens.newstest.bean.GuokeList;
import com.news.gemens.newstest.bean.TouTiaoItem;
import com.news.gemens.newstest.bean.TouTiaoList;
import com.news.gemens.newstest.bean.ZhiHuItem;
import com.news.gemens.newstest.bean.ZhiHuList;
import com.news.gemens.newstest.news.adapter.NewsAdapter;
import com.news.gemens.newstest.news.presenter.NewsPresenter;

import java.util.ArrayList;
import java.util.List;

public class NewsListFragment extends Fragment implements NewsListFragmentView,SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "NewsListFragment";

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private NewsAdapter cnBetaAdapter,zhiHuAdapter,touTiaoAdapter,guoKeAdapter;

    private List<CnBetaNewsItem> cnBetaNewsItems = new ArrayList<>();
    private List<ZhiHuItem> zhiHuItems = new ArrayList<>();
    private List<TouTiaoItem> touTiaoItems = new ArrayList<>();
    private List<GuoKeItem> guoKeItems = new ArrayList<>();

    private View view;
    private String type;
    private boolean isLoading;
    private int lastVisibleItem,totalItemCount;
    private NewsPresenter newsPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_list, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        type = getArguments().getString("type");
        init(view,type);
    }

    private void init(View view,String type){
        recyclerView = (RecyclerView) view.findViewById(R.id.news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.news_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        newsPresenter = new NewsPresenter(this);
        switch (type) {
            case "头条":
                if (touTiaoAdapter == null)
                    touTiaoAdapter = new NewsAdapter(getActivity(),touTiaoItems,"头条");
                recyclerView.setAdapter(touTiaoAdapter);
                newsPresenter.refreshTouTiaoList();
                recyclerView.addOnScrollListener(new ScrollListener("头条"));
                break;
            case "cnBeta":
                if (cnBetaAdapter == null)
                    cnBetaAdapter = new NewsAdapter(getActivity(),cnBetaNewsItems,"cnBeta");
                recyclerView.setAdapter(cnBetaAdapter);
                newsPresenter.refreshCnBetaNewsList();
                recyclerView.addOnScrollListener(new ScrollListener("cnBeta"));
                break;
            case "知乎日报":
                if (zhiHuAdapter == null)
                    zhiHuAdapter = new NewsAdapter(getActivity(),zhiHuItems,"知乎日报");
                recyclerView.setAdapter(zhiHuAdapter);
                newsPresenter.refreshZhiHuList();
                recyclerView.addOnScrollListener(new ScrollListener("知乎日报"));
                break;
            case "果壳精选":
                if (guoKeAdapter == null)
                    guoKeAdapter = new NewsAdapter(getActivity(),guoKeItems,"果壳精选");
                recyclerView.setAdapter(guoKeAdapter);
                newsPresenter.getGuoKeList();
                break;
        }
    }

    @Override
    public void onRefresh() {
        switch (type) {
            case "头条":
                newsPresenter.refreshTouTiaoList();
                break;
            case "cnBeta":
                newsPresenter.refreshCnBetaNewsList();
                break;
            case "知乎日报":
                newsPresenter.refreshZhiHuList();
                break;
            case "果壳精选":
                newsPresenter.getGuoKeList();
                break;
        }
    }

    @Override
    public void showRefresh() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void hideRefresh() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (touTiaoAdapter != null && touTiaoAdapter.isShowFooter()) {
            touTiaoAdapter.setIsShowFooter(false);
            touTiaoAdapter.notifyDataSetChanged();
        }
        if (cnBetaAdapter != null && cnBetaAdapter.isShowFooter()) {
            cnBetaAdapter.setIsShowFooter(false);
            cnBetaAdapter.notifyDataSetChanged();
        }
        if (zhiHuAdapter != null && zhiHuAdapter.isShowFooter()) {
            zhiHuAdapter.setIsShowFooter(false);
            zhiHuAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void cnBetaNewsListRefreshSucceed(CnBetaNewsList cnBetaNewsList) {
        cnBetaNewsItems.clear();
        cnBetaNewsItems.addAll(cnBetaNewsList.getResult().getList());
        cnBetaAdapter.notifyDataSetChanged();
    }

    @Override
    public void cnBetaNewsListRefreshFailed() {
    }

    @Override
    public void loadMoreCnBetaNewsListSucceed(CnBetaNewsList cnBetaNewsList) {
        cnBetaNewsItems.addAll(cnBetaNewsList.getResult().getList());
        cnBetaAdapter.notifyDataSetChanged();
        isLoading = false;
    }

    @Override
    public void loadMoreCnBetaNewsListFailed() {
    }

    @Override
    public void zhiHuListRefreshSucceed(ZhiHuList zhiHuList) {
        zhiHuItems.clear();
        zhiHuItems.addAll(zhiHuList.getStories());
        zhiHuAdapter.notifyDataSetChanged();
    }

    @Override
    public void zhiHuListRefreshFailed() {
    }

    @Override
    public void loadMoreZhiHuListSucceed(ZhiHuList zhiHuList) {
        zhiHuItems.addAll(zhiHuList.getStories());
        zhiHuAdapter.notifyDataSetChanged();
        isLoading = false;
    }

    @Override
    public void loadMoreZhiHuLisFailed() {
    }

    @Override
    public void touTiaoListRefreshSucceed(TouTiaoList touTiaoList) {
        touTiaoItems.clear();
        touTiaoItems.addAll(touTiaoList.getNewslist());
        touTiaoAdapter.notifyDataSetChanged();
    }

    @Override
    public void touTiaoListRefreshFailed() {
    }

    @Override
    public void loadMoreTouTiaoListSucceed(TouTiaoList touTiaoList) {
        touTiaoItems.addAll(touTiaoList.getNewslist());
        touTiaoAdapter.notifyDataSetChanged();
        isLoading = false;
    }

    @Override
    public void loadMoreTouTiaoListFailed() {
    }

    @Override
    public void guoKeListRefreshSucceed(GuokeList guokeList) {
        guoKeItems.clear();
        guoKeItems.addAll(guokeList.getResult());
        guoKeAdapter.notifyDataSetChanged();
    }

    @Override
    public void guoKeListRefreshFailed() {
    }

    private class ScrollListener extends RecyclerView.OnScrollListener {
        private String type;

        public ScrollListener(String type) {
            this.type = type;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            totalItemCount = recyclerView.getLayoutManager().getItemCount();
            lastVisibleItem = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();

            if ((lastVisibleItem + 1) == totalItemCount && !isLoading && newState == RecyclerView.SCROLL_STATE_IDLE ) {
                isLoading = true;
                if ("头条".equals(type)) {
                    newsPresenter.getMoreTouTiaoList();
                } else if ("cnBeta".equals(type)) {
                    newsPresenter.getMoreCnBetaNewsList();
                } else if ("知乎日报".equals(type)) {
                    newsPresenter.getMoreZhiHuList();
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if ("头条".equals(type)) {
                if (!touTiaoAdapter.isShowFooter()) {
                    if(touTiaoAdapter.getItemCount() != lastVisibleItem + 1) {
                        touTiaoAdapter.setIsShowFooter(true);
                        touTiaoAdapter.notifyDataSetChanged();
                    }
                }
            } else if ("cnBeta".equals(type)) {
                if (!cnBetaAdapter.isShowFooter()) {
                    if(cnBetaAdapter.getItemCount() != lastVisibleItem + 1) {
                        cnBetaAdapter.setIsShowFooter(true);
                        cnBetaAdapter.notifyDataSetChanged();
                    }
                }
            } else if ("知乎日报".equals(type)) {
                if (!zhiHuAdapter.isShowFooter()) {
                    if(zhiHuAdapter.getItemCount() != lastVisibleItem + 1) {
                        zhiHuAdapter.setIsShowFooter(true);
                        zhiHuAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
 }
