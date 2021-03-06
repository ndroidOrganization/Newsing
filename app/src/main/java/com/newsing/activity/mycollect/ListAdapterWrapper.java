package com.newsing.activity.mycollect;

import android.support.v4.app.FragmentActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsing.R;
import com.newsing.activity.mian.adapter.ListAdapter;
import com.newsing.basic.BaseActivity;

/**
 * Created by Qzhu on 2017/7/28.
 */

public class ListAdapterWrapper extends ListAdapter {
    public ListAdapterWrapper(FragmentActivity contextThemeWrapper) {
        super(contextThemeWrapper);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.collections_recycler_item,parent,false);
        item.findViewById(R.id.news_template_divider).setVisibility(View.GONE);
        return new ViewHolder(item);
    }
}
