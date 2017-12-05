package com.can.mvp.base.baseactivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.can.mvp.R;
import com.can.mvp.application.MyApplication;
import com.can.mvp.kjActivity.KJActivity;
import com.can.mvp.manager.SystemBarTintManager;
import com.can.mvp.util.ActivityManagerUtil;
import com.can.mvp.util.AppUtil;

/**
 * Created by can on 2017/11/17.
 */

public class BaseActivity extends KJActivity{

    public Context mContext;
    public SystemBarTintManager tintManager;

    public BaseActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(1);
        this.setRequestedOrientation(1);
        ActivityManagerUtil.getInstance().addActivty(this);
        this.mContext = MyApplication.getInstance();
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 19) {
            this.setTranslucentStatus(true);
        }

        this.tintManager = new SystemBarTintManager(this);

        AppUtil.getPromission(this);
        setStatusBarFullTransparent();
        //setStatusBar(true,true,true); //默认状态
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = this.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if(on) {
            winParams.flags |= 67108864;
        } else {
            winParams.flags &= -67108865;
        }

        win.setAttributes(winParams);
    }

    protected void onDestroy() {
        ((MyApplication)this.getApplication()).getActivityManager().finishActivity(this);
        this.mContext = null;
        super.onDestroy();
    }

    public void initWidget() {
        super.initWidget();
        this.initView();
    }

    public void initData() {
        super.initData();
        this.initMyData();
    }

    protected void initView() {

    }

    public void setRootView() {
    }

    public void initMyData() {
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
    }

    /**
     * 设置状态栏状态
     * @param isStatusBarTint 是否给状态栏着色
     * @param isFitStatusBar 内容是否紧贴着状态栏
     * @param isFullTransparent 全透明或半透明  true 全透明 false 半透明
     */
    public void setStatusBar(boolean isStatusBarTint , boolean isFitStatusBar , boolean isFullTransparent){
        tintManager.setStatusBarTintEnabled(isStatusBarTint);
        setFitSystemWindow(isFitStatusBar);
        if(isFullTransparent) {
            setStatusBarFullTransparent();
        }
        else {
            setHalfTransparent();
        }
        setDrawerLayoutFitSystemWindow();

        //默认
        tintManager.setStatusBarTintColor(ContextCompat.getColor(this, R.color.color_fc4743));
        tintManager.setTintAlpha(1);
    }

    /**
     * 设置为全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 设置为半透明状态栏
     */
    protected void setHalfTransparent() {

        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 如果需要内容紧贴着StatusBar
     * 应该在对应的xml布局文件中，设置根布局fitsSystemWindows=true。
     */
    private View contentViewGroup;

    protected void setFitSystemWindow(boolean fitSystemWindow) {
        if (contentViewGroup == null) {
            contentViewGroup = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        }
        contentViewGroup.setFitsSystemWindows(fitSystemWindow);
    }

    /**
     * 为了兼容4.4的抽屉布局->透明状态栏
     */
    protected void setDrawerLayoutFitSystemWindow() {
        if (Build.VERSION.SDK_INT == 19) {//19表示4.4
            int statusBarHeight = getStatusHeight(this);
            if (contentViewGroup == null) {
                contentViewGroup = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
            }
            if (contentViewGroup instanceof DrawerLayout) {
                DrawerLayout drawerLayout = (DrawerLayout) contentViewGroup;
                drawerLayout.setClipToPadding(true);
                drawerLayout.setFitsSystemWindows(false);
                for (int i = 0; i < drawerLayout.getChildCount(); i++) {
                    View child = drawerLayout.getChildAt(i);
                    child.setFitsSystemWindows(false);
                    child.setPadding(0,statusBarHeight, 0, 0);
                }

            }
        }
    }

    /**
     * 得状态栏的高度
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = 0;
        if (statusHeight <= 0) {
            try {
                Class<?> clazz = Class.forName("com.android.internal.R$dimen");
                Object object = clazz.newInstance();
                int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
                statusHeight = context.getResources().getDimensionPixelSize(height);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

}
