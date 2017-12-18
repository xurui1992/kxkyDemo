package com.kxky.demo.ui.tabtwo.adapters;

import android.content.Context;
import android.view.View;


import com.kxky.demo.R;
import com.kxky.demo.adapter.BaseListAdapter;
import com.kxky.demo.adapter.BaseListIitemHolder;
import com.kxky.demo.bean.Title;
import com.kxky.demo.utils.view.OnItemClickListener;

import java.util.List;

/**
 * Created by John on 2017/11/15 0015.
 */

public class TestListAdapter extends BaseListAdapter<Title> {
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public TestListAdapter(Context context, OnItemClickListener listener) {
        super(context);
        this.mContext = context;
        this.mOnItemClickListener = listener;
    }

    @Override
    public void setData(List<Title> data) {
        super.setData(data);
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_title;
    }

    @Override
    public void onBindViewHolder(final BaseListIitemHolder holder, final int position) {
        final Title title = getItem(position);

        holder.setText(R.id.title, title.getName());
        holder.setText(R.id.age, title.getAge());

        holder.setOnClickListener(R.id.item_content, new
                View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(v, position);
                        }
                    }
                });

    }

}
