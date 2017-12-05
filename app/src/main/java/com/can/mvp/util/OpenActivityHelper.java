package com.can.mvp.util;

import android.content.Context;

import com.can.mvp.demo.activity.Activity_Demo1;
import com.can.mvp.demo.activity.Activity_Demo2;
import com.can.mvp.demo.activity.Activity_Demo3;
import com.can.mvp.demo.activity.Activity_Demo4;
import com.can.mvp.demo.activity.Activity_Demo5;
import com.can.mvp.demo.activity.Activity_Demo6;

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
    public static void showDemo5Activity(Context context) {
        OpenActivityUtil.getInstance().openActivity(context, Activity_Demo5.class);
    }
    public static void showDemo6Activity(Context context) {
        OpenActivityUtil.getInstance().openActivity(context, Activity_Demo6.class);
    }
}
