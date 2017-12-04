package com.can.mvp.demo.fragment;

import android.view.View;

import com.can.mvp.R;
import com.can.mvp.base.BaseFragment;

/**
 * Created by can on 2017/11/30.
 */

public class Fragment_Demo2 extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo2;
    }

    @Override
    protected void initView(View view) {
        setStatusBarViewBackground(getActivity(),R.color.color_fc4743);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
