package com.kxky.demo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by kxky on 2017/12/15.
 */

public class SharePref {
    private static final String TAG = "SharePref";

    /**
     * SharedPreferences对象
     */
    private SharedPreferences pref;

    /**
     * editor对象
     */
    private Editor editor;

    /**
     * /**
     * 首次使用APP
     */
    public static final String ISFRIST = "IS_FRIST";


    @SuppressLint("CommitPrefEdits")
    public SharePref(Context context)
    {
        pref = context.getSharedPreferences(Constant.KXKY_INFO, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    /**
     * <首次使用APP> <功能详细描述> 返回类型:void
     */
    public void putIsFrist()
    {
        editor.putBoolean(ISFRIST, false);
        editor.commit();
    }

    /**
     * <首次使用APP> <功能详细描述>
     *
     * @return 返回类型:boolean
     */
    public boolean getIsFrist()
    {
        return pref.getBoolean(ISFRIST, true);
    }

    /**
     * <清除本地数据> <功能详细描述> 返回类型:void
     */
    public void clearData()
    {
        if (null != editor)
        {
            editor.clear().commit();
            CMLog.d(TAG, "sharePref 清除缓存成功");
        }
    }

}
