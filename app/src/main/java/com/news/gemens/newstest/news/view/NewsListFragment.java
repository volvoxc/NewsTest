package com.news.gemens.newstest.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.gemens.newstest.R;
import com.news.gemens.newstest.news.adapter.NewsAdapter;

/**
 * Created by Gemens on 2016/12/26/0026.
 */

public class NewsListFragment extends Fragment{

    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cnbeta_list,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();

        Bundle bundle = getArguments();
        Log.d("TAG", "onActivityCreated: " + bundle.get("type"));
    }

    private void init() {
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerview_cnbeta);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new NewsAdapter(getActivity()));
    }
}
