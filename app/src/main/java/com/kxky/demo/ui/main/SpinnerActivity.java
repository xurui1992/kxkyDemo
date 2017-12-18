package com.kxky.demo.ui.main;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.kxky.demo.R;
import com.kxky.demo.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kxky on 2017/12/15.
 */

public class SpinnerActivity extends BaseActivity {
    private AppCompatSpinner titleSelectSpinner;
    /**
     * title 集合
     */
    protected static List<String> titlieList = new ArrayList<>();

    /***
     * title 适配器
     */
    protected ArrayAdapter<String> tradeTitleAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_spinner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("测试");
        titleSelectSpinner = (AppCompatSpinner) findViewById(R.id.titleSelectSpinner);

        titlieList.clear();//预防数据重复
        titlieList.add("一");
        titlieList.add("二");
        titlieList.add("三");
        titlieList.add("四");
        titlieList.add("五");
        if (titlieList.size() != 0 && titlieList != null) {
            tradeTitleAdapter = new ArrayAdapter<String>(mContext, R.layout.simple_spinner_item, titlieList);
            tradeTitleAdapter.setDropDownViewResource(R.layout.simple_spinner_item);
            titleSelectSpinner.setAdapter(tradeTitleAdapter);
            titleSelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    //   showToast(marketName);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }

    }
}