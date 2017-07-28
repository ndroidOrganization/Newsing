package com.newsing.activity.mycollect;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;

import com.newsing.db.AliApiDataItem;
import com.newsing.db.NewingEntity;
import com.newsing.utils.DBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qzhu on 2017/7/28.
 */

public class CollectionsPresenter implements ICollectionActions{

    private ICollectionCallback callback;
    private ListAdapterWrapper adapter;


    public CollectionsPresenter(ICollectionCallback callback) {
        this.callback = callback;
    }

    @Override
    public void Async() {
        List<NewingEntity> results= DBManager.Query(null);
        if(results != null && results.size()>0){
            List<AliApiDataItem> sets = new ArrayList<>();
            for(NewingEntity item : results){
                sets.add(item);
            }
            adapter.swapDatas(sets);
        }
    }

    @Override
    public void setAdapter(RecyclerView recyclerView, ContextThemeWrapper context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter((adapter = new ListAdapterWrapper(context)));
    }
}
