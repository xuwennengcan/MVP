package com.can.mvp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by can on 2017/11/17.
 */

public class NetWorkUtil {
    public static final int NETTYPE_WIFI = 1;
    public static final int NETTYPE_CMWAP = 2;
    public static final int NETTYPE_CMNET = 3;

    public NetWorkUtil() {
    }

    public boolean validateInternetIfNoShowDialog(Context mContext) {
        ConnectivityManager manager = (ConnectivityManager)mContext.getSystemService("connectivity");
        if(manager == null) {
            this.openWirelessSet(mContext);
            return false;
        } else {
            NetworkInfo[] info = manager.getAllNetworkInfo();
            if(info != null) {
                for(int i = 0; i < info.length; ++i) {
                    if(info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }

            this.openWirelessSet(mContext);
            return false;
        }
    }

    public void openWirelessSet(final Context mContext) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setTitle("网络设置").setMessage("检查网络").setPositiveButton("网络设置", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
                mContext.startActivity(intent);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        dialogBuilder.show();
    }

    public static int getNetworkType(Context context) {
        byte netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null) {
            return netType;
        } else {
            int nType = networkInfo.getType();
            if(nType == 0) {
                String extraInfo = networkInfo.getExtraInfo();
                if(!StringUtils.isEmpty(extraInfo)) {
                    if(extraInfo.toLowerCase().equals("cmnet")) {
                        netType = 3;
                    } else {
                        netType = 2;
                    }
                }
            } else if(nType == 1) {
                netType = 1;
            }

            return netType;
        }
    }

    public static boolean isWifiConn(Context context) {
        return getNetworkType(context) == 1;
    }

    public static boolean hasInternetConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService("connectivity");
        if(manager != null) {
            NetworkInfo network = manager.getActiveNetworkInfo();
            if(network != null && network.isConnectedOrConnecting()) {
                return true;
            }
        }

        return false;
    }

    public static boolean yesNext(Context context) {
        if(!hasInternetConnected(context)) {
            ShowToastUtil.showToast(context, "没有可用的网络");
            return false;
        } else {
            return true;
        }
    }
}
