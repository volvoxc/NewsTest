package com.news.gemens.newstest.bean;

/**
 * Created by Gemens on 2016/12/26/0026.
 */

public class CnBetaNewsList {

    private String state;
    private String message;
    private CnBetaResult result;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CnBetaResult getResult() {
        return result;
    }

    public void setResult(CnBetaResult result) {
        this.result = result;
    }
}
