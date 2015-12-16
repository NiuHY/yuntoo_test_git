package com.yt.webviewtest.fragment;

import android.webkit.WebView;

import com.yt.webviewtest.base.BaseFragment;

/**
 * Created by Administrator on 2015/12/8.
 */
public class Test1_Fragment extends BaseFragment {
    private static final String TAG = "Test1_Fragment";


    @Override
    protected void setUrl(WebView webView) {
//        final GifImageView gifImageView = new GifImageView(UIUtils.getContext());
//        gifImageView.setImageResource(R.drawable.gif1);
////        Netroid.displayImage("assets://test/gif1.gif", gifImageView);
////        NetUtils.getByteByNet("http://ww1.sinaimg.cn/bmiddle/c1a3c815gw1eyigj55457g208c04ob2b.gif", new Listener<byte[]>() {
////            @Override
////            public void onSuccess(byte[] response) {
////                try {
////                    gifImageView.setImageDrawable(new GifDrawable(response));
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        });
//        ImageView imageView = new ImageView(UIUtils.getContext());
//        Netroid.displayImage("", imageView);
//        webView.loadUrl("http://ww1.sinaimg.cn/bmiddle/c1a3c815gw1eyigj55457g208c04ob2b.gif");
        webView.loadUrl("file:///android_asset/test.html");
    }
}
