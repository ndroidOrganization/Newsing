package com.newsing.basic;

import android.support.annotation.StringRes;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public interface BaseInterface<T> {
    /**
     *  the work finished normally
     * @param result type defined
     */
    void onComplete(T result);

    /**
     * a error happened and throws a error String resId
     * @param resId
     */
    void onError(@StringRes int resId);
}
