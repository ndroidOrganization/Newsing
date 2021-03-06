package com.newsing.activity.webpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.newsing.R;
import com.newsing.basic.BaseActivity;
import com.newsing.db.AliApiDataItem;
import com.newsing.db.NewingEntity;
import com.newsing.utils.DBManager;

import static com.newsing.utils.ActivityTranslater.KEY_BUNDLE;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class WebActivity extends BaseActivity {

    public static String KEY_OF_TARGET_URI = "KEYURI";
    private String targetUri = null;

    private WebView webView;

    private AliApiDataItem data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.activity_webview);

        Bundle bundle = getIntent().getBundleExtra(KEY_BUNDLE);
        data = bundle.getParcelable(KEY_OF_TARGET_URI);
        if(data!=null) {
            targetUri = data.getUrl();
            initialData();
        }
        setUpToolBar();
    }

    private void setUpToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.web_toolbar);
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

//    @Override
//    protected void setUpWindowScene() {
//        Window window = getWindow();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.setReturnTransition(new Explode());
//            window.setEnterTransition(new Explode());
//            window.setExitTransition(new Explode());
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.webs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_collects) {
            backUp();
            return true;
        }

        if(id == R.id.action_share)
        {
            share();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void backUp(){
        int result = DBManager.Insert(new NewingEntity(data));
        View view = findViewById(R.id.web_container);
        if(result != -1){
            Snackbar.make(view,R.string.action_collect_ok,Snackbar.LENGTH_LONG).show();
        }
        else{
            Snackbar.make(view,R.string.action_collect_failed,Snackbar.LENGTH_LONG).show();
        }
    }

    private void share(){

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
