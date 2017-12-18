package com.kxky.demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kxky.demo.R;
import com.kxky.demo.utils.dialog.LoadingProgressDialog;


/**
 * Created by kxky on 2017/12/15.
 * 所有fragment都会继承此基类
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener
{

    private static final String TAG = "BaseFragment";

    /**
     * 布局view
     */
    protected View rootView;

    /**
     * 上下文
     */
    protected Context mContext;

    /**
     * 头部bar
     */
    protected RelativeLayout layout_head_container;

    /**
     * 头部左边标签布局
     */
    protected LinearLayout layout_left_container;

    /**
     * 头部左边标签显示的控件
     */
    protected TextView tv_head_left_back;

    /**
     * 头部title
     */
    protected TextView tv_head_title;

    /**
     * 头部右边标签布局
     */
    protected FrameLayout layout_right_container;

    /**
     * 头部左边标签显示的控件
     */
    protected TextView tv_head_right;

    /**
     * 全局加载框
     */
    protected LoadingProgressDialog mLoadingProgressDialog;;

    /**
     * 下拉控件
     */
    protected AppCompatSpinner titleSelectSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getContentViewId(), null);
        if (view != null)
        {
            layout_head_container = (RelativeLayout) view.findViewById(R.id.layout_head_container);
            layout_left_container = (LinearLayout) view.findViewById(R.id.layout_left_container);
            tv_head_left_back = (TextView) view.findViewById(R.id.tv_head_left_back);
            tv_head_title = (TextView) view.findViewById(R.id.tv_head_title);
            layout_right_container = (FrameLayout) view.findViewById(R.id.frame_head_right_container);
            tv_head_right = (TextView) view.findViewById(R.id.tv_head_right);
            titleSelectSpinner = (AppCompatSpinner) view.findViewById(R.id.titleSelectSpinner);
            layout_left_container.setOnClickListener(this);
            layout_right_container.setOnClickListener(this);
        }
        return view;
    }



    /**
     * 展示头部容器
     *
     * @param visibility
     */
    protected void showHeadContanier(int visibility)
    {
        if (layout_head_container != null)
        {
            layout_head_container.setVisibility(visibility);
        }
    }
    /**
     * 设置左边标签的背景
     *
     * @param resId
     */
    protected void setLeftBackGround(int resId)
    {
        layout_left_container.setBackgroundResource(resId);
    }

    /**
     * 设置左边控件是否可见
     *
     * @param visibility
     */
    protected void setLefVisibility(int visibility)
    {
        if (layout_left_container != null)
        {
            layout_left_container.setVisibility(visibility);
        }
    }

    /**
     * 设置 头部左边文字
     *
     * @param msg
     */
    protected void setLeftTxt(String msg)
    {
        if (tv_head_left_back != null)
        {
            tv_head_left_back.setText(msg);
        }
    }

    /**
     * 设置 头部左边文字
     *
     * @param resId
     */
    protected void setLeftTxt(int resId)
    {
        if (tv_head_left_back != null)
        {
            tv_head_left_back.setText(resId);
        }
    }

    /**
     * 设置头部 左边背景
     *
     * @param resId
     */
    protected void setLeftBackGroud(int resId)
    {
        if (tv_head_left_back != null)
        {
            tv_head_left_back.setBackgroundResource(resId);
        }
    }

    /**
     * 设置标题 String
     *
     * @param msg
     */
    protected void setTitleTxt(String msg)
    {
        if (tv_head_title != null)
        {
            tv_head_title.setTextColor(getResources().getColor(R.color.tabChoose));
            tv_head_title.setText(msg);
        }
    }

    /**
     * 设置标题是否可见
     *
     * @param visibility
     */
    protected void setTitleVisibility(int visibility)
    {
        if (layout_head_container != null)
        {
            layout_head_container.setVisibility(visibility);
        }
    }

    /**
     * 设置标题 int
     *
     * @param resId
     */
    protected void setTitleTxt(int resId)
    {
        if (tv_head_title != null)
        {
            tv_head_title.setTextColor(getResources().getColor(R.color.tabChoose));
            tv_head_title.setText(resId);
        }
    }

    /**
     * 设置 标题颜色
     *
     * @param rseId
     */
    protected void setBarColor(int rseId)
    {
        if (layout_head_container != null)
        {
            layout_head_container.setBackgroundColor(rseId);
        }
    }

    /**
     * 设置 右边部分的背景
     *
     * @param resId
     */
    protected void setRightBackGround(int resId)
    {
        if (tv_head_right != null)
        {
            tv_head_right.setBackgroundResource(resId);
        }
    }

    /**
     * 设置 右边文字
     *
     * @param msg
     */
    protected void setRightTxt(String msg)
    {
        if (tv_head_right != null)
        {
            tv_head_right.setText(msg);
        }
    }

    /**
     * 设置 右边文字属性
     *
     * @param resId
     */
    protected void setRightTxt(int resId)
    {
        if (tv_head_right != null)
        {
            tv_head_right.setText(resId);
        }
    }

    /**
     * <主要布局id>
     * <功能详细描述>
     *
     * @return 返回类型:int
     */
    public abstract int getContentViewId();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
//        ViewUtils.inject(this, view);
        onViewInitialized(savedInstanceState);
    }

    /**
     * <初始化一些数据>
     * <功能详细描述>
     *
     * @param savedInstanceState 返回类型:void
     */
    public abstract void onViewInitialized(Bundle savedInstanceState);

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.layout_left_container:
                onHeadLeftClick();
                break;
            case R.id.frame_head_right_container:
                onHeadRightClick();
                break;
        }
    }

    /**
     * 右边 点击事件
     */

    protected void onHeadRightClick()
    {

    }

    /**
     * 左边 点击事件
     */
    protected void onHeadLeftClick()
    {

    }

    public void showCommonProcessDialog(String tips) {
        if(mLoadingProgressDialog == null) {
            mLoadingProgressDialog = new LoadingProgressDialog(getActivity(), "");
        }
        mLoadingProgressDialog.setPressText(tips);
        mLoadingProgressDialog.show();
    }
    public void setCommonProcessDialogText(String tips){
        if(mLoadingProgressDialog != null) {
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
        }catch (Exception e){

        }
    }
}
