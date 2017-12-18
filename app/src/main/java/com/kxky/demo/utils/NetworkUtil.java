package com.kxky.demo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by kxky on 2017/12/14.
 */

public class NetworkUtil {
    public static final int NETWORN_NONE = 0;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 2;

    public static int getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mMobileNetworkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mMobileNetworkInfo != null && mMobileNetworkInfo.isConnected()) {
            return NETWORN_WIFI;
        }
        mMobileNetworkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mMobileNetworkInfo != null && mMobileNetworkInfo.isAvailable() && mMobileNetworkInfo.isConnected()) {
            return NETWORN_MOBILE;
        }
        return NETWORN_NONE;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
         //   Toast.makeText(context, context.getString(R.string.comm_tip_network_error));
            Toast.makeText(context, "没有可用的网络",Toast.LENGTH_LONG);
            return false;
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info == null) {
           //     ToastUtil.showMessage(context, context.getString(R.string.comm_tip_network_error));
                Toast.makeText(context, "没有可用的网络",Toast.LENGTH_LONG);
                return false;
            } else {
                if (info.isAvailable()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNetworkAvailableNoTip(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info == null) {
                return false;
            } else {
                if (info.isAvailable()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getNetworkTypeName(Context context){
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        return info.getTypeName();
    }

}
