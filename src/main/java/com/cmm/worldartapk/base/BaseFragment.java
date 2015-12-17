package com.cmm.worldartapk.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.cmm.worldartapk.utils.ViewUtils;
import com.cmm.worldartapk.utils.WebViewUtils;

/**
 * Created by Administrator on 2015/12/8.
 */
public abstract class BaseFragment extends Fragment {
    //被缓存的View对象
    private View fragmentContentView;

    /**
     * 每个页面对应的WebView
     */
    private WebView webView;

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

    /**
     * 给子类重写，需要返回这个Fragment的内容布局
     * @return Fragment的内容布局
     */
//    protected abstract View initFragmentView();

    //初始化WebView
    private View initFragmentView(){
        //初始化webView
        webView = new WebView(getActivity());
        WebViewUtils.webViewBaseSet(webView);//webView设置初始化
        //通过子类重写，加载对应的url
        setUrl(webView);
        return webView;
    }

    /**
     * 子类通过重写这个方法设置不同的url
     * @param webView 当前页面的 webView
     */
    protected abstract void setUrl(WebView webView);

    /**
     * @return 给外界获取 webview
     */
    public WebView getWebView() {
        return webView;
    }
}
