package com.can.mvp.demo.fragment;

import android.view.View;

import com.can.mvp.R;
import com.can.mvp.base.basefragment.BaseFragment;
import com.can.mvp.util.ShowToastUtil;

/**
 * Created by can on 2017/11/30.
 */

public class Fragment_Demo1 extends BaseFragment {

    public static Fragment_Demo1 newInstance(){
        Fragment_Demo1 fragment_demo1 = new Fragment_Demo1();
        return fragment_demo1;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo1;
    }

    @Override
    protected void initView(View view) {
        setStatusBarViewVisiable(View.GONE);
        bindView(R.id.iv_click,true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.iv_click:
                ShowToastUtil.showToast(getActivity(),"hello");
                break;
        }
    }
}
