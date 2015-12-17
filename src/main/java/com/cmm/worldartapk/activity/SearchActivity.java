package com.cmm.worldartapk.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.cmm.worldartapk.R;
import com.cmm.worldartapk.base.BaseActivity;
import com.cmm.worldartapk.bean.SearchBean;
import com.cmm.worldartapk.net_volley_netroid.Netroid;
import com.cmm.worldartapk.ui.SearchTabView;
import com.cmm.worldartapk.utils.UIUtils;
import com.duowan.mobile.netroid.image.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/16.
 */
public class SearchActivity extends BaseActivity {


    /**
     * 展览搜索
     */
    private final String EXHIBITION_URL = "/api/search/exhibition/";
    private SearchBean exhibitionData; // 展览搜索结果

    private FrameLayout frameLayout; // 搜索内容 容器
    private EditText editText; // 文本编辑框
    private TextView textView; // 搜索按钮

    //搜索按钮判断，true是搜索按钮
    private boolean isSearchButton = false;
    private View searchContentView;
    private List<TextView> pagerList;
    private List<String> imagePathList;

    @Override
    protected void init() {

    }

    @Override
    protected View getContentView() {
        View contentView = View.inflate(UIUtils.getContext(), R.layout.search_activity, null);

        return contentView;
    }

    @Override
    protected void initView() {
        //文本编辑框，搜索按钮，frameLayout
        editText = (EditText) findViewById(R.id.search_et);
        textView = (TextView) findViewById(R.id.search_tv);
        frameLayout = (FrameLayout) findViewById(R.id.search_fl);

        // 给文本编辑框设置变化监听， 当有内容改变时，设置搜索按钮显示为 “搜索”

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当内容改变后，启用搜索按钮
                if (!isSearchButton){
                    textView.setText("搜索");
                    isSearchButton = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //textView 搜索按钮的点击
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取textView 的文本内容，如果是搜索，就响应 搜索，如果是取消，就响应取消
                if (textView.getText().toString().trim().equals("取消")){
                    //取消，关闭搜索页
                    finish();
                }else {
                    //把搜索变成取消
                    textView.setText("取消");
                    // TODO 搜索，显示搜索内容
                    frameLayout.removeAllViews();

                    //放入搜索内容布局，一个tab一个ViewPager
                    searchContentView = View.inflate(UIUtils.getContext(), R.layout.search_content_item, null);

//                    //请求数据，获取搜索结果后填充搜索页
//                    NetUtils.getDataByNet(SearchActivity.this, Const.BASE_URL + EXHIBITION_URL + editText.getText().toString().trim(), RequestMapData.setSearchParams("1", "0", "sdf"), new SearchParser(), new MyNetWorkObject.SuccessListener() {
//                        @Override
//                        public void onSuccess(Object data) {
//                            exhibitionData = (SearchBean) data;
//
//
//                        }
//                    });

                    initSearchContentView();
                    frameLayout.addView(searchContentView);
                }
            }
        });
    }

    // 初始化搜索内容页
    private void initSearchContentView() {
        //tab  ViewPager
        final SearchTabView searchTabView = (SearchTabView) searchContentView.findViewById(R.id.search_content_searchTab);
        final ViewPager viewPager = (ViewPager) searchContentView.findViewById(R.id.search_content_vp);

        searchTabView.setOnItemClicklistener(new SearchTabView.OnItemClicklistener() {
            @Override
            public void onClick(View view, int index) {
                viewPager.setCurrentItem(index);//选择页面
            }
        });

        pagerList = searchTabView.getDataList();

        viewPager.setAdapter(new MyVPAdapter());
        //设置选择页面监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                searchTabView.setCurrentSelectedItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //c 测试图片路径集合
        imagePathList = new ArrayList<String>();
        for (int i = 0; i <= 12; i++) {
            imagePathList.add("assets://test/img" + i + ".jpg");
        }
    }

    // ViewPager Adapter
    private class MyVPAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return pagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {

            GridView gridView = new GridView(UIUtils.getContext());
            gridView.setNumColumns(2);

            gridView.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return imagePathList.size();
                }

                @Override
                public Object getItem(int position) {

                    return imagePathList.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null){
                        convertView = View.inflate(UIUtils.getContext(), R.layout.item_gridview_searchcontent, null);
                    }

                    NetworkImageView networkImageView = (NetworkImageView) convertView.findViewById(R.id.search_content_networkimageview);


//                    int padding = UIUtils.dip2Px(3);
//                    convertView.setPadding(padding, padding, padding, padding);
                    Netroid.displayImage(imagePathList.get(position), networkImageView);
                    return convertView;
                }
            });

            container.addView(gridView);

            return gridView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
