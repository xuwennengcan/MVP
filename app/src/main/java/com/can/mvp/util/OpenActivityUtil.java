package com.can.mvp.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * 打开其它页面的方法
 * Created by can on 2017/11/29.
 */

public class OpenActivityUtil {

    private static String lastToast = "";
    private static long lastToastTime;
    public static OpenActivityUtil uiHelper;

    public OpenActivityUtil() {
    }

    public static OpenActivityUtil getInstance() {
        if(uiHelper == null) {
            uiHelper = new OpenActivityUtil();
        }

        return uiHelper;
    }

    public static void showTellPhone(Context context, String phone) {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phone));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void openSMS(Context context, String smsBody, String tel) {
        Uri uri = Uri.parse("smsto:" + tel);
        Intent it = new Intent("android.intent.action.SENDTO", uri);
        it.putExtra("sms_body", smsBody);
        context.startActivity(it);
    }

    public static void openDail(Context context) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void openSendMsg(Context context) {
        Uri uri = Uri.parse("smsto:");
        Intent intent = new Intent("android.intent.action.SENDTO", uri);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void openActivity(Context context, Class<?> pClass) {
        openActivity(context, (Class)pClass, (Bundle)null);
    }

    public static void openActivityC(Context context, Class<?> pClass) {
        openActivity(context, (Class)pClass, (Bundle)null);
    }

    public static void openActivity(Context context, Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(context, pClass);
        intent.addFlags(268435456);
        if(pBundle != null) {
            intent.putExtras(pBundle);
        }

        context.startActivity(intent);
    }

    protected void openActivity(Context cotnext, String pAction) {
        this.openActivity(cotnext, (String)pAction, (Bundle)null);
    }

    protected void openActivity(Context cotnext, String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if(pBundle != null) {
            intent.putExtras(pBundle);
        }

        cotnext.startActivity(intent);
    }
}
