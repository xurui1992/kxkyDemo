package com.kxky.demo.ui.tabthree;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import com.kxky.demo.R;
import com.kxky.demo.ui.BaseFragment;
import com.kxky.demo.utils.StringUtils;

import java.util.Calendar;


/**
 * Created by kxky on 2017/12/13.
 */

public class TabThreeFragment extends BaseFragment {
    private TextView tv_start_time;
    private TextView tv_end_time;
    private int mYear;
    private int mMonth;
    private int mDay;
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public int getContentViewId() {
        return R.layout.fragment_tab_three;
    }

    @Override
    public void onViewInitialized(Bundle savedInstanceState) {
        initDate();
        initView();
    }

    private void initView() {
        tv_start_time= (TextView) getActivity().findViewById(R.id.tv_start_time);
        tv_end_time= (TextView) getActivity().findViewById(R.id.tv_end_time);
        disPlay(tv_start_time);
        disPlay(tv_end_time);
        tv_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        tv_start_time.setText(StringUtils.formatDate(year, month, day,DATE_FORMAT));
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(getActivity(),0,listener,mYear,mMonth,mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
            }
        });
    }

    private void initDate() {
        final Calendar mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public void disPlay(TextView tv) {
        tv.setText(StringUtils.formatDate(mYear, mMonth, mDay, DATE_FORMAT));
    }

}