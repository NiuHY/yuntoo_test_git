package com.yt.webviewtest.net_volley_netroid.net_2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/11.
 *
 * 设置请求参数的工具类，返回Map集合
 */
public class RequestMapData {

    /**
     * @param offset
     * @param limit
     * @param key
     * @return 搜索的请求参数
     */
    public static Map<String, String> setSearchParams(String offset, String limit, String key){
        HashMap<String, String> paramsMap = new HashMap<String, String>();

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
