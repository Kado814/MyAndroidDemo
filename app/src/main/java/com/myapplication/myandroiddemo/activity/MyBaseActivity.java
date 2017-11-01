package com.myapplication.myandroiddemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.elvishew.xlog.XLog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 我的Activity基类
 **/
public class MyBaseActivity extends Activity {
    public Context context;
    /**
     * 记录处于前台的Activity
     */
    private static MyBaseActivity mForegroundActivity = null;
    /**
     * 记录所有活动的Activity
     */
    private static final List<MyBaseActivity> mActivities = new LinkedList<MyBaseActivity>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);

    }

    /**
     * 获取当前处于栈顶的activity，无论其是否处于前台
     */
    public static MyBaseActivity getCurrentActivity() {
        List<MyBaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<MyBaseActivity>(mActivities);
        }
        if (copy.size() > 0) {
            return copy.get(copy.size() - 1);
        }
        return null;
    }

    /**
     * 是否有启动的Activity
     */
    public static boolean hasActivity() {
        return mActivities.size() > 0;
    }

    /**
     * 获取当前处于前台的activity
     */
    public static MyBaseActivity getForegroundActivity() {
        return mForegroundActivity;
    }

    /**
     * 关闭所有Activity
     */
    public static void finishAll() {
        List<MyBaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<MyBaseActivity>(mActivities);
        }
        for (MyBaseActivity activity : copy) {
            activity.finish();
        }
    }

    /**
     * 关闭所有Activity，除了参数传递的Activity
     */
    public static void finishAll(MyBaseActivity except) {
        List<MyBaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<MyBaseActivity>(mActivities);
        }
        for (MyBaseActivity activity : copy) {
            if (activity != except)
                activity.finish();
        }
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 跳转activity
     **/
    public void startActivity(Context context, Class<?> cls) {
        startActivity(new Intent(context, cls));
    }

    /**
     * Toast提示
     **/
    public void showToast(String msg, int time) {
        Toast.makeText(context, msg, time).show();
    }

}
