package com.can.mvp.demo.fragment;

import android.view.View;

import com.can.mvp.R;
import com.can.mvp.base.basefragment.BaseFragment;

/**
 * Created by can on 2017/11/30.
 */

public class Fragment_Demo2 extends BaseFragment {


    public static Fragment_Demo2 newInstance(){
        Fragment_Demo2 fragment = new Fragment_Demo2();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo2;
    }

    @Override
    protected void initView(View view) {

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
