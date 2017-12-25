package com.kxky.demo.utils.view;

import android.content.Context;
import android.graphics.Color;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.kxky.demo.R;


import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by John on 2017/6/29.
 */

public class AppTimePicker {
    private Context mContext;
    private TimePickerView pvCustomTime;
    private CardView mContent;
    public AppTimePicker(Context context, Calendar selectedDate, final OnTimeSelectListener listener){
        mContext = context;
        pvCustomTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                listener.onTimeSelect(date,v);
            }
        })
                .setDate(selectedDate)
                .setBackgroundId(0x80ffffff)
                .setLayoutRes(R.layout.datepicker_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        mContent = (CardView)v.findViewById(R.id.content_view);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setOutSideCancelable(false)
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)
                .setDividerColor(Color.WHITE)
                .build();
        pvCustomTime.setDialogOutSideCancelable(false);
    }
    public AppTimePicker(Context context, Calendar selectedDate, final OnTimeSelectListener listener, boolean alltrue){
        mContext = context;
        pvCustomTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                listener.onTimeSelect(date,v);
            }
        })
                .setDate(selectedDate)
                .setBackgroundId(0x80ffffff)
                .setLayoutRes(R.layout.datepicker_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        mContent = (CardView)v.findViewById(R.id.content_view);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setOutSideCancelable(false)
                .setType(new boolean[]{true, true, true, true, true, true})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)
                .setDividerColor(Color.WHITE)
                .build();
        pvCustomTime.setDialogOutSideCancelable(false);
    }
    public AppTimePicker(Context context, Calendar selectedDate, boolean isyearshow, boolean isMonthshow,
                         boolean isDayshow, boolean isHourshow, boolean isMinuteshow, boolean issencondshow,
                         boolean isLimitData, Calendar fromDate, Calendar toDate , final OnTimeSelectListener listener){
        mContext = context;

        TimePickerView.Builder builder = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                listener.onTimeSelect(date,v);
            }
        })
                .setDate(selectedDate)
                .setOutSideCancelable(false)
                .setBackgroundId(0x80ffffff)
                .setLayoutRes(R.layout.datepicker_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        mContent = (CardView)v.findViewById(R.id.content_view);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{isyearshow, isMonthshow, isDayshow, isHourshow, isMinuteshow, issencondshow})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)
                .setDividerColor(Color.WHITE);
        if(isLimitData){
            builder.setRangDate(fromDate,toDate);
        }
        pvCustomTime = builder.build();
        pvCustomTime.setDialogOutSideCancelable(false);
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public AppTimePicker(Context context, JSONObject jsonObject, final OnTimeSelectListener listener){
        mContext = context;
        Calendar selectedDate = Calendar.getInstance();
        Calendar fromDate = Calendar.getInstance();
        Calendar toDate = Calendar.getInstance();
        boolean isyearshow = true;
        boolean isDayshow = true;
        boolean isMonthshow = true;
        boolean isHourshow = true;
        boolean isMinuteshow = true;
        boolean issencondshow = true;
        boolean isLimitData = true;
        try {
            JSONObject params = jsonObject.getJSONObject("params");
            if(! params.isNull("current")){
                Date date = simpleDateFormat.parse(params.getString("current"));
                selectedDate.setTime(date);
                Log.d("JSBAPI_datepicker","ACTimePicker selectedDate:"+selectedDate.get(Calendar.HOUR_OF_DAY));
            }
//            if(! params.isNull("isLimitData")){
//                isLimitData = params.getBoolean("isLimitData");
//            }
//            if(! params.isNull("from")){
//                Date date = simpleDateFormat.parse(params.getString("from"));
//                fromDate.setTime(date);
//                Log.d("JSBAPI_datepicker","ACTimePicker fromDate:"+selectedDate.get(Calendar.HOUR_OF_DAY));
//            }
//            if(! params.isNull("to")){
//                Date date = simpleDateFormat.parse(params.getString("to"));
//                toDate.setTime(date);
//                Log.d("JSBAPI_datepicker","ACTimePicker toDate:"+selectedDate.get(Calendar.HOUR_OF_DAY));
//            }
//            if(! params.isNull("yearShow")){
//                isyearshow = params.getBoolean("yearShow");
//            }
//            if(! params.isNull("monthShow")){
//                isMonthshow = params.getBoolean("monthShow");
//            }
//            if(! params.isNull("dayShow")){
//                isDayshow = params.getBoolean("dayShow");
//            }
//            if(! params.isNull("hourShow")){
//                isHourshow = params.getBoolean("hourShow");
//            }
//            if(! params.isNull("minShow")){
//                isMinuteshow = params.getBoolean("minShow");
//            }
//            if(! params.isNull("secondShow")){
//                issencondshow = params.getBoolean("secondShow");
//            }
        } catch (Exception e) {
            Log.d("JSBAPI_datepicker","ACTimePicker e:"+e.toString());
        }

        TimePickerView.Builder builder = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                listener.onTimeSelect(date,v);
            }
        })
                .setDate(selectedDate)
                .setOutSideCancelable(false)
                .setBackgroundId(0x80ffffff)
                .setLayoutRes(R.layout.datepicker_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        mContent = (CardView)v.findViewById(R.id.content_view);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{isyearshow, isMonthshow, isDayshow, isHourshow, isMinuteshow, issencondshow})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)
                .setDividerColor(Color.WHITE);
//        if(isLimitData){
//            builder.setRangDate(fromDate,toDate);
//        }
        pvCustomTime = builder.build();
        pvCustomTime.setDialogOutSideCancelable(false);
        Log.d("JSBAPI_datepicker","ACTimePicker 6:");
    }

    public AppTimePicker(Context context, Date defaultDate, Date startDate, Date endDate , final OnTimeSelectListener listener){
        mContext = context;
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTime(defaultDate);
        Calendar fromDate = Calendar.getInstance();
        fromDate.setTime(startDate);
        Calendar toDate = Calendar.getInstance();
        toDate.setTime(endDate);

        pvCustomTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                listener.onTimeSelect(date,v);
            }
        })
                .setRangDate(fromDate,toDate)
                .setDate(selectedDate)
                .setOutSideCancelable(false)
                .setBackgroundId(0x80ffffff)
                .setLayoutRes(R.layout.datepicker_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        mContent = (CardView)v.findViewById(R.id.content_view);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)
                .setDividerColor(Color.WHITE)
                .build();
        pvCustomTime.setDialogOutSideCancelable(false);
        Log.d("JSBAPI_datepicker","ACTimePicker 6:");
    }
    public AppTimePicker(Context context, Date defaultDate, Date startDate, Date endDate , boolean[] showitme, final OnTimeSelectListener listener){
        mContext = context;
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTime(defaultDate);
        Calendar fromDate = Calendar.getInstance();
        fromDate.setTime(startDate);
        Calendar toDate = Calendar.getInstance();
        toDate.setTime(endDate);

        pvCustomTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                listener.onTimeSelect(date,v);
            }
        })
                .setRangDate(fromDate,toDate)
                .setDate(selectedDate)
                .setBackgroundId(0x80ffffff)
                .setLayoutRes(R.layout.datepicker_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        mContent = (CardView)v.findViewById(R.id.content_view);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(showitme)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)
                .setDividerColor(Color.WHITE)
                .setOutSideCancelable(false)
                .build();
        pvCustomTime.setDialogOutSideCancelable(false);
        Log.d("JSBAPI_datepicker","ACTimePicker 6:");
    }
    public AppTimePicker(Context context, String all, final OnTimeSelectListener listener) {
        Calendar selectedDate = Calendar.getInstance();
        mContext = context;
        pvCustomTime = new TimePickerView.Builder(mContext, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                listener.onTimeSelect(date, v);
            }
        })
                .setDate(selectedDate)
                .setBackgroundId(0x80ffffff)
                .setLayoutRes(R.layout.datepicker_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        mContent = (CardView) v.findViewById(R.id.content_view);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setOutSideCancelable(false)
                .setType(new boolean[]{true, true, true, true, true, true})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)
                .setDividerColor(Color.WHITE)
                .build();
        pvCustomTime.setDialogOutSideCancelable(false);
    }


    public void setContentWidth(int width) {
        if (pvCustomTime != null) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mContent.getLayoutParams();
            params.width = width;
            mContent.setLayoutParams(params);
        }
    }

    public void show() {
        if (pvCustomTime != null) {
            pvCustomTime.show();
        }
    }

    public interface OnTimeSelectListener{
        public void onTimeSelect(Date date, View v);
    }
}
