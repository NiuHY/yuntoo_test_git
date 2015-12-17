package com.cmm.worldartapk.bean.parser;

import com.cmm.worldartapk.bean.SearchBean;
import com.cmm.worldartapk.net_volley_netroid.net_2.BaseParser;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2015/12/11.
 */
public class SearchParser extends BaseParser<SearchBean> {
    @Override
    public SearchBean parserJson(String json) {
        //解析JavaBean
        Gson gson = new Gson();
        SearchBean SearchBean = gson.fromJson(json, SearchBean.class);
        return SearchBean;
    }
}
