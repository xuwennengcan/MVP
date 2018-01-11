package com.can.mvp.application

import android.app.Application
import com.can.mvp.util.ActivityManagerUtil

/**
 * Created by can on 2018/1/10.
 */
class MyApplication2:Application(){

    companion object {
        private var instatnce : MyApplication2?=null
        private var util : ActivityManagerUtil ?= null
        fun getInstance() = instatnce
        fun exit(){

            //util?.finishAllActivity()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instatnce = this
    }





}