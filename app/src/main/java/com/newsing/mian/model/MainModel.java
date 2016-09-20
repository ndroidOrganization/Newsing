package com.newsing.mian.model;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.newsing.basic.BaseFragment;
import com.newsing.basic.BaseInterface;
import com.newsing.mian.adapter.ViewPagerAdapyer;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class MainModel<T> {

    private ViewPagerAdapyer adapter = null;
    private BaseInterface baseInterface = null;
    private WeakReference<AppCompatActivity> activity = null;

    public MainModel(BaseInterface<T> baseInterface){
        this.baseInterface = baseInterface;
    }

    public void setAdapter(AppCompatActivity contextThemeWrapper, BaseFragment... fragment){
        if(adapter == null)
        {
            List<BaseFragment> datas = new ArrayList<>();
            Collections.addAll(datas,fragment);
            this.adapter = new ViewPagerAdapyer(contextThemeWrapper.getSupportFragmentManager(),datas);
            activity = new WeakReference<>(contextThemeWrapper);
        }
        else{
            //baseInterface.onError();
        }
    }
    public ViewPagerAdapyer getAdapter() {
        return adapter;
    }
}
