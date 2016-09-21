package com.newsing.utils;

import android.os.Process;

/**
 * Created by qzzhu on 16-9-21.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        throwable.printStackTrace();
        Process.killProcess(Process.myPid());
    }
}
