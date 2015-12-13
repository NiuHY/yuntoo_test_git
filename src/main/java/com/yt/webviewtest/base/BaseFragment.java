package com.yt.webviewtest.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.yt.webviewtest.utils.ViewUtils;
import com.yt.webviewtest.utils.WebViewUtils;

/**
 * Created by Administrator on 2015/12/8.
 */
public abstract class BaseFragment extends Fragment {
    //被缓存的View对象
    private View fragmentContentView;

    /**
     * 子类中的XWalkView，在initView中初始化
     */
    protected WebView webView;

    // 初始化布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //缓存View对象
        if (fragmentContentView == null){
            fragmentContentView = initFragmentView();
        }else{
            //被ViewPager复用不能有父控件，移除
            ViewUtils.removeSelfFromParent(fragmentContentView);
        }

        return fragmentContentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //初始化WebView
        if (webView != null){

            WebViewUtils.webViewBaseSet(webView);

            //Android 调 JS  loadUrl("javascript:funFromjs()");  调JS 中的 funFromjs()方法

            //给Activity的返回键点击 注册webView
            ((BaseActivity) getActivity()).setWebView(webView);
        }

        // 初始化数据
        initFragmentData(savedInstanceState);

        super.onActivityCreated(savedInstanceState);
    }

    /**
     * //这里初始化数据
     * @param savedInstanceState Activity创建时候保存的信息
     */
    protected abstract void initFragmentData(Bundle savedInstanceState);

    /**
     * 给子类重写，需要返回这个Fragment的内容布局
     * @return Fragment的内容布局
     */
    protected abstract View initFragmentView();

}
