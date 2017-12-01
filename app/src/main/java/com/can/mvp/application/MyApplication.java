package com.can.mvp.application;


import android.app.Application;

import com.can.mvp.util.ActivityManagerUtil;

/**
 * Application
 * Created by can on 2017/11/17.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;
    private static ActivityManagerUtil activityManager = null;

    public MyApplication() {

    }


    public static Application getInstance(){
        if(mInstance == null){
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    public static ActivityManagerUtil getActivityManager() {
        if(activityManager==null)
            activityManager = ActivityManagerUtil.getInstance();
        return activityManager;
    }

    public static void exit() {
        activityManager.finishAllActivity();
    }
}
