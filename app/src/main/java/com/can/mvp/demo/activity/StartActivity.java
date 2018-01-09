package com.can.mvp.demo.activity;

import android.os.Bundle;
import android.view.View;

import com.can.mvp.R;
import com.can.mvp.base.baseactivity.BaseActivity;
import com.can.mvp.util.OpenActivityHelper;

/**
 * 启动Activity
 * Created by can on 2017/11/17.
 */

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(true,true,true);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void initView() {
        super.initView();
        bindView(R.id.ll_click1,true);
        bindView(R.id.ll_click2,true);
        bindView(R.id.ll_click3,true);
        bindView(R.id.ll_click4,true);
        bindView(R.id.ll_click5,true);
        bindView(R.id.ll_click6,true);
        bindView(R.id.ll_click7,true);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.ll_click1:
                OpenActivityHelper.showDemo1Activity(StartActivity.this);
                break;
            case R.id.ll_click2:
                OpenActivityHelper.showDemo2Activity(StartActivity.this);
                break;
            case R.id.ll_click3:
                OpenActivityHelper.showDemo3Activity(StartActivity.this);
                break;
            case R.id.ll_click4:
                OpenActivityHelper.showDemo4Activity(StartActivity.this);
                break;
            case R.id.ll_click5:
                OpenActivityHelper.showDemo5Activity(StartActivity.this);
                break;
            case R.id.ll_click6:
                OpenActivityHelper.showDemo6Activity(StartActivity.this);
                break;
            case R.id.ll_click7:
                OpenActivityHelper.showDemo7Activity(StartActivity.this);
                break;
        }
    }
}
