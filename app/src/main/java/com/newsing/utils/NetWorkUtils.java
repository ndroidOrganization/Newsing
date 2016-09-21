package com.newsing.utils;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
     * http Get method execute immediately
     * @param url url
     * @return result [String]
     * @throws IOException
     */
    public Response Get_Sync(String url) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .build();
        return client.newCall(request).execute();
    }

    /**
     * http Get mothod execute on other thread
     * @param url url
     * @param callBack request callback
     */
    public void Get_Async(String url,final NetWorkCallBack callBack){
        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
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
}
