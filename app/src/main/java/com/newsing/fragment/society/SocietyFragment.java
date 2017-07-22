package com.newsing.fragment.society;

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

public class SocietyFragment extends BaseFragment {

    private final static String TABNAME = "社会";

    @Override
    public String getTabName(){
        return TABNAME;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_society,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.society_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        //async();
        return view;
    }
}
