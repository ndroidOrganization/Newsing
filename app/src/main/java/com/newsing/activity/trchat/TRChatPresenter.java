package com.newsing.activity.trchat;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;

import com.newsing.R;
import com.turing.androidsdk.HttpRequestListener;
import com.turing.androidsdk.TuringManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Qzhu on 2017/7/28.
 */
public class TRChatPresenter implements TRChatActions{

    private TRChatCallback callback;
    private Context context;

    private RecyclerView recyclerView;
    private TRRecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;

    private TuringManager mTuringManager;

    private final static String TAG ="TRChatPresenter";
    /**
     * 申请的turing的apikey（测试使用）
     * **/
    private final String TURING_APIKEY = "26efcc4c4b824d1396ba0d16d10e3697";
    /**
     * 申请的secret（测试使用）
     * **/
    private final String TURING_SECRET = "92707613e0b52aaa";

    public TRChatPresenter(TRChatCallback callback, ContextThemeWrapper context) {
        this.callback = callback;
        this.context =context;
    }

    @Override
    public void initialTR() {
        mTuringManager = new TuringManager(this.context, TURING_APIKEY,
                TURING_SECRET);
        mTuringManager.setHttpRequestListener(myHttpConnectionListener);
    }

    @Override
    public void requestTR(String args) {
        mTuringManager.requestTuring(args);
    }

    @Override
    public void addData(boolean request, String content) {
        adapter.addData(request,content);
        recyclerView.smoothScrollToPosition(adapter.getTotal());
    }

    @Override
    public void initRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager((layoutManager = new LinearLayoutManager(context)));
        recyclerView.setAdapter((adapter = new TRRecyclerAdapter(context)));
    }

    /**
     * 网络请求回调
     */
    private HttpRequestListener myHttpConnectionListener = new HttpRequestListener() {
        @Override
        public void onSuccess(String result) {
            if (result != null) {
                try {
                    JSONObject result_obj = new JSONObject(result);
                    if (result_obj.has("result")) {
                        callback.onResponse(null,result_obj.get("result").toString());
                    }else if(result_obj.has("text")){
                        callback.onResponse(null,result_obj.get("text").toString());
                    }
                } catch (JSONException e) {
                    callback.onResponse(null,context.getString(R.string.tr_notxt));
                }
            }else{
                callback.onResponse(null,context.getString(R.string.tr_notxt));
            }
        }

        @Override
        public void onFail(int code, String s) {
            callback.onResponse(null,context.getString(R.string.tr_error));
        }
    };
}
