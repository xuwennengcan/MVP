package com.can.mvp.activity;

import android.os.Bundle;

import com.can.mvp.R;
import com.can.mvp.base.BaseActivity;

/**
 * 沉浸式 - 全透明
 * Created by can on 2017/11/29.
 */

public class Activity_Demo3 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true,true,false);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_demo3);
    }
}
