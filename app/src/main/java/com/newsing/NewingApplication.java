package com.newsing;

import android.app.Application;

import com.newsing.utils.FileUtils;

/**
 * Created by qzzhu on 16-9-21.
 */

public class NewingApplication extends Application {

    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        FileUtils.checkFileDir(this);
    }

    public static Application getInstance(){
        synchronized (NewingApplication.class)
        {
            return mApplication;
        }
    }

}
