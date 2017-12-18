package com.kxky.demo.utils.view;

/**
 * Created by kxky on 2017/12/18.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kxky.demo.R;

/**
 * item，带一个 icon 和一行文字
 */
public class ListItem extends FrameLayout {

    private ImageView mIcon;
    private TextView mText;

    public ListItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.common_list_item, this);
        mIcon = (ImageView)findViewById(R.id.icon);
        mText = (TextView) findViewById(R.id.title);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ListItem);
        String title = array.getString(R.styleable.ListItem_itemTitle);
        int icon = array.getResourceId(R.styleable.ListItem_itemIcon, R.drawable.common_btn_soild_gray_bg);

        boolean showBottomLine = array.getBoolean(R.styleable.ListItem_itemShowBottomLine, false);
        boolean showRightArrow = array.getBoolean(R.styleable.ListItem_itemShowRightArrow, true);

        boolean center = array.getBoolean(R.styleable.ListItem_itemCenter, false);
        array.recycle();

        if (title == null) title = "";
        mText.setText(title);
        mIcon.setImageResource(icon);
        if(showRightArrow){
            findViewById(R.id.arrow).setVisibility(VISIBLE);
        }else {
            findViewById(R.id.arrow).setVisibility(GONE);
        }
        if (showBottomLine) {
            View line = findViewById(R.id.bottomLine);
            line.setVisibility(VISIBLE);
        }
        if (center) {
            findViewById(R.id.arrow).setVisibility(GONE);

            LinearLayout.LayoutParams layoutParams;
            layoutParams = (LinearLayout.LayoutParams) mText.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.weight = 0;
            View projectListItem = findViewById(R.id.clickProject);
            if (projectListItem instanceof RelativeLayout) {
                ((RelativeLayout) projectListItem).setGravity(Gravity.CENTER);
            }
        }
    }

    public void setText(String s) {
        if (s == null) {
            return;
        }
        mText.setText(s);
    }

}