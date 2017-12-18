package com.kxky.demo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.kxky.demo.R;
import com.kxky.demo.ui.BaseActivity;
import com.kxky.demo.utils.CMLog;
import com.kxky.demo.utils.SharePref;

/**
 * Created by kxky on 2017/12/15.
 */

public class SplashActivity extends BaseActivity {

    /**
     * TAG
     */
    private static final String TAG = SplashActivity.class.getSimpleName();

    /**
     * 是否是第一次进入
     */
    boolean isFirstIn = false;

    private ImageView splash_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        splash_image = (ImageView) findViewById(R.id.splash_image);
        isFirstIn = new SharePref(mContext).getIsFrist();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        showHeadContanier(View.GONE);
    //    isFirstIn = new SharePref(mContext).getIsFrist();
        toAntherActivity();
    }

    /**
     * 跳转至home页面
     */
    private void toAntherActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                if (isFirstIn)//isFirst ==true 为第一次登录
                {
                    CMLog.e(TAG,"first");
                    intent = new Intent(mContext, MainActivity.class);
                    //   intent = new Intent(mContext, GuidePagerActivity.class);
                } else {
                    CMLog.e(TAG,"no_first");
                    intent = new Intent(mContext, MainActivity.class);
                }
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 1000);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }
}