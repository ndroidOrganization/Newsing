package com.newsing.basic;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
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

    protected void setUpWindowScene() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //finishAfterTransition
            window.setReturnTransition(new Fade());

            //enter exit
            window.setEnterTransition(new Explode());
            window.setExitTransition(new Fade());
        }

    }
}
