package com.news.gemens.newstest.utils;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by Gemens on 2016/12/26/0026.
 */

public class Constant {
    public static String BASE_URL = "http://www.cnbeta.com";
    private static final String ARTICLE_URL = BASE_URL + "/articles/%s.htm";
    public static final Pattern ARTICLE_PATTERN = Pattern.compile("http://www\\.cnbeta\\.com/articles/(\\d+)\\.htm");
    public static final Pattern STANDRA_PATTERN = Pattern.compile("cnBeta\\.COM_中文业界资讯站");
    public static final Pattern SN_PATTERN = Pattern.compile("SN:\"(.{5})\"");

    public static String ZHIHU_LIST = "http://news-at.zhihu.com/api/4/news/latest";
    public static String ZHIHU_CONTENT = "http://daily.zhihu.com/story/";
    public static String ZHIHU_LIST_BEFORE = "http://news-at.zhihu.com/api/4/news/before/";

    public static String GUO_KE_LIST = "http://apis.guokr.com/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1";

    public static String TOU_TIAO_LIST = "https://api.tianapi.com/social/";
    public static String TOU_TIAO_KEY = "c051ba4df3000c69e142bbf0b03ffa76";
    public static int TOU_TIAO_PAGE_NUM = 10;

    public static String CNBETA_NEWS_LIST_TYPE = "all";

    public static String buildArticleUrl(String sid) {
        return String.format(Locale.CHINA, ARTICLE_URL, sid);
    }


}
