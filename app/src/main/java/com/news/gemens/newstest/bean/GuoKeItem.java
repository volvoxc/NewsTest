package com.news.gemens.newstest.bean;

/**
 * Created by Gemens on 2016/12/28/0028.
 */

public class GuoKeItem {
    private int id;
    private String title;
    private String headline_img;
    private String summary;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadline_img() {
        return headline_img;
    }

    public void setHeadline_img(String headline_img) {
        this.headline_img = headline_img;
    }
}
