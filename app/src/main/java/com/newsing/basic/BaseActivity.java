package com.newsing.basic;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.Window;
/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpWindowScene();
    }

    private void setUpWindowScene() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setEnterTransition(new Explode());
            window.setReturnTransition(new Explode());
        }

    }
}
