package com.newsing.utils;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;

import com.newsing.basic.BaseActivity;

import static com.newsing.activity.webpage.WebActivity.KEY_OF_TARGET_URI;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class ActivityTranslater {

    public final static String KEY_BUNDLE = "EXTRAS_KEY";

    public static void GoTo(FragmentActivity context, Class name, Bundle bundle){
        Intent i = new Intent(context,name);
        if(null != bundle)
            i.putExtra(KEY_BUNDLE,bundle);
        context.startActivity(i);//, ActivityOptions.makeSceneTransitionAnimation(context).toBundle());
    }

//    public static void GoTo(FragmentActivity context, Class name){
//        Intent i = new Intent(context,name);
//        context.startActivity(i, ActivityOptions.makeSceneTransitionAnimation(context).toBundle());
//    }
}
