package com.can.mvp.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.can.mvp.kjActivity.ViewColor;
import com.can.mvp.util.AnnotateUtil;

/**
 * Created by can on 2017/12/1.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected ViewGroup mView;
    protected View mStatusBarView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = (ViewGroup) inflater.inflate(getLayoutId(), null);
            AnnotateUtil.initBindView(this, mView);
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addStatusBar();
        initView(view);
        initData();
        initListener();
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void initListener();

    private void addStatusBar() {
        if (mStatusBarView == null) {
            mStatusBarView = new View(getActivity());
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            int statusBarHeight = ViewColor.getStatusBarHeight(getActivity());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(screenWidth, statusBarHeight);
            mStatusBarView.setLayoutParams(params);
            mStatusBarView.requestLayout();
            if (mView != null&& Build.VERSION.SDK_INT >= 19)
                mView.addView(mStatusBarView, 0);
        }
    }

    /**
     * 设置状态栏背景色
     * @param context 上下文
     * @param color 颜色值
     */
    public  void setStatusBarViewBackground(Context context, int color){
        if ( Build.VERSION.SDK_INT >= 19)
            mStatusBarView.setBackgroundColor(ContextCompat.getColor(context, color));
    }

    protected <T extends View> T bindView(int id) {
        return (T) mView.findViewById(id);
    }

    protected <T extends View> T bindView(int id, boolean click) {
        T view = (T) mView.findViewById(id);
        if (click) {
            view.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /**
     * widget click method
     */
    protected void widgetClick(View v) {
    }
}
