package com.kxky.demo.ui.tabthree;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kxky.demo.R;
import com.kxky.demo.ui.BaseFragment;
import com.kxky.demo.utils.DateUtil;
import com.kxky.demo.utils.StringUtils;
import com.kxky.demo.utils.view.AppTimePicker;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by kxky on 2017/12/13.
 */

public class TabThreeFragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_start_time;
    private TextView tv_end_time;
    private int mYear;
    private int mMonth;
    private int mDay;
    public static final String DATE_YYMMDD = "yyyy-MM-dd";
    public static final String DATE_YYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    private TextView tv_time_one;
    private TextView tv_time_two;
    private TextView tv_time_three;

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
        tv_start_time = (TextView) getActivity().findViewById(R.id.tv_start_time);
        tv_end_time = (TextView) getActivity().findViewById(R.id.tv_end_time);
        disPlay(tv_start_time);
        disPlay(tv_end_time);
        tv_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        tv_start_time.setText(StringUtils.formatDate(year, month, day, DATE_YYMMDD));
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), 0, listener, mYear, mMonth, mDay);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
            }
        });

        tv_time_one = (TextView) getActivity().findViewById(R.id.tv_time_one);
        tv_time_two = (TextView) getActivity().findViewById(R.id.tv_time_two);
        tv_time_three = (TextView) getActivity().findViewById(R.id.tv_time_three);
        tv_time_one.setOnClickListener(this);
        tv_time_two.setOnClickListener(this);
        tv_time_three.setOnClickListener(this);
    }

    private void initDate() {
        final Calendar mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public void disPlay(TextView tv) {
        tv.setText(StringUtils.formatDate(mYear, mMonth, mDay, DATE_YYMMDD));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_time_one:
                Date selectedDate = DateUtil.parseStrToDate("2017-12-12", DATE_YYMMDD);
                Calendar selectCalerdar = Calendar.getInstance();
                selectCalerdar.setTime(selectedDate);
                AppTimePicker acTimePicker = new AppTimePicker(getActivity(),
                        selectCalerdar, new AppTimePicker.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tv_time_one.setText(DateUtil.parseDateToStr(date, DATE_YYMMDD));
                    }
                });
                //      acTimePicker.setContentWidth(getResources().getDimensionPixelSize(R.dimen.space_500));
                acTimePicker.show();
                break;
            case R.id.tv_time_two:
                Date selectedDate2 = DateUtil.parseStrToDate("2017-12-12 12:12:12", DATE_YYMMDD_HHMMSS);
                Calendar selectCalerdar2 = Calendar.getInstance();
                selectCalerdar2.setTime(selectedDate2);
                AppTimePicker acTimePicker2 = new AppTimePicker(getActivity(),
                        selectCalerdar2, new AppTimePicker.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tv_time_two.setText(DateUtil.parseDateToStr(date, DATE_YYMMDD_HHMMSS));
                    }
                }, true);
                acTimePicker2.show();
                break;
            case R.id.tv_time_three:

                Date selectedDate3 = DateUtil.parseStrToDate("2017-12-12 12:12:12", DATE_YYMMDD_HHMMSS);
                Calendar selectCalerdar3 = Calendar.getInstance();
                selectCalerdar3.setTime(selectedDate3);
                AppTimePicker acTimePicker3 = new AppTimePicker(getActivity(),
                        selectCalerdar3,
                        false, false, false, true, true, false,
                        false, selectCalerdar3, selectCalerdar3, new AppTimePicker.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tv_time_three.setText(DateUtil.parseDateToStr(date, DATE_YYMMDD_HHMMSS));
                    }
                });
                acTimePicker3.show();
                break;
            default:
                break;
        }
    }


}