package com.newsing.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * Created by qzzhu on 16-9-21.
 */

public class FileUtils {
    public final static String PrivateDir_Beauty = "beauty";

    public static void checkFileDir(Context context){
        File privateDir = context.getFilesDir();
        File beauty = new File(privateDir,PrivateDir_Beauty);
        if(!beauty.exists())
        {
            boolean success = beauty.mkdir();
            if(!success)
                Log.w("beauty dir create","failed");
        }
    }

    public static File getBeautyFileDir(Context context){
        return new File(context.getFilesDir(),PrivateDir_Beauty);
    }
}
