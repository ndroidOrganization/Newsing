package com.newsing.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;

import com.newsing.R;

/**
 * Created by qzzhu on 16-9-21.
 */

public class ColorUtils {

//    Vibrant(充满活力的)
//
//    Vibrant dark(充满活力的黑)
//
//    Vibrant light(充满活力的亮)
//
//    Muted(柔和的)
//
//    Muted dark(柔和的黑)
//
//    Muted lighr(柔和的亮)

    public static void getBitmapColor(Context context,Palette.PaletteAsyncListener listener){
        new Palette.Builder(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_test)).generate(listener);
    }
}
