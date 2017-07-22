package com.newsing.mian.model;

import android.support.v7.app.AppCompatActivity;

import com.newsing.basic.BaseFragment;
import com.newsing.basic.BaseInterface;
import com.newsing.mian.adapter.ViewPagerAdapyer;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class MainModel<T> {

    private ViewPagerAdapyer adapter = null;
    private BaseInterface baseInterface = null;
    private WeakReference<AppCompatActivity> activity = null;

    private ArrayList<BaseFragment> pagers;

    public MainModel(BaseInterface<T> baseInterface){
        this.baseInterface = baseInterface;
    }

    public void setAdapter(AppCompatActivity contextThemeWrapper, BaseFragment... fragment){
        if(adapter == null)
        {
            pagers = new ArrayList<>();
            Collections.addAll(pagers,fragment);
            this.adapter = new ViewPagerAdapyer(contextThemeWrapper.getSupportFragmentManager(),pagers);
            activity = new WeakReference<>(contextThemeWrapper);
        }
        else{
            //baseInterface.onError();
        }
    }

    public void notifyRefresh(int position){
        if(pagers != null)
            pagers.get(position).refresh();
    }

    public ViewPagerAdapyer getAdapter() {
        return adapter;
    }
}
