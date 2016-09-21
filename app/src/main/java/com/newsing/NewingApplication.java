package com.newsing;

import android.annotation.SuppressLint;
import android.app.Application;

import com.newsing.utils.CrashHandler;
import com.newsing.utils.FileManager;

/**
 * Created by qzzhu on 16-9-21.
 */
public class NewingApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        FileManager.checkFileDir(this);

        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
    }

    public static Application getInstance(){
        synchronized (NewingApplication.class)
        {
            return mApplication;
        }
    }

}
