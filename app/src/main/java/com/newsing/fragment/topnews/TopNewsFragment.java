package com.newsing.fragment.topnews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.newsing.R;
import com.newsing.basic.BaseFragment;
import com.newsing.basic.BaseInterface;
import com.newsing.utils.ConstValue;
import com.newsing.utils.NetWorkUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/22 0022.
 */

public class TopNewsFragment extends BaseFragment {
    private final static String TABNAME = "头条";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_topnews,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.news_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        async();
        return view;
    }

    @Override
    public String getTabName(){
        return TABNAME;
    }

    @Override
    public void refresh() {
        async();
    }

    //presenter module
    private void async(){
        NetWorkUtils.getInstance().ALIGet_Sync(requestResult);
    }

    private BaseInterface<String> requestResult = new BaseInterface<String>() {
        @Override
        public void onComplete(String result) {
            if(!TextUtils.isEmpty(result))
            {
                ConstValue.ALIAPIBEAN bean = JSON.parseObject(result,ConstValue.ALIAPIBEAN.class);
                List<ConstValue.ALIAPIBEANDATAITEM> datats = bean.getResult().getData();
                adapter.swapDatas(datats);
                onRefreshComplete();
            }
        }

        @Override
        public void onError(@StringRes int resId) {
            //http error
        }
    };
}
