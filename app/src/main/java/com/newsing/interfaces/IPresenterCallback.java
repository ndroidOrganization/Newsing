package com.newsing.interfaces;

/**
 * Created by Administrator on 2017/7/23 0023.
 */

public interface IPresenterCallback {

    void onRequestComplete(String args);

    void onRequestError(Exception e);
}
