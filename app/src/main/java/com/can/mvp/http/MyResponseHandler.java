package com.can.mvp.http;

import org.kymjs.kjframe.http.HttpCallBack;

/**
 * Created by can on 2017/12/4.
 * 网络数据返回监听
 */

public abstract class MyResponseHandler extends HttpCallBack {
    public MyResponseHandler() {
    }

    public void onSuccess(String t) {
        super.onSuccess(t);
        this.dataSuccess(t);
    }

    public void onFailure(int errorNo, String strMsg) {
        super.onFailure(errorNo, strMsg);
        this.dataFailuer(errorNo, strMsg);
    }

    public void onFinish() {
        super.onFinish();
        this.dataFinish();
    }

    public abstract void dataSuccess(String var1);

    public abstract void dataFinish();

    public abstract void dataFailuer(int var1, String var2);
}

