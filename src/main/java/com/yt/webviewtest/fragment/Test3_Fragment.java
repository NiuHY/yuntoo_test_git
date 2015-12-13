package com.yt.webviewtest.fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.yt.webviewtest.R;
import com.yt.webviewtest.base.BaseFragment;


/**
 * Created by Administrator on 2015/12/8.
 */
public class Test3_Fragment extends BaseFragment {
    private static final String TAG = "Test3_Fragment";

    @Override
    protected void initFragmentData(Bundle savedInstanceState) {
        // 测试加载
        webView.loadUrl("file:///android_asset/test.html");
    }


    @Override
    protected View initFragmentView() {
        View view =  View.inflate(getActivity(), R.layout.main_vp_fragment, null);
        webView = (WebView) view.findViewById(R.id.main_fm_webview);

        return view;
    }
}