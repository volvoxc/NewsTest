package com.news.gemens.newstest.bean;

import java.util.List;

/**
 * Created by gemens on 2016/12/27.
 */

public class ZhiHuItem {
    private List<String> images;
    private long id;
    private String title;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
