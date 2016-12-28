package com.news.gemens.newstest.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.news.gemens.newstest.R;
import com.news.gemens.newstest.bean.CnBetaNewsItem;
import com.news.gemens.newstest.bean.TouTiaoItem;
import com.news.gemens.newstest.bean.ZhiHuItem;

import java.util.List;

/**
 * Created by Gemens on 2016/12/26/0026.
 */

public class NewsAdapter<T> extends RecyclerView.Adapter<NewsAdapter.Holder> {

    private Context context;
    private List<T> newsList;
    private String type;

    public NewsAdapter(Context context, List<T> newsList, String type) {
        this.context = context;
        this.newsList = newsList;
        this.type = type;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        switch (type) {
            case "头条":
                setTouTiaoData(holder,position);
                break;
            case "cnBeta":
                setCnBetaData(holder, position);
                break;
            case "知乎日报":
                setZhiHuData(holder, position);
                break;
            case "果壳":
                break;
        }

    }

    private void setCnBetaData(Holder holder, int position) {
        holder.cnBetaTitle.setVisibility(View.VISIBLE);
        holder.cnBetaDesc.setVisibility(View.VISIBLE);
        holder.newsDate.setVisibility(View.VISIBLE);
        holder.zhihuTitle.setVisibility(View.GONE);

        CnBetaNewsItem cnBetaNewsItem = (CnBetaNewsItem) newsList.get(position);
        Glide.with(context).load(cnBetaNewsItem.getThumb()).into(holder.newsImag);
        holder.cnBetaTitle.setText(cnBetaNewsItem.getTitle());
        StringBuilder sb = new StringBuilder(Html.fromHtml(cnBetaNewsItem.getHometext().replaceAll("<.*?>|[\\r|\\n]", "")));
        holder.cnBetaDesc.setText(sb);
        holder.newsDate.setText(cnBetaNewsItem.getInputtime());
    }

    private void setZhiHuData(Holder holder, int position) {
        holder.cnBetaTitle.setVisibility(View.GONE);
        holder.cnBetaDesc.setVisibility(View.GONE);
        holder.newsDate.setVisibility(View.GONE);
        holder.zhihuTitle.setVisibility(View.VISIBLE);

        ZhiHuItem zhiHuItem = (ZhiHuItem) newsList.get(position);
        Glide.with(context).load(zhiHuItem.getImages().get(0)).into(holder.newsImag);
        holder.zhihuTitle.setText(zhiHuItem.getTitle());
    }

    private void setTouTiaoData(Holder holder,int position) {
        holder.cnBetaTitle.setVisibility(View.GONE);
        holder.cnBetaDesc.setVisibility(View.GONE);
        holder.newsDate.setVisibility(View.VISIBLE);
        holder.zhihuTitle.setVisibility(View.VISIBLE);

        TouTiaoItem touTiaoItem = (TouTiaoItem) newsList.get(position);
        Glide.with(context).load(touTiaoItem.getPicUrl()).centerCrop().into(holder.newsImag);
        holder.zhihuTitle.setText(touTiaoItem.getTitle());
        holder.newsDate.setText(touTiaoItem.getCtime());
    }

    static class Holder extends RecyclerView.ViewHolder {
        private ImageView newsImag;
        private TextView cnBetaTitle;
        private TextView cnBetaDesc;
        private TextView newsDate;
        private TextView zhihuTitle;

        public Holder(View view) {
            super(view);

            newsImag = (ImageView) view.findViewById(R.id.iv_news_img);
            cnBetaTitle = (TextView) view.findViewById(R.id.tv_cnBeta_title);
            cnBetaDesc = (TextView) view.findViewById(R.id.tv_cnBeta_desc);
            newsDate = (TextView) view.findViewById(R.id.tv_news_date);

            zhihuTitle = (TextView) view.findViewById(R.id.tv_zhihu_title);
        }
    }
}
