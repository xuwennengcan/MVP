package com.can.mvp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.can.mvp.R;

/**
 * Created by can on 2017/11/17.
 */

public class ShowToastUtil {
    private static String lastToast = "";
    private static long lastToastTime;

    public ShowToastUtil() {
    }

    public static void showToast(Context context, int message) {
        showToast(context, message, 1, 0);
    }

    public static void showToast(Context context, String message) {
        showToast(context, message, 1, 0, 80);
    }

    public static void showToast(Context context, int message, int icon) {
        showToast(context, message, 1, icon);
    }

    public static void showToast(Context context, String message, int icon) {
        showToast(context, message, 1, icon, 80);
    }

    public static void showToastShort(Context context, int message) {
        showToast(context, message, 0, 0);
    }

    public static void showToastShort(Context context, String message) {
        showToast(context, message, 0, 0, 80);
    }

    public static void showToastShort(Context context, int message, Object... args) {
        showToast(context, message, 0, 0, 80, args);
    }

    public static void showToast(Context context, int message, int duration, int icon) {
        showToast(context, message, duration, icon, 80);
    }

    public static void showToast(Context context, int message, int duration, int icon, int gravity) {
        showToast(context, context.getString(message), duration, icon, gravity);
    }

    public static void showToast(Context context, int message, int duration, int icon, int gravity, Object... args) {
        showToast(context, context.getString(message, args), duration, icon, gravity);
    }

    public static void showToast(Context context, String message, int duration, int icon, int gravity) {
        if(message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if(!message.equalsIgnoreCase(lastToast) || Math.abs(time - lastToastTime) > 2000L) {
                View view = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
                ((TextView)view.findViewById(R.id.tv_title)).setText(message);
                if(icon != 0) {
                    ((ImageView)view.findViewById(R.id.iv_title)).setImageResource(icon);
                    view.findViewById(R.id.iv_title).setVisibility(View.GONE);
                }

                Toast toast = new Toast(context);
                toast.setView(view);
                if(gravity == 17) {
                    toast.setGravity(gravity, 0, 0);
                } else {
                    toast.setGravity(gravity, 0, 35);
                }

                toast.setDuration(duration);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }

    }
}
