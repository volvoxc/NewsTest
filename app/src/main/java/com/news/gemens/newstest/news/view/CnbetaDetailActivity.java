package com.news.gemens.newstest.news.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.news.gemens.newstest.R;
import com.news.gemens.newstest.bean.CnBetaNewsItem;
import com.news.gemens.newstest.service.ApiService;
import com.news.gemens.newstest.utils.Constant;
import com.news.gemens.newstest.utils.NetWorkUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Gemens on 2017/2/8/0008.
 */

public class CnbetaDetailActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings settings;
    private Handler handler;
    private VideoWebChromeClient client = new VideoWebChromeClient();
    private String title;
    private int sid;
    private CnBetaNewsItem cnBetaNewsItem;
    private static final String TAG = "CnbetaDetailActivity";

    private String webTemplate = "<!DOCTYPE html><html><head><title></title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>" +
            "<link  rel=\"stylesheet\" href=\"file:///android_asset/style.css\" type=\"text/css\"/><style>.title{color: #%s;}%s</style>" +
            "<script>var config = {\"enableImage\":%s,\"enableFlashToHtml5\":%s};</script>" +
            "<script src=\"file:///android_asset/BaseTool.js\"></script>" +
            "<script src=\"file:///android_asset/ImageTool.js\"></script>" +
            "<script src=\"file:///android_asset/VideoTool.js\"></script></head>" +
            "<body><div><div class=\"title\">%s</div><div class=\"from\">%s<span style=\"float: right\">%s</span></div><div id=\"introduce\">%s<div class=\"clear\"></div></div><div id=\"content\">%s</div><div class=\"clear foot\">-- The End --</div></div>" +
            "<script src=\"file:///android_asset/loder.js\"></script></body></html>";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cnbeta_detail);
        handler = new Handler();

        Intent intent = getIntent();
        if (null != intent && intent.hasExtra("sid") && intent.hasExtra("title")) {
            sid = intent.getIntExtra("sid",0);
            title = intent.getStringExtra("title");
            cnBetaNewsItem = new CnBetaNewsItem(sid,title);
        }

        initWebview();

        loadData();

    }

    private void loadData() {

        Retrofit retrofit =
                new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).build();

        Call<String> cnBetaNewsContent =
                retrofit.create(ApiService.class).getCnBetaNewsContent(Constant.buildArticleUrl(sid + ""));
        cnBetaNewsContent.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                loadSucceed(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void loadSucceed(String response) {
        if (Constant.STANDRA_PATTERN.matcher(response).find()) {
            new AsyncTask<String, String, Boolean>() {
                @Override
                protected Boolean doInBackground(String... strings) {
                    boolean hascontent = handleResponceString(cnBetaNewsItem, strings[0], false,false);
                    return hascontent;
                }

                @Override
                protected void onPostExecute(Boolean hascontent) {
                    if (hascontent) {
                        bindData();
                    } else {
                        Toast.makeText(CnbetaDetailActivity.this,"哎呦，什么问题啊...",Toast.LENGTH_SHORT).show();
                    }
                }
            }.execute(response);
        } else {
            Toast.makeText(CnbetaDetailActivity.this,"没有内容吗...",Toast.LENGTH_SHORT).show();
        }
    }

    private void bindData() {
        String colorString = Integer.toHexString(getResources().getColor(R.color.colorPrimary));

        setTitle(cnBetaNewsItem.getTitle());
        String data = String.format(Locale.CHINA, webTemplate, colorString.substring(2, colorString.length()),
                "light", false, false, cnBetaNewsItem.getTitle(), cnBetaNewsItem.getFrom(), cnBetaNewsItem.getInputtime(),
                cnBetaNewsItem.getHometext(), cnBetaNewsItem.getContent());
        webView.loadDataWithBaseURL(Constant.BASE_URL, data, "text/html", "utf-8", null);
    }

    public static boolean handleResponceString(CnBetaNewsItem item, String resp, boolean shouldCache, boolean cacheImage){
        Document doc = Jsoup.parse(resp);
        Elements newsHeadlines = doc.select(".body");
        item.setTitle(newsHeadlines.select("#news_title").html().replaceAll("<.*?>", ""));
        item.setFrom(newsHeadlines.select(".where").html());
        item.setInputtime(newsHeadlines.select(".date").html());
        Elements introduce = newsHeadlines.select(".introduction");
        introduce.select("div").remove();
        item.setHometext(introduce.html());
        Elements content = newsHeadlines.select(".content");
        content.select(".tigerstock").remove();
        Elements scripts = content.select("script");
        for (int i=0;i<scripts.size();i++){
            Element script = scripts.get(i);
            Element SiblingScript = script.nextElementSibling();
            String _script;
            if(SiblingScript!=null&&SiblingScript.tag()== org.jsoup.parser.Tag.valueOf("script")){
                i++;
                _script = script.toString().replaceAll(",?\"?(width|height)\"?:?\"(.*)?\"","");
                _script += SiblingScript.toString();
                _script = _script.replaceAll("\"|'","'");
                SiblingScript.remove();
            }else{
                _script = script.toString().replaceAll(",?\"(width|height)\":\"\\d+\"","").replaceAll("\"|'","'");
            }
            Element element = new Element(org.jsoup.parser.Tag.valueOf("iframe"),"");
            element.attr("contentScript",_script);
            element.attr("ignoreHolder","true");
            element.attr("style","width:100%");
            element.attr("allowfullscreen ","true");
            element.attr("onload","VideoTool.onloadIframeVideo(this)");
            script.replaceWith(element);
        }

        item.setContent(content.html());
        Matcher snMatcher = Constant.SN_PATTERN.matcher(resp);
        if (snMatcher.find())
            item.setSN(snMatcher.group(1));
        if(item.getContent()!=null&&item.getContent().length()>0){
            return true;
        }else{
            return false;
        }
    }

    private void initWebview() {
        webView = (WebView) findViewById(R.id.cnBeta_detail_webview);
        settings = webView.getSettings();
        settings.setSupportZoom(false);
        settings.setAllowFileAccess(true);
        settings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        if (NetWorkUtil.isWifiConnected()) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        webView.addJavascriptInterface(new JavaScriptInterface(this), "Interface");
        webView.setWebChromeClient(client);
        webView.setWebViewClient(new MyWebViewClient());

    }


    private class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void showImage(String pos, final String[] imageSrcs) {
            final int posi;
            try {
                posi = Integer.parseInt(pos);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        Intent intent = new Intent(mContext, ImageViewActivity.class);
//                        intent.putExtra(ImageViewActivity.IMAGE_URLS, imageSrcs);
//                        intent.putExtra(ImageViewActivity.CURRENT_POS, posi);
//                        mContext.startActivity(intent);
                    }
                });
            } catch (Exception e) {
                Log.d(getClass().getName(), "Illegal argument");
            }
        }
    }

    class VideoWebChromeClient extends WebChromeClient {
        private View myView = null;
        CustomViewCallback myCallback = null;
        @Override
        public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        }

        @Override
        public void onHideCustomView() {
        }
    }

    class MyWebViewClient extends WebViewClient {
        private static final String TAG = "WebView ImageLoader";
        private boolean finish = false;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            finish = false;
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            try {
                Intent intent;
                Matcher sidMatcher = Constant.ARTICLE_PATTERN.matcher(url);
                Log.d(TAG, "sidMatcher.find() = : " + sidMatcher.find());
                if (sidMatcher.find()) {
                } else {
                    Toast.makeText(CnbetaDetailActivity.this,"唉，什么情况啊",Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ignored) {

            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            System.out.println("MyWebViewClient.onPageFinished");
            super.onPageFinished(view, url);
            finish = true;
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            System.out.println("MyWebViewClient.shouldInterceptRequest(view,url) url = [" + url + "]");
            String prefix = MimeTypeMap.getFileExtensionFromUrl(url);
            if (!TextUtils.isEmpty(prefix)) {
                String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(prefix);
                if (mimeType != null && mimeType.startsWith("image")) {
                        System.out.println("Load Image Hoder");
                        try {
                            return new WebResourceResponse("image/svg+xml", "UTF-8", CnbetaDetailActivity.this.getAssets().open("image.svg"));
                        } catch (IOException ignored) {
                        }
                    }
                } else {
                    System.out.println("load other resourse");
                }
        
            return super.shouldInterceptRequest(view, url);
        }
    }




}
