package com.kxky.demo.ui.tabone;

import android.os.Bundle;

import com.kxky.demo.R;
import com.kxky.demo.imageloader.GlideImageLoader;
import com.kxky.demo.ui.BaseFragment;
import com.kxky.demo.utils.CMLog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kxky on 2017/12/13.
 */

public class TabOneFragment extends BaseFragment {
    private Banner banner;
    private List<Object> images;
    private List<String> titles;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_tab_one;
    }

    @Override
    public void onViewInitialized(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        banner = (Banner) getActivity().findViewById(R.id.banner);
        initBanner();
    }

    private void initBanner() {
        if (images == null) {
            images = new ArrayList<>();
        }

        images.clear();
        images.add(R.drawable.guide_one);
        images.add(R.drawable.guide_two);
        images.add(R.drawable.guide_three);

        if (titles == null) {
            titles = new ArrayList<>();
        }
        titles.add("one");
        titles.add("two");
        titles.add("three");

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
        banner.setBannerTitles(titles);//设置标题源

        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.NOT_INDICATOR    都没有
        //2. Banner.CIRCLE_INDICATOR   显示圆形指示器
        //3.  Banner.NUM_INDICATOR   显示数字指示器
        //4. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //5,Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题，分为上下行
        //6,Banner.CIRCLE_INDICATOR_TITLE_INSIDE  显示圆形指示器和标题，同一行
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//设置圆形指示器与标题

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        banner.setIndicatorGravity(BannerConfig.RIGHT);//设置指示器位置

        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(false);

        //设置轮播图片间隔时间（不设置默认为2000）
        banner.setDelayTime(5000);

        //所有设置参数方法都放在此方法之前执行
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);//设置图片源

        //设置点击事件，下标是从1开始
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                CMLog.e("TAG", "..." + titles.get(position));
                CMLog.e("TAG", "...");
            }
        });

        banner.start();
    }


}
