package com.kxky.demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kxky.demo.BaseApplication;
import com.kxky.demo.R;
import com.kxky.demo.utils.dialog.LoadingProgressDialog;

/**
 * Created by kxky on 2017/12/14.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    /**
     * TAG
     */
    private static final String TAG = BaseActivity.class.getSimpleName();

    /**
     * Context
     */
    protected Context mContext = null;

    /**
     * Activity title容器部分
     **/
    protected LinearLayout layoutheadview;

    /**
     * 返回
     */
    protected TextView tvHeadLeftBack;

    /**
     * 返回按钮
     */
    protected LinearLayout layoutBackContainer;

    /**
     * title
     */
    protected TextView tvHeadTitle;

    /**
     * 右部容器
     */
    protected FrameLayout frameHeadRightContainer;

    /**
     * 右侧设置部分， 按钮
     */
    protected TextView tvHeadRight;

    /**
     * 右侧设置部分， 未读图标
     */
    protected TextView tvMessageCount;

    /**
     * end
     */
    protected View viewLine;

    /**
     * 显示部分
     */
    protected LinearLayout layoutcontainer;

    /**
     * 全局加载框
     */
    protected LoadingProgressDialog mLoadingProgressDialog;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        BaseApplication.getApplication().addActivity(this);
        setContentView(R.layout.activity_base);
        initView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化控件
     *
     * @param savedInstanceState
     */
    protected void initView(Bundle savedInstanceState) {
        layoutheadview = (LinearLayout) findViewById(R.id.layout_headview);
        if (getHeadViewId() != 0) {
            layoutheadview.addView(
                    getLayoutInflater().inflate(getHeadViewId(), null),
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

            switch (getHeadViewId()) {
                case R.layout.default_head_layout:
                    // head部分
                    layoutBackContainer = (LinearLayout) findViewById(R.id.layout_back_container);
                    layoutBackContainer.setOnClickListener(this);
                    tvHeadLeftBack = (TextView) findViewById(R.id.tv_head_left_back);
                    tvHeadTitle = (TextView) findViewById(R.id.tv_head_title);
                    frameHeadRightContainer = (FrameLayout) findViewById(R.id.frame_head_right_container);
                    frameHeadRightContainer.setOnClickListener(this);
                    tvHeadRight = (TextView) findViewById(R.id.tv_head_right);
                    tvMessageCount = (TextView) findViewById(R.id.tv_message_count);
                    viewLine = findViewById(R.id.view_line);
                    break;
                default:
                    break;
            }
        } else {
            layoutheadview.setVisibility(View.GONE);
        }
        // 初始化容器部分
        layoutcontainer = (LinearLayout) findViewById(R.id.layout_container);
        if (getContentViewId() != 0) {
            layoutcontainer.addView(
                    getLayoutInflater().inflate(getContentViewId(), null),
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
        } else {
            layoutcontainer.setVisibility(View.GONE);
        }

    }

    /**
     * <是否加载自定义布局> <功能详细描述>
     *
     * @return 返回类型:int
     */
    protected int getHeadViewId() {
        return R.layout.default_head_layout;
    }

    /**
     * <获得内容布局id>
     *
     * @param
     * @return 请 返回类型:View
     */
    protected abstract int getContentViewId();

    /**
     * 展示头部容器
     *
     * @param visibility
     */
    protected void showHeadContanier(int visibility) {
        if (layoutheadview != null) {
            layoutheadview.setVisibility(visibility);
        }
    }

    /**
     * <返回按钮点击事件> <功能详细描述> 返 回 类 型：void
     */
    protected void onBackClick() {
        this.finish();
    }

    /**
     * 返回按钮是否可见
     *
     * @param visibility
     */
    protected void showBackVisible(int visibility) {
        if (tvHeadLeftBack != null) {
            tvHeadLeftBack.setVisibility(visibility);
        }
    }

    /**
     * <设置title bar颜色> <功能详细描述>
     *
     * @param resID 文本颜色color的id 返回类型:void
     */
    public void setTitleColor(int resID) {
        layoutheadview.setBackgroundColor(resID);
    }

    /**
     * <设置title 文字> <功能详细描述>
     *
     * @param name 返回类型:void
     */
    public void setTitle(String name) {
        tvHeadTitle.setTextColor(getResources().getColor(R.color.bottom_grey));
        tvHeadTitle.setText(name);
    }

    /**
     * {@inheritDoc}
     */
    public void setTitle(int resId) {
        tvHeadTitle.setTextColor(getResources().getColor(R.color.bottom_grey));
        tvHeadTitle.setText(resId);
    }

    /**
     * <设置右侧显示的文本颜色> <功能详细描述>
     *
     * @param resId 文本颜色color的id 返回类型:void
     */
    public void setRightColor(int resId) {
        tvHeadRight.setTextColor(resId);
    }

    /**
     * <设置页面背景> <功能详细描述>
     *
     * @param drawableID 返 回 类 型：void
     */
    public void setBackBtnBackground(int drawableID) {
        layoutBackContainer.setBackgroundResource(drawableID);
    }

    /**
     * <设置右侧按钮文本内容> <功能详细描述>
     *
     * @param resId 返回类型:void
     */
    public void setRightButtonText(String resId) {
        if (tvHeadRight != null) {
            tvHeadRight.setText(resId);
        }
    }

    /**
     * <设置右侧按钮文本内容> <功能详细描述>
     *
     * @param resId 返 回 类 型：void
     */
    public void setRightButtonText(int resId) {
        if (tvHeadRight != null) {
            tvHeadRight.setText(getResources().getString(resId));
        }
    }

    /**
     * 右击事件
     */
    protected void onRightClick() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back_container:
                onBackClick();
                break;
            case R.id.frame_head_right_container:
                onRightClick();
                break;
        }
    }

    public void showCommonProcessDialog(String tips) {
        if (mLoadingProgressDialog == null) {
            mLoadingProgressDialog = new LoadingProgressDialog(this, "");
        }
        mLoadingProgressDialog.setPressText(tips);
        mLoadingProgressDialog.show();
    }

    public void setCommonProcessDialogText(String tips) {
        if (mLoadingProgressDialog != null) {
            mLoadingProgressDialog.setPressText(tips);
        }
    }

    /**
     * 关闭对话框
     */
    public void dismissCommonPostingDialog() {
        if (mLoadingProgressDialog == null || !mLoadingProgressDialog.isShowing()) {
            return;
        }
        try {
            mLoadingProgressDialog.dismiss();
            mLoadingProgressDialog = null;
        } catch (Exception e) {

        }
    }
}