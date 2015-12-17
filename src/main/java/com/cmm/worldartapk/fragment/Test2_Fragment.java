package com.cmm.worldartapk.fragment;

import android.webkit.WebView;

import com.cmm.worldartapk.base.BaseFragment;


/**
 * Created by Administrator on 2015/12/8.
 */
public class Test2_Fragment extends BaseFragment {

    private static final String TAG = "Test2_Fragment";


    @Override
    protected void setUrl(WebView webView) {
        webView.loadUrl("http://192.168.0.160:8083/index2.html");
    }
}
