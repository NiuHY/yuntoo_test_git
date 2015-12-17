package com.cmm.worldartapk.net_volley_netroid.net_2;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/11.
 *
 * 设置请求参数的工具类，返回Map集合
 */
public class RequestMapData {


    /**
     * 搜索请求参数
     * @param offset 如果-1不加
     * @param limit 如果-1不加
     * @param key 搜索内容
     * @param client_type 客户端类型 Android 5
     * @param uuid 设备id
     * @param client_version 客户端版本
     * @param session_key 登陆用户session
     * @return 搜索Map
     */
    public static Map<String, String> setSearchParams(String offset, String limit, String key, String client_type, String uuid, String client_version, String session_key){
        HashMap<String, String> paramsMap = new HashMap<String, String>();

        if (!TextUtils.equals(offset, "-1")){
            paramsMap.put("offset", offset);
        }
        paramsMap.put("offset", offset);
        paramsMap.put("limit", limit);
        paramsMap.put("key", key);

        return paramsMap;
    }

    public static Map<String, String> setCollectString(String loadUrl) {

        HashMap<String, String> paramsMap = new HashMap<String, String>();

        paramsMap.put("collectUrl", loadUrl);

        return paramsMap;
    }
}
