package com.can.mvp.util;

import android.content.Context;

import com.can.mvp.mine_code.activity.Activity_Demo1;
import com.can.mvp.mine_code.activity.Activity_Demo2;
import com.can.mvp.mine_code.activity.Activity_Demo3;
import com.can.mvp.mine_code.activity.Activity_Demo4;

/**
 * 展示其它页面
 * Created by can on 2017/11/29.
 */

public class OpenActivityHelper {

    public static void showDemo1Activity(Context context) {
        OpenActivityUtil.getInstance().openActivity(context, Activity_Demo1.class);
    }
    public static void showDemo2Activity(Context context) {
        OpenActivityUtil.getInstance().openActivity(context, Activity_Demo2.class);
    }
    public static void showDemo3Activity(Context context) {
        OpenActivityUtil.getInstance().openActivity(context, Activity_Demo3.class);
    }
    public static void showDemo4Activity(Context context) {
        OpenActivityUtil.getInstance().openActivity(context, Activity_Demo4.class);
    }
}
