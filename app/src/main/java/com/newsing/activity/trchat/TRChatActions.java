package com.newsing.activity.trchat;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Qzhu on 2017/7/28.
 */

public interface TRChatActions {

    void initialTR();

    void requestTR(String args);

    void addData(boolean request, String content);

    void initRecyclerView(RecyclerView recyclerView);
}
