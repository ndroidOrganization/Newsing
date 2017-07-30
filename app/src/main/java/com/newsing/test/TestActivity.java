package com.newsing.test;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newsing.R;
import com.newsing.basic.BaseActivity;

/**
 * Created by Administrator on 2017/7/30 0030.
 */

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
