package com.newsing.fragment.beauty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsing.R;
import com.newsing.basic.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class BeautyFragment extends BaseFragment {

    private final static int COLUMNCOUNT = 2;

    List<ItemModel> datats = new ArrayList<>();

    public BeautyFragment(){
        //request picture


        //test
        for(int i = 0;i<18;i++)
        {
            datats.add(new ItemModel());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.beauty_recycle);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(COLUMNCOUNT,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new RecycleItemAdapter(datats));
        return view;
    }

    public String getTabName(){
        return "Beauty";
    }
}
