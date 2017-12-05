package com.can.mvp.base.baseview;


import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.TextView;

import com.can.mvp.R;

/**
 * Created by can on 2017/12/1.
 * 自定义TabLayout ：
 *          下划线与文本等长
 */

public class MyTablayout extends TabLayout {


    public MyTablayout(Context context) {
        super(context);
    }

    public MyTablayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置适配器
     */
    public void setAdapter(final FragmentPagerAdapter adapter , final ViewPager vp , String [] strings, int view){
        setupWithViewPager(vp);

        for(int i = 0 ; i<adapter.getCount();i++){
            TabLayout.Tab tab = getTabAt(i);
            tab.setCustomView(view);
            if(i==0){
                tab.getCustomView().findViewById(R.id.tv_item).setSelected(true);
                tab.getCustomView().findViewById(R.id.view_item).setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_fc4743));
            }
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tv_item);
            textView.setText(strings[i]);
        }
        setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tv_item).setSelected(true);
                tab.getCustomView().findViewById(R.id.view_item).setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_fc4743));
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(Tab tab) {
                tab.getCustomView().findViewById(R.id.tv_item).setSelected(false);
                tab.getCustomView().findViewById(R.id.view_item).setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_white));
            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });
    }

}
