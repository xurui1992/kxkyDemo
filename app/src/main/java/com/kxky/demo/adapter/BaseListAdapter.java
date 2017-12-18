package com.kxky.demo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by John on 2017/6/12.
 */

public abstract class BaseListAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;
    private T mSelectedData;
    protected Map<Integer, BaseListIitemHolder> mList = new HashMap<>();

    public BaseListAdapter(Context context) {
        mContext = context;
    }

    public BaseListAdapter(Context context, List<T> data) {
        mContext = context;
        mData = data;
    }

    public Context getContext() {
        return mContext;
    }

    public void setSelectedItem(T selectedData) {
        Log.d("BaseListAdapter", "setSelectItem:" + selectedData);
        mSelectedData = selectedData;
    }

    public T getSelectedItem() {
        return mSelectedData;
    }
    public List<T> getData(){
        return mData;
    }
    public void setData(List<T> data) {
        mData = data;
    }
    public void appendData(List<T> data){
        mData.addAll(data);
    }
    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseListIitemHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(mContext).inflate(getItemLayoutId(), parent,false);
            viewHolder = BaseListIitemHolder.createViewHolder(mContext, convertView, position);
        } else {
            viewHolder = (BaseListIitemHolder) convertView.getTag();
        }
        mList.put(position, viewHolder);
        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    abstract public int getItemLayoutId();

    abstract public void onBindViewHolder(BaseListIitemHolder holder, int position);

}
