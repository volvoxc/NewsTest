package com.news.gemens.newstest.bean;

import java.util.List;

/**
 * Created by gemens on 2016/12/27.
 */

public class TouTiaoList {
    private int code;
    private String msg;
    private List<TouTiaoItem> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<TouTiaoItem> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<TouTiaoItem> newslist) {
        this.newslist = newslist;
    }
}
