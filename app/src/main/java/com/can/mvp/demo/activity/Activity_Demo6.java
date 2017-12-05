package com.can.mvp.demo.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.can.mvp.R;
import com.can.mvp.base.baseactivity.BaseActivity;
import com.can.mvp.base.baseadapter.BasePageAdapter;
import com.can.mvp.base.baseview.MyTablayout;
import com.can.mvp.demo.fragment.Fragment_Demo1;
import com.can.mvp.demo.fragment.Fragment_Demo2;
import com.can.mvp.kjActivity.BindView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by can on 2017/11/30.
 * 沉浸式ViewPager 包含状态栏背景为图片和颜色
 */

public class Activity_Demo6 extends BaseActivity {

    @BindView(id = R.id.tl_activity)
    private MyTablayout tl;
    @BindView(id = R.id.vp_activity)
    private ViewPager vp;

    private List<Fragment> fragmentList;//视图集合
    private String[] strings ; //滚动标题栏

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_demo6);
    }

    @Override
    public void initView() {
        super.initView();
        fragmentList = new ArrayList<>();
        strings = getResources().getStringArray(R.array.strings_viewpager);
        fragmentList.add(Fragment_Demo1.newInstance());
        fragmentList.add(Fragment_Demo2.newInstance());

        BasePageAdapter adapter = new BasePageAdapter(getSupportFragmentManager(),strings,fragmentList);
        vp.setAdapter(adapter);
        tl.setAdapter(adapter,vp,strings,R.layout.item_my_tablayout);
    }
}
