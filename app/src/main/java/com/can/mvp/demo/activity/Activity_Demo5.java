package com.can.mvp.demo.activity;

import android.os.Bundle;

import com.can.mvp.base.baseactivity.XRefreshListViewActivity;

/**
 * Created by can on 2017/11/30.
 * 沉浸式ViewPager 包含状态栏背景为图片和颜色
 */

public class Activity_Demo5 extends XRefreshListViewActivity {

    @Override
    public void setRootView() {
        super.setRootView();
        //   setContentView(R.layout.activity_listview_refresh);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true,true,false);

    }
}
