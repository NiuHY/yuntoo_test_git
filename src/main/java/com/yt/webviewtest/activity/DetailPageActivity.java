package com.yt.webviewtest.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.yt.webviewtest.R;
import com.yt.webviewtest.base.BaseActivity;
import com.yt.webviewtest.net_volley_netroid.net_2.MyNetWorkObject;
import com.yt.webviewtest.net_volley_netroid.net_2.NetUtils;
import com.yt.webviewtest.net_volley_netroid.net_2.RequestMapData;
import com.yt.webviewtest.publicinfo.ConstInfo;
import com.yt.webviewtest.utils.UIUtils;
import com.yt.webviewtest.utils.WebViewUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by Administrator on 2015/12/11.
 */
public class DetailPageActivity extends BaseActivity {

    private View contentView;
    private Intent intent;
    private String loadUrl;
    private int loadCategory;
    private RelativeLayout shareLayout;
    private WindowManager windowManager;
    private View shareView;
    private boolean shaleFlag;

    @Override
    protected void init() {

        //得到 打开这个详情页的 Intent
        intent = getIntent();
        //这个详情页要加载的 url
        loadUrl = intent.getStringExtra("loadUrl");
        //这是哪一个板块的详情页
        loadCategory = intent.getIntExtra("loadCategory", ConstInfo.JINTAN);
    }

    @Override
    public void supportFinishAfterTransition() {
        super.supportFinishAfterTransition();
    }

    @Override
    protected View getContentView() {
        contentView = View.inflate(this, R.layout.detailpage_activity, null);
        return contentView;
    }

    @Override
    protected void initView() {

        //返回按钮
        ImageButton myBack = (ImageButton) findViewById(R.id.bt_back);
        //变背景
        switch (loadCategory){
            case 1:
                myBack.setBackgroundResource(R.drawable.icon_back_bg_yellow);
                break;
            case 2:
                myBack.setBackgroundResource(R.drawable.icon_back_bg_red);
                break;
            case 3:
                myBack.setBackgroundResource(R.drawable.icon_back_bg_blue);
                break;
            default:
                break;
        }
        //关闭
        myBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setExitSwichLayout();
//                overridePendingTransition(R.anim.setg_pre_in, R.anim.setg_pre_out);
            }
        });

        // webView
        webView = (WebView) findViewById(R.id.detailpage_webview);
        //给webView设置
        WebViewUtils.webViewBaseSet(webView);

        //加载url
        webView.loadUrl(loadUrl);
        //长按打开分享
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                UIUtils.showToastSafe("长按分享" + loadUrl);

                //添加一个window
                showShareWindow();

                return true;
            }
        });
    }

    private void showShareWindow() {
        windowManager = getWindowManager();

        shareView = View.inflate(this, R.layout.sharepage_activity, null);
        //设置布局，初始化6个分享按钮
        initShareButton();

        // 设置布局参数
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        //宽高填充
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        //全屏 要有WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，好像因为这个才能相应返回键？
        params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //透明窗体
        params.format = PixelFormat.TRANSPARENT;
        //普通应用程序窗口
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;

        //添加
        windowManager.addView(shareView, params);
        //设置打开标记
        shaleFlag = true;
    }

    // 初始化分享按钮
    private void initShareButton() {

        //六个分享按钮
        ImageButton share_qq = (ImageButton) shareView.findViewById(R.id.share_btn_qq); //qq
        ImageButton share_weibo = (ImageButton) shareView.findViewById(R.id.share_btn_weibo);//微博
        ImageButton share_wenxin = (ImageButton) shareView.findViewById(R.id.share_btn_wenxin);//微信
        ImageButton share_friend = (ImageButton) shareView.findViewById(R.id.share_btn_friend);//朋友圈

        ImageButton share_copyLink = (ImageButton) shareView.findViewById(R.id.share_btn_copyLink);//剪贴板
        ImageButton share_collect = (ImageButton) shareView.findViewById(R.id.share_btn_collect);//收藏

        //设置点击监听
        shareClick(share_qq);
        shareClick(share_weibo);
        shareClick(share_wenxin);
        shareClick(share_friend);
        shareClick(share_copyLink);
        shareClick(share_collect);

    }

    // 六个分享按钮的点击监听
    private void shareClick(ImageButton ib){
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    //四个分享
                    case R.id.share_btn_qq:
                        //点击调用QQ分享
                        shareMethod(QQ.NAME);
                        break;
                    case R.id.share_btn_weibo:
                        //新浪微博
                        shareMethod(SinaWeibo.NAME);
                        break;
                    case R.id.share_btn_wenxin:
                        //微信好友
                        shareMethod(Wechat.NAME);
                        break;
                    case R.id.share_btn_friend:
                        //微信朋友圈
                        shareMethod(WechatMoments.NAME);
                        break;

                    case R.id.share_btn_copyLink:
                        //获取剪贴板管理器，复制到剪贴板
                        ClipboardManager jtb = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                        //TODO
//                        ClipData clipData = new ClipData();
//                        jtb.setPrimaryClip(clipData);
                        jtb.setText(loadUrl);
                        UIUtils.showToastSafe(jtb.getText().toString().trim());
                        break;
                    case R.id.share_btn_collect:
                        //TODO 收藏 连接服务器
                        //把url传给服务器
                        NetUtils.pushStringByNet("http://www.baidu.com", RequestMapData.setCollectString(loadUrl), new MyNetWorkObject.SuccessListener() {
                            @Override
                            public void onSuccess(Object data) {
                                UIUtils.showToastSafe("请求成功"+ data);
                            }
                        });
                        break;
                    default:
                        break;
                }

                //点击后关闭分享
                if (shaleFlag){
                    windowManager.removeView(shareView);
                    shaleFlag = false;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (shaleFlag){
            windowManager.removeView(shareView);
            shaleFlag = false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && shaleFlag) {
            windowManager.removeView(shareView);
            shaleFlag = false;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




    //分享方法
    //QQ分享
    //新浪微博
    //微信好友
    //微信朋友圈
    private void shareMethod(String target){
        //公共方法
        Platform.ShareParams shareParams = new Platform.ShareParams();
        //分享的标题(链接)，文本，图片。。。
        shareParams.setTitle("分享标题");
        shareParams.setText("这里是分享的内容");
        shareParams.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        shareParams.setUrl("http://www.yuntoo.com");
        shareParams.setShareType(Platform.SHARE_WEBPAGE);

        //根据目标设置回调
        Platform platform = ShareSDK.getPlatform(target);
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                UIUtils.showToastSafe(hashMap.toString());
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                UIUtils.showToastSafe(throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                UIUtils.showToastSafe("取消"+i);
            }
        });

        //分享
        platform.share(shareParams);

    }

}
