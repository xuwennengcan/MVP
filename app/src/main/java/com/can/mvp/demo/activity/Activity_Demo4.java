package com.can.mvp.demo.activity;

import android.view.View;
import android.widget.Button;

import com.can.mvp.R;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.kjActivity.BindView;
import com.can.mvp.demo.fragment.Fragment_Demo1;
import com.can.mvp.demo.fragment.Fragment_Demo2;

/**
 * Created by can on 2017/11/30.
 * 沉浸式ViewPager 包含状态栏背景为图片和颜色
 */

public class Activity_Demo4 extends BaseActivity {

    @BindView(id = R.id.btn_fragment1,click = true)
    private Button btn_fragment1;    //按钮1
    @BindView(id = R.id.btn_fragment2,click = true)
    private Button btn_fragment2;    //按钮2

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_demo4);
        changeFragment(R.id.frame_layout,new Fragment_Demo1());

    }

    @Override
    protected void initView() {
        super.initView();
        changeFragment(R.id.frame_layout,new Fragment_Demo1());

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.btn_fragment1:
                changeFragment(R.id.frame_layout,new Fragment_Demo1());

                break;
            case R.id.btn_fragment2:
                changeFragment(R.id.frame_layout,new Fragment_Demo2());

                break;
        }
    }
}
