package com.cmm.worldartapk.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmm.worldartapk.R;
import com.cmm.worldartapk.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/16.
 */
public class SearchTabView extends LinearLayout {

    private Context context;

    //TextView 集合
    private List<TextView> textViews;

    public SearchTabView(Context context) {
        super(context);
        initView(context);
    }

    public SearchTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SearchTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化
     * @param context
     */
    private void initView(Context context) {
        this.context = context;

        textViews = new ArrayList<TextView>();


        String[] textArr = {"展讯", "艺术馆", "艺术品"};

        //添加三个TextView
        for (int i = 0; i < textArr.length; i++) {
            TextView textView = new TextView(UIUtils.getContext());
            textView.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            textView.setText(textArr[i]);
            int padding = UIUtils.dip2Px(10);
            textView.setPadding(0, padding, 0, padding);
            textView.setTextColor(0xcc000000);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.search_tab_selector);
            //默认第一个选中
            if (i == 0){
                textView.setSelected(true);
            }

            addView(textView); // 添加到布局
            textViews.add(textView); // 添加到集合
            // 设置点击回调
            textViewSetOnClick(textView);


            if (i < textArr.length-1){
                //如果不是最后一个，就添加分割线
                //分割线
                View fgx = new View(UIUtils.getContext());
                fgx.setLayoutParams(new LayoutParams(UIUtils.dip2Px(1), ViewGroup.LayoutParams.MATCH_PARENT));
                fgx.setBackgroundColor(0x33000000);
                addView(fgx);
            }
        }
    }

    // 点击回调
    public interface OnItemClicklistener{
        public void onClick(View view, int index);
    }
    private OnItemClicklistener onItemClicklistener;
    public void setOnItemClicklistener(OnItemClicklistener onItemClicklistener) {
        this.onItemClicklistener = onItemClicklistener;
    }

    // 默认选中
    private int selectedIndex = 0;

    private void textViewSetOnClick(final TextView textView) {
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                textViews.get(selectedIndex).setSelected(false);
                //改变状态
                v.setSelected(true);

                selectedIndex = textViews.indexOf(v);

                if (onItemClicklistener != null){
                    onItemClicklistener.onClick(v, selectedIndex);
                }
            }
        });
    }

    /**
     * 设置选择当前的条目
     * @param index
     */
    public void setCurrentSelectedItem(int index){
        textViews.get(selectedIndex).setSelected(false);
        //改变状态
        textViews.get(index).setSelected(true);

        selectedIndex = index;
    }

    /**
     * @return 返回TextView集合
     */
    public List<TextView> getDataList(){
        return textViews;
    }

}
