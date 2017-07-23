package com.newsing.fragment.fashion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.newsing.R;
import com.newsing.basic.BaseFragment;
import com.newsing.fragment.topnews.ListAdapter;
import com.newsing.utils.ConstValue;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22 0022.
 */

public class FashionFragment extends BaseFragment {

    private final static String TABNAME = "时尚";

    @Override
    public String getTabName(){
        return TABNAME;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_fashion,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fashion_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        async(ConstValue.ALIAPI.FASHION);
        return view;
    }

    @Override
    public void refresh(){
        async(ConstValue.ALIAPI.FASHION);
    }

    public void onRequestComplete(String result) {
        if(!TextUtils.isEmpty(result))
        {
            ConstValue.ALIAPIBEAN bean = JSON.parseObject(result,ConstValue.ALIAPIBEAN.class);
            List<ConstValue.ALIAPIBEANDATAITEM> datats = bean.getResult().getData();
            adapter.swapDatas(datats);
            onRefreshComplete();
        }
    }
}
