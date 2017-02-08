package com.news.gemens.newstest.bean;

/**
 * Created by Gemens on 2016/12/27/0027.
 */

public class CnBetaNewsItem {
    private String title;
    private String hometext;
    private String thumb;
    private String url_show;
    private String inputtime;
    private String from;
    private String content;
    private String SN;
    private int sid;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public CnBetaNewsItem(int sid, String title) {
        this.sid = sid;
        this.title = title;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getInputtime() {
        return inputtime;
    }

    public void setInputtime(String inputtime) {
        this.inputtime = inputtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHometext() {
        return hometext;
    }

    public void setHometext(String hometext) {
        this.hometext = hometext;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUrl_show() {
        return url_show;
    }

    public void setUrl_show(String url_show) {
        this.url_show = url_show;
    }
}
