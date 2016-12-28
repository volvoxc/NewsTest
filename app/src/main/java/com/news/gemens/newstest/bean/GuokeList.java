package com.news.gemens.newstest.bean;

import java.util.List;

/**
 * Created by Gemens on 2016/12/28/0028.
 */

public class GuokeList {
    private String now;
    private boolean ok;
    private List<GuoKeItem> result;

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<GuoKeItem> getResult() {
        return result;
    }

    public void setResult(List<GuoKeItem> result) {
        this.result = result;
    }
}
