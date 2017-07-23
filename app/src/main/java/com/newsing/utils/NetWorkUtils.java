package com.newsing.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.newsing.basic.BaseInterface;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by qzzhu on 16-8-22.
 * offered both Async and Sync method request
 * use permission android.permission.Internet
 */
public class NetWorkUtils {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private volatile static NetWorkUtils instance = null;

    private OkHttpClient client = null;

//    private Handler deliver = null;

    private NetWorkUtils(){
        client = new OkHttpClient();
    }

    public static NetWorkUtils getInstance(){
        if(instance == null)
        {
            synchronized (NetWorkUtils.class)
            {
                if(instance == null)
                    instance = new NetWorkUtils();
            }
        }
        return instance;
    }

    public interface NetWorkCallBack{
        void onError(Exception e);
        void onComplete(Response result);
    }

    /**
     * http Get method execute Async
     * @return result [String]
     * @throws IOException
     */
    public void ALIGet_Sync(final BaseInterface<String> callback,String newsType){
        final String path = (newsType == null ? ConstValue.ALIAPI.TOPNEWS : newsType);
        Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Log.i("NetWorkUtils","load start uri = \t"+ConstValue.ALIAPI.uri+path);
                    Response response = NetWorkUtils.getInstance().Get_Sync(ConstValue.ALIAPI.uri+path,
                            new Pair<String, String>(ConstValue.ALIAPI.ALIAUTHORY, ConstValue.ALIAPI.ALIAPPCODE));
                    subscriber.onNext(response.body().string());
                } catch (IOException e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callback.onError(-1);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("NetWorkUtils","load complete");
                        callback.onComplete(s);
                    }
                });
    }

    /**
     * http Get method execute immediately
     * @param url url
     * @return result [String]
     * @throws IOException
     */
    public Response Get_Sync(String url, Pair<String,String>... heads) throws IOException{
        Request.Builder request = new Request.Builder()
                .url(url);
        for(Pair<String,String> pair:heads)
        {
            request.header(pair.first,pair.second);
        }
        return client.newCall(request.build()).execute();
    }

    /**
     * http Get mothod execute on other thread
     * @param url url
     * @param callBack request callback
     */
    public void Get_Async(String url,final NetWorkCallBack callBack,Pair<String,String>... heads){
        Request.Builder request = new Request.Builder()
                .url(url);
        for(Pair<String,String> pair:heads)
        {
            request.header(pair.first,pair.second);
        }
        client.newCall(request.build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onComplete(response);
            }
        });
    }

    /**
     * http Post method execute immediately
     * @param url url
     * @param jsonParam request format String
     * @param header default ['application/json; charset=utf-8']
     * @return result [String]
     * @throws IOException
     */
    public String Post_Sync(String url,String jsonParam,@Nullable String header) throws IOException{
        MediaType mediaType = null;
        if(header == null)
        {
            mediaType = JSON;
        }
        else{
            mediaType = MediaType.parse(header);
        }
        RequestBody body = RequestBody.create(mediaType, jsonParam);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * http post method execute on other thread
     * @param url url
     * @param jsonParam param String
     * @param header default ['application/json; charset=utf-8']
     * @param callBack async callback
     * @throws IOException
     */
    public void Post_Async(String url,String jsonParam,@Nullable String header,final NetWorkCallBack callBack) throws IOException{
        MediaType mediaType = null;
        if(header == null)
        {
            mediaType = JSON;
        }
        else{
            mediaType = MediaType.parse(header);
        }
        RequestBody body = RequestBody.create(mediaType, jsonParam);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onComplete(response);
            }
        });
    }

    public static boolean isNetWorkAvailable(Application context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni == null)
            return false;
        else
            return ni.isConnected();
    }
}
