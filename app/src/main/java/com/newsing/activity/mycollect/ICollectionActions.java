package com.newsing.activity.mycollect;

import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;

/**
 * Created by Qzhu on 2017/7/28.
 */

public interface ICollectionActions {
    void Async();

    void setAdapter(RecyclerView recyclerView, ContextThemeWrapper context);
}
