package com.can.mvp.kjActivity;

import android.view.View;

/**
 * 接口协议，实现此接口可使用KJActivityManager堆栈
 * Created by can on 2017/11/17.
 */

public interface I_KJActivity {

    int DESTROY = 0;
    int STOP = 2;
    int PAUSE = 1;
    int RESUME = 3;

    /**
     * 设置root界面
     */
    void setRootView();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 在线程中初始化数据
     */
    void initDataFromThread();

    /**
     * 初始化控件
     */
    void initWidget();

    /**
     * 点击事件回调方法
     */
    void widgetClick(View v);
}
