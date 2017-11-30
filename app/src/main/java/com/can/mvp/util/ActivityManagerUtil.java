package com.can.mvp.util;

import android.app.Activity;
import android.os.Process;
import java.util.Iterator;
import java.util.Stack;

public class ActivityManagerUtil {
    private static Stack<Activity> activityStack;
    private static ActivityManagerUtil instance;

    private ActivityManagerUtil() {
    }

    public static ActivityManagerUtil getInstance() {
        if(instance == null) {
            instance = new ActivityManagerUtil();
        }

        if(activityStack == null) {
            activityStack = new Stack();
        }

        return instance;
    }

    public static Activity getActivity(Class<?> cls) {
        if(activityStack != null) {
            Iterator var1 = activityStack.iterator();

            while(var1.hasNext()) {
                Activity activity = (Activity)var1.next();
                if(activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        }

        return null;
    }

    public void addActivty(Activity activity) {
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        Activity activity = (Activity)activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = (Activity)activityStack.lastElement();
        this.finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if(activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }

    }

    public void removeActivity(Activity activity) {
        if(activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
        }

    }

    public void finishActivity(Class<?> cls) {
        Iterator var2 = activityStack.iterator();

        while(var2.hasNext()) {
            Activity activity = (Activity)var2.next();
            if(activity.getClass().equals(cls)) {
                this.finishActivity(activity);
                break;
            }
        }

    }

    public void finishAllActivity() {
        int i = 0;

        for(int size = activityStack.size(); i < size; ++i) {
            if(null != activityStack.get(i)) {
                this.finishActivity((Activity)activityStack.get(i));
            }
        }

        activityStack.clear();
    }

    public void AppExit() {
        try {
            this.finishAllActivity();
            Process.killProcess(Process.myPid());
            System.exit(0);
        } catch (Exception var2) {
            ;
        }

    }
}
