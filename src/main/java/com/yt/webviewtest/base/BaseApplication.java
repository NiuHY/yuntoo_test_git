package com.yt.webviewtest.base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.yt.webviewtest.net_volley_netroid.Netroid;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2015/11/10.
 */
public class BaseApplication extends Application {

    //上下文
    private static BaseApplication mContext;
    //主线程的Handler
    private static Handler mMainThreadHandler;
    //主线程
    private static Thread mMainThread;
    //主线程ID
    private static int mMainThreadId;
    //Looper
    private static Looper mLooper;

    @Override
    public void onCreate() {
        super.onCreate();

        // Netroid初始化
        Netroid.init(this);

        //初始化分享
        ShareSDK.initSDK(this);

        //给成员变量初始化
        this.mContext = this;
        this.mMainThreadHandler = new Handler();
        this.mMainThread = Thread.currentThread();
        //Returns the identifier of the calling thread, which be used with setThreadPriority(int, int).
        this.mMainThreadId = android.os.Process.myTid();
        this.mLooper = getMainLooper();
    }

    public static BaseApplication getApplication() {
        return mContext;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Looper getLooper() {
        return mLooper;
    }
}