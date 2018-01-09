package com.can.mvp.demo.activity

import android.os.Bundle
import com.can.mvp.R
import com.can.mvp.base.baseactivity.BaseActivity
import kotlinx.android.synthetic.main.activity_demo7.*

/**
 * Created by can on 2018/1/9.
 */
class Activity_Demo7: BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar(true,true,true)
    }

    override fun setRootView() {
        super.setRootView()
        setContentView(R.layout.activity_demo7)
        tv.setText("Nihao")
        tv.text = "nihao"
    }



}