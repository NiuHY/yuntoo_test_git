package com.yt.webviewtest.net_volley_netroid.net_2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.duowan.mobile.netroid.Listener;
import com.duowan.mobile.netroid.request.StringRequest;
import com.yt.webviewtest.net_volley_netroid.Netroid;
import com.yt.webviewtest.utils.UIUtils;

import java.util.Map;

/**
 * Created by Administrator on 2015/12/10.
 */
public class NetUtils {


    /**
     * 根据传入的参数进行网络请求，如果有返回数据，在回调方法中接受
     */
    public static void getDataByNet(Context context, String url, Map<String, String> requestDataMap, BaseParser<?> jsonParser, MyNetWorkObject.SuccessListener successListener){
        //TODO  封参数
        // 创建 网络请求对象，请求JSON数据
        MyNetWorkObject myNetWorkObject = new MyNetWorkObject(context, url, requestDataMap, jsonParser, successListener);
        myNetWorkObject.getDataByNet();
    }

    /**
     * 请求网络，返回字符串
     */
    public static void getStringByNet(String url, Listener<String> listener){
        StringRequest stringRequest = new StringRequest(url, listener);
        Netroid.getmRequestQueue().add(stringRequest);
    }

    /**
     * 给服务器传一个字符串
     * @param url 地址
     * @param requestDataMap 请求参数(字符串)
     * @param successListener 成功回调
     */
    public static void pushStringByNet(String url, Map<String, String> requestDataMap, MyNetWorkObject.SuccessListener successListener){
        MyNetWorkObject myNetWorkObject = new MyNetWorkObject(null, url, requestDataMap, null, successListener);
        myNetWorkObject.pushString();
    }

    /**
     * wifi是否连接
     * @return wifi是否连接
     */
    public static boolean isWifiConnected() {
        Context context = UIUtils.getContext();
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 是否能上网
     * @return  是否能上网
     */
    public static boolean hasConnectedNetwork() {
        Context context = UIUtils.getContext();
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
