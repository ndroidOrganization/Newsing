package com.newsing.fragment.military;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.newsing.R;
import com.newsing.basic.BaseFragment;
import com.newsing.activity.mian.adapter.ListAdapter;
import com.newsing.db.AliApiDataItem;
import com.newsing.utils.ConstValue;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22 0022.
 */

public class MilitaryFragment extends BaseFragment {

    private final static String TABNAME = "军事";

    @Override
    public String getTabName(){
        return TABNAME;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_military,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.military_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.frag_page_fresh);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.YELLOW,Color.BLUE,Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter = new ListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        async(ConstValue.ALIAPI.MILITARY);
        return view;
    }

    @Override
    public void refresh(){
        async(ConstValue.ALIAPI.MILITARY);
    }

    public void onRequestComplete(String result) {
        if(!TextUtils.isEmpty(result))
        {
            ConstValue.ALIAPIBEAN bean = JSON.parseObject(result,ConstValue.ALIAPIBEAN.class);
            List<AliApiDataItem> datats = bean.getResult().getData();
            adapter.swapDatas(datats);
            onRefreshComplete();
        }
    }
}
