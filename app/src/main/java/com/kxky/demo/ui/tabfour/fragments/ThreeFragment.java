package com.kxky.demo.ui.tabfour.fragments;

import android.os.Bundle;

import com.kxky.demo.R;
import com.kxky.demo.ui.BaseFragment;


/**
 * Created by kxky on 2017/12/13.
 */

public class ThreeFragment extends BaseFragment {


    @Override
    public int getContentViewId() {
        return R.layout.fragment_tab_one;
    }

    @Override
    public void onViewInitialized(Bundle savedInstanceState) {
        setTitleTxt("one");
    }
}
