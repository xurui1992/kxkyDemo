package com.kxky.demo.ui.tabfive;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.kxky.demo.BaseApplication;
import com.kxky.demo.R;

import com.kxky.demo.ui.BaseFragment;
import com.kxky.demo.ui.main.LoginActivity;
import com.kxky.demo.ui.main.SpinnerActivity;
import com.kxky.demo.utils.CMLog;
import com.kxky.demo.utils.SecurityThread;


/**
 * Created by kxky on 2017/12/13.
 */

public class TabFiveFragment extends BaseFragment {
    private String TAG = TabFiveFragment.class.getSimpleName();
    private Button mBtnLogout;
    private Button mButtonSpinner;
    private Button daojishi_one;
    private Button daojishi_two;


    @Override
    public int getContentViewId() {
        return R.layout.fragment_tab_five;
    }

    @Override
    public void onViewInitialized(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        mButtonSpinner = (Button) getActivity().findViewById(R.id.button_spinner);
        mButtonSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SpinnerActivity.class);
                startActivity(intent);
            }
        });

        daojishi_one = (Button) getActivity().findViewById(R.id.daojishi_one);
        daojishi_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SecurityThread(daojishi_one, mContext.getResources()
                        .getColor(R.color.red), mContext.getResources()
                        .getColor(R.color.red)).start();
            }
        });

        daojishi_two = (Button) getActivity().findViewById(R.id.daojishi_two);
        daojishi_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        mBtnLogout = (Button) getActivity().findViewById(R.id.btn_logout);
        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext).setTitle("温馨提示")//设置对话框标题
                        .setMessage("确认要退出该账号吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BaseApplication.getApplication().logout(mContext);
                                Intent intent1 = new Intent(mContext, LoginActivity.class);
                                mContext.startActivity(intent1);
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });

    }

    private void startTimer() {
        daojishi_two.setClickable(false);
        timer.start();
    }

    private void endTimer() {
        daojishi_two.setClickable(true);
        timer.cancel();
    }

    //定时器
    private CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            daojishi_two.setText("重新发送 " + "(" + millisUntilFinished / 1000 + ")");
            CMLog.e(TAG, "...." + millisUntilFinished);
        }

        @Override
        public void onFinish() {
            endTimer();
            daojishi_two.setText("发送");
        }
    };

}
