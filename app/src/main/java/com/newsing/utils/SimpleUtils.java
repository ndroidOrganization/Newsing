package com.newsing.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

class SimpleUtils {
    public static boolean netWorkAvailable(Context t){
        ConnectivityManager cm=(ConnectivityManager) t.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null;
    }
}
