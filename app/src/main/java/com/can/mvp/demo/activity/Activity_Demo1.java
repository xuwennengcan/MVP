package com.can.mvp.demo.activity;

import android.os.Bundle;

import com.can.mvp.R;
import com.can.mvp.base.baseactivity.BaseActivity;

/**
 * 沉浸式
 * Created by can on 2017/11/29.
 */

public class Activity_Demo1 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(false,false,true);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_demo1);
    }
}
