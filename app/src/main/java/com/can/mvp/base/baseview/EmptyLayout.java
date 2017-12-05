package com.can.mvp.base.baseview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.can.mvp.R;
import com.can.mvp.util.NetWorkUtil;
import com.github.ybq.android.spinkit.style.ThreeBounce;

/**
 * Created by can on 2017/12/4.
 */

public class EmptyLayout extends LinearLayout implements View.OnClickListener {
    public static final int HIDE_LAYOUT = 4;
    public static final int NETWORK_ERROR = 1;
    public static final int NETWORK_LOADING = 2;
    public static final int NODATA = 3;
    public static final int NODATA_ENABLE_CLICK = 5;
    public static final int NO_LOGIN = 6;
    private ProgressBar animProgress;
    private boolean clickEnable = true;
    private final Context context;
    public ImageView img;
    private View.OnClickListener listener;
    private int mErrorState;
    private RelativeLayout mLayout;
    private RelativeLayout error_nodata_layout;
    private String strNoDataContent = "";
    private int imgResource = 0;
    private TextView tv;
    private LinearLayout error_load_fail_lin;
    private LinearLayout error_loading_lin;

    public EmptyLayout(Context context) {
        super(context);
        this.context = context;
        this.init();
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.init();
    }

    private void init() {
        View view = View.inflate(this.context, R.layout.view_error_layout, (ViewGroup)null);
        this.img = (ImageView)view.findViewById(R.id.img_error_layout);
        this.tv = (TextView)view.findViewById(R.id.tv_error_layout);
        this.error_loading_lin = (LinearLayout)view.findViewById(R.id.error_loading_lin);
        this.error_load_fail_lin = (LinearLayout)view.findViewById(R.id.error_load_fail_lin);
        this.error_nodata_layout = (RelativeLayout)view.findViewById(R.id.error_nodata_layout);
        this.mLayout = (RelativeLayout)view.findViewById(R.id.pageerrLayout);
        this.animProgress = (ProgressBar)view.findViewById(R.id.animProgress);
        ThreeBounce load_style = new ThreeBounce();
        this.animProgress.setIndeterminateDrawable(load_style);
        this.setBackgroundColor(-1);
        this.setOnClickListener(this);
        this.img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(EmptyLayout.this.clickEnable && EmptyLayout.this.listener != null) {
                    EmptyLayout.this.listener.onClick(v);
                }

            }
        });
        this.addView(view);
        this.changeErrorLayoutBgMode(this.context);
    }

    public void changeErrorLayoutBgMode(Context context1) {
    }

    public void dismiss() {
        this.mErrorState = 4;
        this.setVisibility(8);
    }

    public int getErrorState() {
        return this.mErrorState;
    }

    public boolean isLoadError() {
        return this.mErrorState == 1;
    }

    public boolean isLoading() {
        return this.mErrorState == 2;
    }

    public void onClick(View v) {
        if(this.clickEnable && this.listener != null) {
            this.listener.onClick(v);
        }

    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.onSkinChanged();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onSkinChanged() {
    }

    public void setDayNight(boolean flag) {
    }

    public void setErrorMessage(String msg) {
        this.tv.setText(msg);
    }

    public void setErrorType(int i) {
        this.setVisibility(0);
        switch(i) {
            case 1:
                this.mErrorState = 1;
                this.error_load_fail_lin.setVisibility(0);
                this.error_loading_lin.setVisibility(8);
                if(NetWorkUtil.hasInternetConnected(this.context)) {
                    this.tv.setText(R.string.error_view_load_error_click_to_refresh);
                    this.img.setBackgroundResource(R.drawable.img_pagefailed_bg);
                } else {
                    this.tv.setText(R.string.error_view_network_error_click_to_refresh);
                    this.img.setBackgroundResource(R.drawable.img_no_net);
                }

                this.img.setVisibility(0);
                this.animProgress.setVisibility(8);
                this.clickEnable = true;
                break;
            case 2:
                this.error_load_fail_lin.setVisibility(8);
                this.error_loading_lin.setVisibility(0);
                this.mErrorState = 2;
                this.animProgress.setVisibility(0);
                this.img.setVisibility(8);
                this.tv.setText(R.string.error_view_loading);
                this.clickEnable = false;
                break;
            case 3:
                this.error_load_fail_lin.setVisibility(0);
                this.error_loading_lin.setVisibility(8);
                this.mErrorState = 3;
                this.img.setVisibility(0);
                this.animProgress.setVisibility(8);
                this.setTvNoDataContent();
                this.clickEnable = true;
                break;
            case 4:
                this.setVisibility(8);
                break;
            case 5:
                this.error_load_fail_lin.setVisibility(0);
                this.error_loading_lin.setVisibility(8);
                this.mErrorState = 5;
                this.img.setVisibility(0);
                this.animProgress.setVisibility(8);
                this.setTvNoDataContent();
                this.clickEnable = true;
        }

        try {
            if(this.imgResource != 0) {
                this.img.setBackgroundResource(this.imgResource);
            } else {
                this.img.setBackgroundResource(R.drawable.img_no_data);
            }
        } catch (Exception var3) {
            ;
        }

    }

    public void setNoDataContent(String noDataContent) {
        this.strNoDataContent = noDataContent;
    }

    public void setOnLayoutClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setTvNoDataContent() {
        if(!this.strNoDataContent.equals("")) {
            this.tv.setText(this.strNoDataContent);
        } else {
            this.tv.setText(R.string.error_view_no_data);
        }

    }

    public void setNoDataImg(int imId) {
        this.imgResource = imId;
    }

    public void setVisibility(int visibility) {
        if(visibility == 8) {
            this.mErrorState = 4;
        }

        super.setVisibility(visibility);
    }
}
