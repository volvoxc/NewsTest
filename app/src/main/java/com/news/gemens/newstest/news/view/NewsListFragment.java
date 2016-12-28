package com.news.gemens.newstest.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class NewsListFragment extends Fragment implements NewsListFragmentView{

    private static final String TAG = "NewsListFragment";

    private RecyclerView recyclerView;
    private NewsAdapter cnBetaAdapter,zhiHuAdapter,touTiaoAdapter,guoKeAdapter;

    private List<CnBetaNewsItem> cnBetaNewsItems = new ArrayList<>();
    private List<ZhiHuItem> zhiHuItems = new ArrayList<>();
    private List<TouTiaoItem> touTiaoItems = new ArrayList<>();
    private List<GuoKeItem> guoKeItems = new ArrayList<>();

    private View view;
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
        String type = getArguments().getString("type");
        init(view,type);
    }

    private void init(View view,String type){
        recyclerView = (RecyclerView)view.findViewById(R.id.news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsPresenter = new NewsPresenter(this);
        switch (type) {
            case "头条":
                if (touTiaoAdapter == null) touTiaoAdapter = new NewsAdapter(getActivity(),touTiaoItems,"头条");
                recyclerView.setAdapter(touTiaoAdapter);
                newsPresenter.getTouTiaoList();
                break;
            case "cnBeta":
                if (cnBetaAdapter == null) cnBetaAdapter = new NewsAdapter(getActivity(),cnBetaNewsItems,"cnBeta");
                recyclerView.setAdapter(cnBetaAdapter);
                newsPresenter.refreshCnBetaNewsList();
                break;
            case "知乎日报":
                if (zhiHuAdapter == null) zhiHuAdapter = new NewsAdapter(getActivity(),zhiHuItems,"知乎日报");
                recyclerView.setAdapter(zhiHuAdapter);
                newsPresenter.getZhiHuList();
                break;
            case "果壳精选":
                if (guoKeAdapter == null) guoKeAdapter = new NewsAdapter(getActivity(),guoKeItems,"果壳精选");
                recyclerView.setAdapter(guoKeAdapter);
                newsPresenter.getGuoKeList();
                break;
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
}
