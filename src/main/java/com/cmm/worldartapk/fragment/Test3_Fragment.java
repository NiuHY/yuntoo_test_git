package com.cmm.worldartapk.fragment;

import android.webkit.WebView;

import com.cmm.worldartapk.base.BaseFragment;


/**
 * Created by Administrator on 2015/12/8.
 */
public class Test3_Fragment extends BaseFragment {
    private static final String TAG = "Test3_Fragment";


    @Override
    protected void setUrl(WebView webView) {
        webView.loadUrl("file:///android_asset/test.html");
    }
}
