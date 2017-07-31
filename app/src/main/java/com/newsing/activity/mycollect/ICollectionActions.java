package com.newsing.activity.mycollect;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;

import com.newsing.basic.BaseActivity;

/**
 * Created by Qzhu on 2017/7/28.
 */

public interface ICollectionActions {
    void Async();

    void setAdapter(RecyclerView recyclerView, FragmentActivity context);
}
