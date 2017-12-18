package com.kxky.demo.ui.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kxky.demo.R;
import com.kxky.demo.ui.BaseActivity;
import com.kxky.demo.ui.tabfive.TabFiveFragment;
import com.kxky.demo.ui.tabfour.TabFourFragment;
import com.kxky.demo.ui.tabone.TabOneFragment;
import com.kxky.demo.ui.tabthree.TabThreeFragment;
import com.kxky.demo.ui.tabtwo.TabTwoFragment;
import com.kxky.demo.utils.DoubleClickExitHelper;
import com.kxky.demo.utils.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kxky on 2017/12/15.
 */

public class MainActivity extends BaseActivity {
    private DoubleClickExitHelper mDoubleClickExit;
    private TabLayout mTabHostLayout;
    private MyViewPager mViewContentPager;
    private ConsultationAdapter adapter;
    private String[] titles = new String[]{"one", "two", "three", "four", "five"};
    /**
     * ViewPage选项卡页面集合
     */
    private List<Fragment> mFragments = new ArrayList<>();
    /**
     * Tab标题集合
     */
    private List<String> mTitles = new ArrayList<>();
    /**
     * 图片数组
     */
    private int[] mImgs = new int[]{R.drawable.tab_one_selector, R.drawable.tab_two_selector,
            R.drawable.tab_three_selector, R.drawable.tab_four_selector, R.drawable.tab_five_selector};

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHeadContanier(View.GONE);
        initView();
        initData();
    }

    private void initView() {
        mViewContentPager = (MyViewPager) findViewById(R.id.viewContentpager);
        mTabHostLayout = (TabLayout) findViewById(R.id.tabHostlayout);
        mDoubleClickExit = new DoubleClickExitHelper(this);     //DoubleClick Listener
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < titles.length; i++) {
            mTitles.add(titles[i]);
        }
        mFragments.add(new TabOneFragment());
        mFragments.add(new TabTwoFragment());
        mFragments.add(new TabThreeFragment());
        mFragments.add(new TabFourFragment());
        mFragments.add(new TabFiveFragment());

        adapter = new ConsultationAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewContentPager.setAdapter(adapter);
        mViewContentPager.setCurrentItem(0);
        mViewContentPager.setOffscreenPageLimit(4);
        mTabHostLayout.setupWithViewPager(mViewContentPager);//将导航栏和viewpager进行关联

        for (int i = 0; i < mTitles.size(); i++) {
            TabLayout.Tab itemTab = mTabHostLayout.getTabAt(i);
            if (itemTab != null) {
                //设置自定义的标题
                itemTab.setCustomView(R.layout.item_tab);
                TextView textView = (TextView) itemTab.getCustomView().findViewById(R.id.tv_name);
                textView.setText(mTitles.get(i));
                ImageView imageView = (ImageView) itemTab.getCustomView().findViewById(R.id.iv_img);
                imageView.setImageResource(mImgs[i]);
                /**
                 * 此处可进行导航栏小圆点的判断显示
                 */
              /*  TextView unread = (TextView) itemTab.getCustomView().findViewById(R.id.tab_message_count);
                if(itemTab.getText().equals("one")){
                    unread.setVisibility(View.VISIBLE);
                }
                if(itemTab.getText().equals("two")){
                    unread.setVisibility(View.VISIBLE);
                }*/
            }
        }

        mTabHostLayout.getTabAt(0).getCustomView().setSelected(true);
        mTabHostLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewContentPager.setCurrentItem(tab.getPosition());
                onSelected(mTitles, tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                onUnSelected(mTitles, tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 未选中状态
     *
     * @param mTitles
     * @param tab
     */
    private void onUnSelected(List<String> mTitles, TabLayout.Tab tab) {
        View view = tab.getCustomView();
        TextView name = (TextView) view.findViewById(R.id.tv_name);
        ImageView img = (ImageView) view.findViewById(R.id.iv_img);
        for (int i = 0; i < mTitles.size(); i++) {
            String names = mTitles.get(i);
            if (name.getText().toString().equals(names)) {
                name.setSelected(false);
                img.setImageResource(mImgs[i]);
                img.setSelected(false);
            }
        }
    }

    /**
     * 选中状态
     *
     * @param mTitles
     * @param tab
     */
    private void onSelected(List<String> mTitles, TabLayout.Tab tab) {
        // tab.getPosition();
        View view = tab.getCustomView();
        TextView name = (TextView) view.findViewById(R.id.tv_name);
        ImageView img = (ImageView) view.findViewById(R.id.iv_img);
        for (int i = 0; i < mTitles.size(); i++) {
            String names = mTitles.get(i);
            if (name.getText().toString().equals(names)) {
                name.setSelected(true);
                img.setImageResource(mImgs[i]);
                img.setSelected(true);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return mDoubleClickExit.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode,event);
    }
}
