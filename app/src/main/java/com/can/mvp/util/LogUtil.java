package com.can.mvp.util;

import android.util.Log;

/**
 * Created by can on 2017/11/17.
 */


public class LogUtil {
    private static boolean isDebug = true;
    private static String TAG = "Ican";
    public static String CP = "Ican";

    public LogUtil() {
    }

    public static void w(String msg) {
        if(isDebug && null == msg) {
            msg = "msg is null ";
        }

        Log.i(TAG, msg);
    }

    public static void w(int msg) {
        w(msg + "");
    }

    public static void w(float msg) {
        w(msg + "");
    }

    public static void v(String tag, String msg) {
        if(isDebug) {
            Log.v(tag, msg);
        }

    }

    public static void v(String tag, String msg, Throwable t) {
        if(isDebug) {
            Log.v(tag, msg, t);
        }

    }

    public static void d(String tag, String msg) {
        if(isDebug) {
            Log.d(tag, msg);
        }

    }

    public static void d(String tag, String msg, Throwable t) {
        if(isDebug) {
            Log.d(tag, msg, t);
        }

    }

    public static void i(String tag, String msg) {
        if(isDebug) {
            Log.i(tag, msg);
        }

    }

    public static void i(String tag, String msg, Throwable t) {
        if(isDebug) {
            Log.i(tag, msg, t);
        }

    }

    public static void w(String tag, String msg) {
        if(isDebug) {
            Log.w(tag, msg);
        }

    }

    public static void w(String tag, String msg, Throwable t) {
        if(isDebug) {
            Log.w(tag, msg, t);
        }

    }

    public static void e(String tag, String msg) {
        if(isDebug) {
            Log.e(tag, msg);
        }

    }

    public static void e(String tag, String msg, Throwable t) {
        if(isDebug) {
            Log.e(tag, msg, t);
        }

    }

    public static final void analytics(String log) {
        if(isDebug) {
            Log.d(TAG, log);
        }

    }

    public static final void error(String log) {
        if(isDebug) {
            Log.e(TAG, "" + log);
        }

    }

    public static final void log(String log) {
        if(isDebug) {
            Log.i(TAG, log);
        }

    }

    public static final void log(String tag, String log) {
        if(isDebug) {
            Log.i(tag, log);
        }

    }

    public static final void logv(String log) {
        if(isDebug) {
            Log.v(TAG, log);
        }

    }

    public static final void warn(String log) {
        if(isDebug) {
            Log.w(TAG, log);
        }

    }
}

