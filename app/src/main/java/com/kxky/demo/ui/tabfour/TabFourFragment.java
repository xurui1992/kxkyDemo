package com.kxky.demo.ui.tabfour;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.kxky.demo.R;
import com.kxky.demo.ui.BaseFragment;
import com.kxky.demo.ui.tabfour.fragments.FourFragment;
import com.kxky.demo.ui.tabfour.fragments.OneFragment;
import com.kxky.demo.ui.tabfour.fragments.ThreeFragment;
import com.kxky.demo.ui.tabfour.fragments.TwoFragment;
import com.kxky.demo.ui.main.TabListAdapter;
import com.kxky.demo.utils.CMLog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kxky on 2017/12/13.
 */

public class TabFourFragment extends BaseFragment implements View.OnClickListener
{

    private static final String TAG = TabFourFragment.class.getSimpleName();


    private ViewPager mViewPager;

    /**
     * 导航栏
     */
    private TabLayout mTabLayout;

    /**
     * 标题集合
     */
    private List<String> mTitleList = new ArrayList<>();

    /**
     * 视图集合
     */
    private List<Fragment> fragments = new ArrayList<>();

    /**
     * 是否刷新数据
     */
    private boolean isRefresh = false;

    @Override
    public int getContentViewId()
    {
        return R.layout.fragment_tab_four;
    }

    @Override
    public void onViewInitialized(Bundle savedInstanceState)
    {
        initView();
        initData();
    }

    @Override
    public void onDestroy()
    {
        isRefresh = false;
        super.onDestroy();
    }

    @Override
    protected void onHeadLeftClick()
    {
        getActivity().finish();
        super.onHeadLeftClick();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        isRefresh = true;
        CMLog.e(TAG, "--->1_onResume");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        isRefresh = false;
        CMLog.e(TAG, "--->2_onPause");
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        mViewPager = (ViewPager) getActivity().findViewById(R.id.mTradeViewpager);
        mTabLayout = (TabLayout) getActivity().findViewById(R.id.mTradeTabLayout);
    }

    /**
     * 初始化数据
     */
    private void initData()
    {
        mTitleList.add("one");
        mTitleList.add("two");
        mTitleList.add("three");
        mTitleList.add("four");

        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());
        TabListAdapter tabListAdapter = new TabListAdapter(getChildFragmentManager(), fragments, mTitleList);

        //给ViewPager设置适配器
        mViewPager.setAdapter(tabListAdapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setOnTabSelectedListener(onTabSelectedListener);
    }

    /**
     *
     */
    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener()
    {
        @Override
        public void onTabSelected(TabLayout.Tab tab)
        {
            mViewPager.setCurrentItem(tab.getPosition());//联动
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab)
        {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab)
        {

        }
    };
}