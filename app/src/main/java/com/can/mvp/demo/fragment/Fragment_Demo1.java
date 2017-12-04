package com.can.mvp.demo.fragment;

import android.view.View;
import android.widget.ImageView;

import com.can.mvp.R;
import com.can.mvp.base.BaseFragment;
import com.can.mvp.kjActivity.BindView;
import com.can.mvp.util.ShowToastUtil;

/**
 * Created by can on 2017/11/30.
 */

public class Fragment_Demo1 extends BaseFragment {

    @BindView(id = R.id.iv_click,click = true)
    private ImageView iv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo1;
    }

    @Override
    protected void initView(View view) {
        mStatusBarView.setVisibility(View.GONE);
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
        ShowToastUtil.showToast(getActivity(),"hello");
    }
}
