package com.can.mvp.mine_code.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.can.mvp.R;
import com.can.mvp.kjActivity.KJFragment;

/**
 * Created by can on 2017/11/30.
 */

public class Fragment_Demo2 extends KJFragment {

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_demo2,null);
    }
}
