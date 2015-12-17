package com.cmm.worldartapk.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;

import com.cmm.worldartapk.R;
import com.cmm.worldartapk.activity.MainActivity;
import com.cmm.worldartapk.utils.UIUtils;


/**
 * Activity 基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    // 静态的 可以得到前台Activity
    private static Activity mForegroundActivity;
    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //初始化数据
        init();
        //设置内容页布局
        contentView = getContentView();
        setContentView(contentView);

        //进入动画
        setEnterSwichLayout();

        //初始化View
        initView();
    }

    /**
     * 初始化数据
     */
    protected abstract void init();

    /**
     * @return 返回Activity的内容View
     */
    protected abstract View getContentView();

    /**
     * 初始化界面
     */
    protected abstract void initView();

    @Override
    protected void onResume() {
        super.onResume();
        this.mForegroundActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mForegroundActivity = null;
    }


    /**
     * @return 获取前台Activity
     */
    public static Activity getForegroundActivity() {
        return mForegroundActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 接收WebView对象
    protected WebView webView;

    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    // 初始化双击时间
    long currentTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // WebView返回上一页
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView != null && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

        //如果当前Activity是 MainActivity双击返回键退出
        else if (keyCode == KeyEvent.KEYCODE_BACK && this instanceof MainActivity) {
            //双击
            if (currentTime < (SystemClock.currentThreadTimeMillis() - 250L)) {
                //两次点击间隔小于500毫秒，Toast
                UIUtils.showToastSafe("再次点击返回键退出");

                currentTime = SystemClock.currentThreadTimeMillis();
            } else {
                //在500毫秒内再次点击了返回键，退出
                currentTime = 0;
                //TODO 退出
                setExitSwichLayout();
            }
            return true;
        }else if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            setExitSwichLayout();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //进入动画
    public void setEnterSwichLayout() {
        overridePendingTransition(R.anim.setg_next_in, R.anim.setg_next_out);
    }
    //退出动画
    public void setExitSwichLayout() {
        finish();
        overridePendingTransition(R.anim.setg_pre_in, R.anim.setg_pre_out);
    }
}
