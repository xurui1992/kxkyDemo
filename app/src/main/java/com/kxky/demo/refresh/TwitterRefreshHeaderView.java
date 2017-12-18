package com.kxky.demo.refresh;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;
import com.kxky.demo.R;

/**
 * Created by kxky on 2017/12/13.
 */

public class TwitterRefreshHeaderView extends SwipeRefreshHeaderLayout {

    private ImageView ivArrow;

//    private ImageView ivSuccess;

    private TextView tvRefresh;

    private ProgressBar progressBar;

    private int mHeaderHeight;

    private Animation rotateUp;

    private Animation rotateDown;

    private boolean rotated = false;
    private Context context;
 //   private AnimationDrawable drawable;

    public TwitterRefreshHeaderView(Context context) {
        this(context, null);
    }

    public TwitterRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwitterRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.space_80);
//        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
//        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
      //  drawable = (AnimationDrawable)context.getResources().getDrawable(R.drawable.emloading);
        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        ivArrow.setVisibility(GONE);
//        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
  //      progressBar.setIndeterminateDrawable(drawable);
      //  drawable.start();
    }

    @Override
    public void onRefresh() {
//        ivSuccess.setVisibility(GONE);
//        ivArrow.clearAnimation();
//        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
   //     drawable.start();
        tvRefresh.setText("刷新...");
        tvRefresh.setVisibility(GONE);
    }

    @Override
    public void onPrepare() {
        Log.d("TwitterRefreshHeader", "onPrepare()");
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
//            ivArrow.setVisibility(VISIBLE);
            progressBar.setVisibility(VISIBLE);
      //      drawable.start();
//            ivSuccess.setVisibility(GONE);
            if (y > mHeaderHeight) {
                tvRefresh.setText("松开刷新");
                if (!rotated) {
//                    ivArrow.clearAnimation();
//                    ivArrow.startAnimation(rotateUp);
                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {
//                    ivArrow.clearAnimation();
//                    ivArrow.startAnimation(rotateDown);
                    rotated = false;
                }

                tvRefresh.setText("下拉刷新");
            }
        }
    }

    @Override
    public void onRelease() {
        Log.d("TwitterRefreshHeader", "onRelease()");
    }


    public void onComplete() {
        rotated = false;
//        ivSuccess.setVisibility(VISIBLE);
//        ivArrow.clearAnimation();
//        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
   //     drawable.stop();
        tvRefresh.setText("刷新完成");
    }

    @Override
    public void onReset() {
        rotated = false;
//        ivSuccess.setVisibility(GONE);
//        ivArrow.clearAnimation();
//        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
  //      drawable.stop();
    }

}
