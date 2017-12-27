package com.kxky.demo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.kxky.demo.http.RetrofitService;
import com.kxky.demo.ui.BaseActivity;
import com.kxky.demo.utils.CMLog;
import com.kxky.demo.utils.SharePref;

import java.util.Stack;


/**
 * Created by kxky on 2017/12/14.
 */

public class BaseApplication extends Application {

    /**
     * tag
     */
    public static final String TAG = "BaseApplication";

    /**
     * 本地栈
     */
    private Stack<BaseActivity> activitys = null;

    /**
     * app 实例
     */
    private static BaseApplication instance;


    public static Context applicationContext;

    /**
     * 单例，返回一个实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        if (instance == null) {
            instance = new BaseApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
        if (activitys == null) {
            activitys = new Stack<BaseActivity>();
        }
        /**
         * 初始化 比如友盟，极光，分享等
         */
        /**初始化网络请求模块*/
        RetrofitService.getInstance().init(this);

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        int memClass = activityManager.getMemoryClass();//64，以m为单位
        CMLog.e(TAG, "-----内存-----》" + memClass + "");
    }

    /**
     * <添加activity> <功能详细描述>
     *
     * @param activity activity 返 回 类 型：void
     */
    public void addActivity(BaseActivity activity) {
        CMLog.e(TAG, activity + "");
        activitys.add(activity);
    }

    /**
     * <获得当前栈顶Activity> <功能详细描述>
     *
     * @return activity 返 回 类 型：Activity
     */
    public BaseActivity currentActivity() {
        BaseActivity activity = null;
        if (!activitys.empty()) {
            activity = activitys.lastElement();
        }
        return activity;
    }

    /**
     * 退出登录
     *
     * @param context
     */
    public void logout(Context context) {
        clearAllActivities();
        SharePref pref = new SharePref(context);
        pref.putIsFrist();
    }

    /**
     * <删除activity栈中一个activity> <功能详细描述>
     *
     * @param activity activity 返 回 类 型：void
     */
    public void deleteActivity(Activity activity) {
        if (activity != null) {
            CMLog.e("deleteActivity:", activity + "");
            activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }


    /**
     * <退出应用程序，清除activity栈中所有的activity> <功能详细描述> 返 回 类 型：void
     */
    public void exit() {
        for (Activity activity : activitys) {
            activity.finish();
            activity = null;
        }
        activitys.clear();
        // 退出程序 (会导致出现异常时，重启应用程序两次)
        // android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /***
     * <清除所有activitys> <功能详细描述> 返回类型:void
     */
    public void clearAllActivities() {
        for (Activity activity : activitys) {
            activity.finish();
            activity = null;
        }
        activitys.clear();
    }

    /**
     * <获取本地activity栈> <功能详细描述>
     *
     * @return 返 回 类 型：Stack<Activity>
     */
    public Stack<BaseActivity> getActivitys() {
        return instance.activitys;
    }

    public static BaseApplication getApplication() {
        return instance;
    }

}
