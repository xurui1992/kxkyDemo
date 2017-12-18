package com.kxky.demo.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.kxky.demo.R;

/**
 * Created by kxky on 2017/12/15.
 */
public class LoadingProgressDialog extends Dialog {

    private TextView mTextView;
    private View mImageView;
    AsyncTask mAsyncTask;

    private final OnCancelListener mCancelListener
            = new OnCancelListener() {

        @Override
        public void onCancel(DialogInterface dialog) {
            if(mAsyncTask != null) {
                mAsyncTask.cancel(true);
            }
        }
    };

    /**
     * @param context
     */
    public LoadingProgressDialog(Context context) {
        super(context , R.style.Theme_Light_CustomDialog_Blue);
        getWindow().setGravity(Gravity.CENTER);
        mAsyncTask = null;
        setCancelable(true);
        setContentView(R.layout.common_loading_dialog);
        View decorView = getWindow().getDecorView();
        decorView.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        mTextView = (TextView)findViewById(R.id.textview);
//        mTextView.setText(R.string.loading_press);
        mImageView = findViewById(R.id.imageview);
        setOnCancelListener(mCancelListener);
    }

    /**
     * @param context
     * @param resid
     */
    public LoadingProgressDialog(Context context , int resid) {
        this(context);
        mTextView.setText(resid);
    }

    public LoadingProgressDialog(Context context , CharSequence text) {
        this(context);
        mTextView.setText(text);
    }

    public LoadingProgressDialog(Context context , AsyncTask asyncTask) {
        this(context);
        mAsyncTask = asyncTask;
    }

    public LoadingProgressDialog(Context context , CharSequence text , AsyncTask asyncTask) {
        this(context , text);
        mAsyncTask = asyncTask;
    }

    /**
     * 设置对话框显示文本
     * @param text
     */
    public final void setPressText(CharSequence text) {
        mTextView.setText(text);
    }

    public final void dismiss() {
        try {
            super.dismiss();
            mImageView.clearAnimation();
        }catch (Exception e){

        }
    }

    public final void show() {
        super.show();
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.loading);
        mImageView.startAnimation(loadAnimation);
    }
}
