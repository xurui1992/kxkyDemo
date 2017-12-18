package com.kxky.demo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.kxky.demo.imageloader.LoadImage;


/**
 * Created by John on 2017/6/12.
 */

public class BaseListIitemHolder {
    private SparseArray<View> mViews;
    private Context mContext;
    public int mPosition;
    private View mConvertView;

    public BaseListIitemHolder(Context context, View convertView, int position) {
        mViews = new SparseArray<>();
        mContext = context;
        mConvertView = convertView;
        mPosition = position;
    }

    /**
     * 获取viewHolder
     */
    public static BaseListIitemHolder createViewHolder(Context context, View convertView, int position) {
        return new BaseListIitemHolder(context, convertView, position);
    }

    /**
     * get view
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * set text
     */
    public BaseListIitemHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     *
     */
    public BaseListIitemHolder setText(int viewId, int stringRid) {
        TextView tv = getView(viewId);
        tv.setText(mContext.getString(stringRid));
        return this;
    }

    /**
     * set image res
     */
    public BaseListIitemHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    public BaseListIitemHolder loadImage(int viewId, String imageUrl) {
        ImageView iv = getView(viewId);
        LoadImage.loadImage(imageUrl, iv);
        return this;
    }

    public BaseListIitemHolder loadcircleAvatar(int viewId, String imageUrl) {
        ImageView iv = getView(viewId);
        LoadImage.loadcircleAvatar(imageUrl, iv);
        return this;
    }

    /**
     * set image bitmap
     */
    public BaseListIitemHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置背景
     */
    public BaseListIitemHolder setBackgroundColor(int viewId, int colorRid) {
        View view = getView(viewId);
        view.setBackgroundColor(mContext.getResources().getColor(colorRid));
        return this;
    }

    public BaseListIitemHolder setBackgroundDrawable(int viewId, int drawableid) {
        View view = getView(viewId);
        view.setBackground(mContext.getResources().getDrawable(drawableid));
        return this;
    }

    public BaseListIitemHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public BaseListIitemHolder setOnClickListener(int viewId, View.OnClickListener onClickListener) {
        View view = getView(viewId);
        view.setOnClickListener(onClickListener);
        return this;
    }

    public BaseListIitemHolder setVisibility(int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    public BaseListIitemHolder setTextColor(int viewId, @ColorInt int color) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(color);
        }
        return this;
    }
    public BaseListIitemHolder setCheck(int viewId,boolean isCheck){
        View view = getView(viewId);
        if(view instanceof CheckBox){
            ((CheckBox)view).setChecked(isCheck);
        }
        return this;
    }
    public TextView getTextView(int viewId) {
        View view = getView(viewId);
        if (view instanceof TextView) {
            return (TextView) view;
        }
        return null;
    }
}
