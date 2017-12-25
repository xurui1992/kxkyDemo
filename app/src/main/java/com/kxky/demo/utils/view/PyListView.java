package com.kxky.demo.utils.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kxky.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/11/10 0010.
 */

public class PyListView extends LinearLayout implements View.OnClickListener {
    private char[] listdata = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z'};
    private List<LinearLayout> childViewList = new ArrayList<>();
    private List<TextView> childTextList = new ArrayList<>();

    public PyListView(Context context) {
        this(context, null);
    }

    public PyListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PyListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(VERTICAL);
        for (char item : listdata) {
            LinearLayout childView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.pylist_view_item_layout, this, false);
            TextView textView = (TextView) childView.findViewById(R.id.name);
            textView.setText(String.valueOf(item));
            childView.setOnClickListener(this);
            addView(childView);
            childViewList.add(childView);
            childTextList.add(textView);
        }
    }

    public void setSelectedItem(String text) {
        for (TextView textView : childTextList) {
            if (textView.getText().toString().toLowerCase().equals(text.toLowerCase())) {
                textView.setTextColor(getContext().getResources().getColor(R.color.white));
                textView.setBackground(getContext().getResources().getDrawable(R.drawable.py_cicle_bg));
            } else {
                textView.setTextColor(getContext().getResources().getColor(R.color.bottom_grey));
                textView.setBackgroundColor(getContext().getResources().getColor(R.color.transparent));
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.childcontent) {
            TextView textView = (TextView) ((LinearLayout) v).findViewById(R.id.name);
            if (onItemClickLisnter != null) {
                onItemClickLisnter.onItemClick(textView.getText().toString());
            }
        }
    }

    private OnItemClickLisnter onItemClickLisnter;

    public void setOnItemClickLisnter(OnItemClickLisnter lisnter) {
        this.onItemClickLisnter = lisnter;
    }

    public interface OnItemClickLisnter {
        public void onItemClick(String py);
    }
}
