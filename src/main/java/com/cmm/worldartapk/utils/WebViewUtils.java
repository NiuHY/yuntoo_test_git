package com.cmm.worldartapk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cmm.worldartapk.SafeWebViewBridge.InjectedChromeClient;
import com.cmm.worldartapk.SafeWebViewBridge.JsCallJava;
import com.cmm.worldartapk.SafeWebViewBridge.js.JsScope;


/**
 * Created by Administrator on 2015/12/11.
 * WebView 延展出的 工具类
 */
public class WebViewUtils {

    private static Context context;

    private static WebSettings settings;

    public static void webViewBaseSet(final WebView webView) {

//        webView.reload();

        context = webView.getContext();

        settings = webView.getSettings();

        //图片不自动加载，在网页加载完成后才加载
        if(Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        //JS可用
        settings.setJavaScriptEnabled(true);
        //给webView 关联 需要JS调用的类
        webView.setWebChromeClient(new MyWebChromeClient("ADS", JsScope.class));
        //可以通过触摸获取焦点
        webView.requestFocusFromTouch();
        //页面大小自适应
        settings.setLoadWithOverviewMode(true);
//        //背景透明
//        webView.setBackgroundColor(Color.alpha(0xff000000));

        //在WebView中加载后续页面
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //加载完成开启图片自动加载
                if (!settings.getLoadsImagesAutomatically()) {
                    settings.setLoadsImagesAutomatically(true);
                }

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            //自定义出错界面
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

                //显示加载错误页面，(重新加载按钮)
            }
        });


    }

    // 自定义 WebChromeClient
    public static class MyWebChromeClient extends InjectedChromeClient {

        public MyWebChromeClient(String injectedName, Class injectedCls) {
            super(injectedName, injectedCls);
        }

        public MyWebChromeClient(JsCallJava jsCallJava) {
            super(jsCallJava);
        }


        // ============= 重写的三个方法
        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            // TODO  code
            // ...
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public void onProgressChanged (WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // TODO  code
            // 当进度条开始走，显示正在加载，进度条走完，才显示webView
            UIUtils.showToastSafe("当前加载进度" + newProgress);

        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            // TODO  code
            // ...
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
    }
}
