package com.can.mvp.mine_code.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.can.mvp.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.kjActivity.BindView;
import com.can.mvp.util.OpenActivityHelper;

/**
 * 启动Activity
 * Created by can on 2017/11/17.
 */

public class StartActivity extends BaseActivity {

    @BindView(id = R.id.ll_click1,click = true)
    private LinearLayout ll1;
    @BindView(id = R.id.ll_click2,click = true)
    private LinearLayout ll2;
    @BindView(id = R.id.ll_click3,click = true)
    private LinearLayout ll3;
    @BindView(id = R.id.ll_click4,click = true)
    private LinearLayout ll4;

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
        }
    }
}
