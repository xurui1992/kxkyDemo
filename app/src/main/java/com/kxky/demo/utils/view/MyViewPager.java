package com.kxky.demo.utils.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by kxky on 2017/12/14.
 */

public class MyViewPager extends ViewPager
{
    private boolean enabled=false;

    public MyViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    //触摸没有反应就可以了
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (enabled)
        {
            return super.onTouchEvent(event);
        }
        return false;
    }


    @Override
    public void setOnTouchListener(OnTouchListener l)
    {
        super.setOnTouchListener(l);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        if (enabled)
        {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}
