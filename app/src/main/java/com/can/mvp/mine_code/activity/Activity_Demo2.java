package com.can.mvp.mine_code.activity;

import android.os.Bundle;

import com.can.mvp.R;
import com.can.mvp.base.BaseActivity;

/**
 * 沉浸式 - 半透明
 * Created by can on 2017/11/29.
 */

public class Activity_Demo2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true,true,true);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_demo2);
    }
}
