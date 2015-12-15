package com.yt.webviewtest.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yt.webviewtest.R;
import com.yt.webviewtest.base.BaseActivity;
import com.yt.webviewtest.fragment.FragmentFactory;
import com.yt.webviewtest.utils.UIUtils;


public class MainActivity extends BaseActivity {

    //三页
    private static String[] pagerName = {"pager1", "pager2", "pager3"};


    private View contentView;
    private ViewPager viewPager;

    /**
     * @return 返回内容页View 如果有XWalkView就通过父类的方法设置
     */
    @Override
    protected View getContentView() {
        //返回内容View，初始化XWalkView
        contentView = View.inflate(UIUtils.getContext(), R.layout.activity_main, null);
        // 如果有XWalkView 要在这里初始化
//        xWalkView = contentView.findViewById(R.id.xwalkview_main);
        return contentView;
    }

    /**
     * setContentView之后
     * 这里findViewById等
     */
    @Override
    protected void initView() {

        // ViewPager
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        //三个页面用 Fragment填充(容易控制)
        // 设置适配器，默认显示中间页
        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));

        //设置页面切换监听，关联webView
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //给Activity的当前的Fragment 注册webView
                setWebView(FragmentFactory.createFragment(position).getWebView());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /**
     * setContentView之前
     * 先各种初始化，后初始化界面
     */
    @Override
    protected void init() {

    }

    //ViewPager的 Fragment适配器
    private class MyFragmentAdapter extends FragmentPagerAdapter{

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 通过工厂类创建Fragment
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return pagerName.length;
        }
    }
}
