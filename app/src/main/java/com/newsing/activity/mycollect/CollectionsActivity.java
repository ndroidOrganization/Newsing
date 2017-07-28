package com.newsing.activity.mycollect;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.newsing.R;
import com.newsing.basic.BaseActivity;

/**
 * Created by Qzhu on 2017/7/28.
 */
public class CollectionsActivity extends BaseActivity implements ICollectionCallback{

    private RecyclerView recyclerView;
    private ICollectionActions presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        setUpToolBar();
        setUpView();
        setUpData();
    }

    private void setUpData() {
        presenter = new CollectionsPresenter(this);
        presenter.setAdapter(recyclerView,this);

        presenter.Async();
    }

    private void setUpView() {
        recyclerView = (RecyclerView) findViewById(R.id.collections_recyclerView);
    }


    private void setUpToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.action_collects_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
