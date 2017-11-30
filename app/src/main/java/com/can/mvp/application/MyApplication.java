package com.can.mvp.application;


import android.app.Application;

import com.can.mvp.util.ActivityManagerUtil;

/**
 * Application
 * Created by can on 2017/11/17.
 */

public class MyApplication extends Application {
    private static MyApplication context;
    private static ActivityManagerUtil activityManager = null;

    public MyApplication() {

    }

    public void onCreate() {
        super.onCreate();
        context = this;
        activityManager = ActivityManagerUtil.getInstance();
    }

    public static MyApplication getInstance() {
        return context;
    }

    public ActivityManagerUtil getActivityManager() {
        return activityManager;
    }

    public static void exit() {
        activityManager.finishAllActivity();
    }
}
