package com.newsing.activity.trchat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.newsing.R;
import com.newsing.basic.BaseActivity;

/**
 * Created by Qzhu on 2017/7/28.
 */

public class TRChatActivity extends BaseActivity implements TRChatCallback, View.OnClickListener {

    private TRChatActions actions;

    private EditText questions;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulingchat);
        setUpToolBar();
        initData();
    }

    private void setUpToolBar(){
        questions = (EditText) findViewById(R.id.tuling_chat_quest);
        findViewById(R.id.tuling_chat_send).setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.collections_recyclerView);

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

    private void initData() {
        actions = new TRChatPresenter(this,this);
        actions.initialTR();
        actions.initRecyclerView(recyclerView);
    }

    public void submit(View view){
        String request = questions.getText().toString();
        actions.addData(true,request);

        actions.requestTR(request);
        questions.setText("");
    }

//    @Override
//    protected void setUpWindowScene() {
//        Window window = getWindow();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.setReturnTransition(new Slide(Gravity.START));
//            window.setEnterTransition(new Explode());
//            window.setExitTransition(new Fade());
//        }
//    }

    @Override
    public void onResponse(String question, String answers) {
        actions.addData(false,answers);
    }

    @Override
    public void onClick(View v) {
        submit(v);
    }
}
