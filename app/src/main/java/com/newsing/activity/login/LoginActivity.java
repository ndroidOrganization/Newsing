package com.newsing.activity.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.newsing.R;
import com.newsing.activity.mian.MainActivity;
import com.newsing.basic.BaseActivity;
import com.newsing.utils.ActivityTranslater;

/**
 * Created by Qzhu on 2017/7/31.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button login;
    private EditText userName,passWord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialView();
    }


    private void initialView(){
        login = (Button) findViewById(R.id.login_login);


        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_login:
                ActivityTranslater.GoTo(this, MainActivity.class,null);
                finishAfterTransition();
                break;
        }
    }
}
