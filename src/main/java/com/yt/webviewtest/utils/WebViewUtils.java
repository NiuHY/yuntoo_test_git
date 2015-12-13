package com.yt.webviewtest.utils;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yt.webviewtest.SafeWebViewBridge.InjectedChromeClient;
import com.yt.webviewtest.SafeWebViewBridge.JsCallJava;
import com.yt.webviewtest.SafeWebViewBridge.js.JsScope;

/**
 * Created by Administrator on 2015/12/11.
 * WebView 延展出的 工具类
 */
public class WebViewUtils {

    public static void webViewBaseSet(WebView webView) {
        WebSettings settings = webView.getSettings();
        //JS可用
        settings.setJavaScriptEnabled(true);
//        //背景透明
//        webView.setBackgroundColor(Color.alpha(0xff000000));

        //在WebView中加载后续页面
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }
        });

        //给webView 关联 需要JS调用的类
        webView.setWebChromeClient(new MyWebChromeClient("ADS", JsScope.class));
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
            // ...
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            // TODO  code
            // ...
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
    }
}
