package com.newsing.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class ActivityTranslater {
    public static void GoTo(Context context, Class name, Bundle bundle){
        Intent i = new Intent(context,name);
        if(bundle != null)
            i.putExtras(bundle);
        context.startActivity(i);
    }
}
