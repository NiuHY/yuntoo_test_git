package com.yt.webviewtest.bean.parser;

import com.google.gson.Gson;
import com.yt.webviewtest.bean.SearchTest;
import com.yt.webviewtest.net_volley_netroid.net_2.BaseParser;

/**
 * Created by Administrator on 2015/12/11.
 */
public class SearchTestParser extends BaseParser<SearchTest> {
    @Override
    public SearchTest parserJson(String json) {
        //解析JavaBean
        Gson gson = new Gson();
        SearchTest searchTest = gson.fromJson(json, SearchTest.class);
        return searchTest;
    }
}
