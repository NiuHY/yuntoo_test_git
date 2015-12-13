package com.yt.webviewtest.net_volley_netroid;

import com.duowan.mobile.netroid.RequestQueue;
import com.duowan.mobile.netroid.request.ImageRequest;
import com.duowan.mobile.netroid.toolbox.ImageLoader;

import java.util.concurrent.TimeUnit;

/**
 * 指定图片缓存在硬盘的时间
 * Created by Administrator on 2015/12/10.
 */
public class SelfImageLoder extends ImageLoader {
    /**
     * Constructs a new ImageLoader.
     *
     * @param queue      The RequestQueue to use for making image requests.
     * @param imageCache The cache to use as an L1 cache.
     */
    public SelfImageLoder(RequestQueue queue, ImageCache imageCache) {
        super(queue, imageCache);
    }

    @Override
    public ImageRequest buildRequest(String requestUrl, int maxWidth, int maxHeight) {
        //指定图片缓存在硬盘的时间
        ImageRequest imageRequest = new ImageRequest(requestUrl, maxWidth, maxHeight);
        imageRequest.setCacheExpireTime(TimeUnit.MINUTES, 15);
        return imageRequest;
    }
}
