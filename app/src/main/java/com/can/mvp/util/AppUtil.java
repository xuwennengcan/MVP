package com.can.mvp.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class AppUtil {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 3;

    public AppUtil() {
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static void getPromission(Activity context) {
        LogUtil.i(LogUtil.CP, AppUtil.class + "  来取权限!");
        if(0 == ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE")) {
            LogUtil.i(LogUtil.CP, " 已经有了读取sdcard 的权限!");
        } else {
            LogUtil.i(LogUtil.CP, "  没有 读取sdcard 的权限!");
            if(!ActivityCompat.shouldShowRequestPermissionRationale(context, "android.permission.READ_EXTERNAL_STORAGE")) {
                ActivityCompat.requestPermissions(context, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 2);
            }
        }

        if(0 == ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            LogUtil.i(LogUtil.CP, " 已经有了写入sdcard 的权限!");
        } else {
            LogUtil.i(LogUtil.CP, "  没有 写入sdcard 的权限!");
            if(!ActivityCompat.shouldShowRequestPermissionRationale(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                ActivityCompat.requestPermissions(context, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
            }
        }

        if(0 == ContextCompat.checkSelfPermission(context, "android.permission.CAMERA")) {
            LogUtil.i(LogUtil.CP, " 已经有了  使用相机  的权限!");
        } else {
            LogUtil.i(LogUtil.CP, "  没有  使用相机  的权限!");
            if(!ActivityCompat.shouldShowRequestPermissionRationale(context, "android.permission.CAMERA")) {
                ActivityCompat.requestPermissions(context, new String[]{"android.permission.CAMERA"}, 3);
            }
        }

    }
}
