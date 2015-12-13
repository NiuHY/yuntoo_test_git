package com.yt.webviewtest.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.duowan.mobile.netroid.image.NetworkImageView;
import com.yt.webviewtest.R;
import com.yt.webviewtest.base.BaseActivity;
import com.yt.webviewtest.bean.SearchTest;
import com.yt.webviewtest.bean.parser.SearchTestParser;
import com.yt.webviewtest.net_volley_netroid.Const;
import com.yt.webviewtest.net_volley_netroid.Netroid;
import com.yt.webviewtest.net_volley_netroid.net_2.MyNetWorkObject;
import com.yt.webviewtest.net_volley_netroid.net_2.NetUtils;
import com.yt.webviewtest.net_volley_netroid.net_2.RequestMapData;
import com.yt.webviewtest.utils.UIUtils;

/**
 * Created by Administrator on 2015/12/10.
 */
public class SearchActivity extends BaseActivity {

    private GridView gridView;
    private SearchTest searchTest;

    @Override
    protected View getContentView() {
        View contentView = View.inflate(UIUtils.getContext(), R.layout.test_layout, null);

        return contentView;
    }

    @Override
    protected void initView() {

        final TextView textView = (TextView) findViewById(R.id.test_tv);
        gridView = (GridView) findViewById(R.id.test_gv);


        // 请求JSON数据
        NetUtils.getDataByNet(this, Const.BASE_URL, RequestMapData.setSearchParams("0", "1", "xx"), new SearchTestParser(), new MyNetWorkObject.SuccessListener() {
            @Override
            public void onSuccess(Object data) {
                searchTest = ((SearchTest) data);
                // 请求成功加载数据
                gridView.setAdapter(new MyAdapter());
            }
        });

    }

    @Override
    protected void init() {

    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return searchTest.data.size();
        }

        @Override
        public Object getItem(int position) {
            return searchTest.data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = View.inflate(UIUtils.getContext(), R.layout.test_item, null);
            }
            NetworkImageView networkImageView = (NetworkImageView) convertView.findViewById(R.id.test_item_niv);
            networkImageView.setImageUrl(searchTest.data.get(position).exhibition_image, Netroid.getmImageLoader());
            networkImageView.setDefaultImageResId(R.drawable.ic_error_page);
            return convertView;
        }
    }

    // GridView的Adapter
//    private class MyAdapter extends MyBaseAdapter<SearchTest.SearchData> {
//
//        private NetworkImageView imageView;
//        private View view;
//
//        public MyAdapter(List<SearchTest.SearchData> data) {
//            super(data);
//        }
//
//        @Override
//        public View initView() {
//            imageView = new NetworkImageView(UIUtils.getContext());
////            view = View.inflate(UIUtils.getContext(), R.layout.test_item, null);
////            imageView = (NetworkImageView) view.findViewById(R.id.test_item_niv);
//            return imageView;
//        }
//
//        @Override
//        public void setData(View view, SearchTest.SearchData itemData) {
////            this.view = view;
//            this.imageView.setImageUrl(itemData.exhibition_image, Netroid.getmImageLoader());
//        }
//
//        @Override
//        protected List onLoadmore() {
//            return null;
//        }
//    }
}
