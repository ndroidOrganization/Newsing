package com.newsing.fragment.sports;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsing.R;
import com.newsing.basic.BaseFragment;
import com.newsing.fragment.topnews.ListAdapter;

/**
 * Created by Administrator on 2017/7/22 0022.
 */

public class SportsFragment extends BaseFragment {

    private final static String TABNAME = "体育";

    @Override
    public String getTabName(){
        return TABNAME;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_sports,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.sports_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        //async();
        return view;
    }
}
