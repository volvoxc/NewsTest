package com.news.gemens.newstest.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.news.gemens.newstest.R;
import com.news.gemens.newstest.bean.CnBetaNewsItem;
import com.news.gemens.newstest.bean.GuoKeItem;
import com.news.gemens.newstest.bean.TouTiaoItem;
import com.news.gemens.newstest.bean.ZhiHuItem;

import java.util.List;

/**
 * Created by Gemens on 2016/12/26/0026.
 */

public class NewsAdapter<T> extends RecyclerView.Adapter {

    private Context context;
    private List<T> newsList;
    private String type;

    private boolean isShowFooter = false;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String type,int position);
    }


    public NewsAdapter(Context context, List<T> newsList, String type,OnItemClickListener listener) {
        this.context = context;
        this.newsList = newsList;
        this.type = type;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false);
            return new Holder(view,listener,type);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_footer, parent, false);
            return new FooterViewHolder(view);
        }
    }


    @Override
    public int getItemCount() {
        int begin = isShowFooter ? 1 : 0;
        if (newsList == null) {
            return begin;
        }
        return newsList.size() + begin;
    }

    @Override
    public int getItemViewType(int position) {
        if (!isShowFooter) {
            return TYPE_ITEM;
        } else {
            if (position + 1 == getItemCount()) {
                return TYPE_FOOTER;
            } else {
                return TYPE_ITEM;
            }
        }
    }

    public void setIsShowFooter(boolean isShowFooter) {
        this.isShowFooter = isShowFooter;
    }

    public boolean isShowFooter() {
        return isShowFooter;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (type) {
            case "头条":
                if (holder instanceof NewsAdapter.Holder)
                    setTouTiaoData(holder,position);
                break;
            case "cnBeta":
                if (holder instanceof NewsAdapter.Holder)
                    setCnBetaData(holder, position);
                break;
            case "知乎日报":
                if (holder instanceof NewsAdapter.Holder)
                    setZhiHuData(holder, position);
                break;
            case "果壳精选":
                setGuoKeData(holder,position);
                break;
        }

    }

    private void setCnBetaData(RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).cnBetaTitle.setVisibility(View.VISIBLE);
        ((Holder)holder).cnBetaDesc.setVisibility(View.VISIBLE);
        ((Holder)holder).newsDate.setVisibility(View.VISIBLE);
        ((Holder)holder).zhihuTitle.setVisibility(View.GONE);

        CnBetaNewsItem cnBetaNewsItem = (CnBetaNewsItem) newsList.get(position);
        Glide.with(context).load(cnBetaNewsItem.getThumb()).into(((Holder)holder).newsImag);
        ((Holder)holder).cnBetaTitle.setText(cnBetaNewsItem.getTitle());
        StringBuilder sb = new StringBuilder(Html.fromHtml(cnBetaNewsItem.getHometext().replaceAll("<.*?>|[\\r|\\n]", "")));
        ((Holder)holder).cnBetaDesc.setText("        " + sb);
        ((Holder)holder).newsDate.setText(cnBetaNewsItem.getInputtime());
    }

    private void setZhiHuData(RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).cnBetaTitle.setVisibility(View.GONE);
        ((Holder)holder).cnBetaDesc.setVisibility(View.GONE);
        ((Holder)holder).newsDate.setVisibility(View.GONE);
        ((Holder)holder).zhihuTitle.setVisibility(View.VISIBLE);

        ZhiHuItem zhiHuItem = (ZhiHuItem) newsList.get(position);
        Glide.with(context).load(zhiHuItem.getImages().get(0)).into(((Holder)holder).newsImag);
        ((Holder)holder).zhihuTitle.setText(zhiHuItem.getTitle());
    }

    private void setTouTiaoData(RecyclerView.ViewHolder holder,int position) {
        ((Holder)holder).cnBetaTitle.setVisibility(View.GONE);
        ((Holder)holder).cnBetaDesc.setVisibility(View.GONE);
        ((Holder)holder).newsDate.setVisibility(View.VISIBLE);
        ((Holder)holder).zhihuTitle.setVisibility(View.VISIBLE);

        TouTiaoItem touTiaoItem = (TouTiaoItem) newsList.get(position);
        Glide.with(context).load(touTiaoItem.getPicUrl()).centerCrop().into(((Holder)holder).newsImag);
        ((Holder)holder).zhihuTitle.setText(touTiaoItem.getTitle());
        ((Holder)holder).newsDate.setText(touTiaoItem.getCtime());
    }

    private void setGuoKeData(RecyclerView.ViewHolder holder,int position) {
        ((Holder)holder).cnBetaTitle.setVisibility(View.VISIBLE);
        ((Holder)holder).cnBetaDesc.setVisibility(View.VISIBLE);
        ((Holder)holder).newsDate.setVisibility(View.GONE);
        ((Holder)holder).zhihuTitle.setVisibility(View.GONE);

        GuoKeItem guoKeItem = (GuoKeItem) newsList.get(position);
        Glide.with(context).load(guoKeItem.getHeadline_img()).centerCrop().into(((Holder)holder).newsImag);
        ((Holder)holder).cnBetaTitle.setText(guoKeItem.getTitle());
        ((Holder)holder).cnBetaDesc.setText("        " + guoKeItem.getSummary());
    }

     static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView newsImag;
        private TextView cnBetaTitle;
        private TextView cnBetaDesc;
        private TextView newsDate;
        private TextView zhihuTitle;
        private RelativeLayout rl;
        private OnItemClickListener itemListener;
         private String itemType;

        public Holder(View view,OnItemClickListener itemListener,String type) {
            super(view);
            itemType = type;
            this.itemListener = itemListener;
            rl = (RelativeLayout) view.findViewById(R.id.rl_item);
            rl.setOnClickListener(this);
            newsImag = (ImageView) view.findViewById(R.id.iv_news_img);
            cnBetaTitle = (TextView) view.findViewById(R.id.tv_cnBeta_title);
            cnBetaDesc = (TextView) view.findViewById(R.id.tv_cnBeta_desc);
            newsDate = (TextView) view.findViewById(R.id.tv_news_date);

            zhihuTitle = (TextView) view.findViewById(R.id.tv_zhihu_title);
        }

        @Override
        public void onClick(View v) {
            if (itemListener != null) {
                itemListener.onItemClick(itemType,getLayoutPosition());
            }
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }
    }

}
