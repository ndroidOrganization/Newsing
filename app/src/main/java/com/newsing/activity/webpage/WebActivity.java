package com.newsing.activity.webpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.newsing.R;
import com.newsing.basic.BaseActivity;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class WebActivity extends BaseActivity {

    public static String KEY_OF_TARGET_URI = "KEYURI";
    private String targetUri = null;

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.activity_webview);

        targetUri = getIntent().getStringExtra(KEY_OF_TARGET_URI);

        initialData();
    }

    private void initialData(){
        webView.loadUrl(targetUri);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器

                //do nothing
                //view.loadUrl(url);
                return true;
            }
        });
    }
}
