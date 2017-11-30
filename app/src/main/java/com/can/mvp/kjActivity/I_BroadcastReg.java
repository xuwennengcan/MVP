package com.can.mvp.kjActivity;

/**
 * 规范Activity中广播接受者注册的接口协议
 * Created by can on 2017/11/17.
 */

public interface I_BroadcastReg {
    /**
     * 注册广播
     */
    void registerBroadcast();

    /**
     * 解除注册广播
     */
    void unRegisterBroadcast();
}
