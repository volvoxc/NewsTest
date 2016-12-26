package com.news.gemens.newstest.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.gemens.newstest.R;

/**
 * Created by Gemens on 2016/12/26/0026.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {

    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cnbeta_list_item,parent,false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder (View view) {
            super(view);
        }
    }
}
