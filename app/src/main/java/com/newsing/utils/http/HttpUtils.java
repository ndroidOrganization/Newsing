//package com.newsing.utils.http;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.util.Log;
//import android.widget.Toast;
//import org.androidannotations.annotations.Bean;
//import org.androidannotations.annotations.EBean;
//import org.androidannotations.annotations.RootContext;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONObject;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.List;
//import java.util.UUID;
//
///**
// * Created by Angel on 2016/8/25.
// */
//@EBean
//public class HttpUtils {
//    @RootContext
//    Context context;
//
//    public boolean isConnnected() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (null != connectivityManager) {
//            NetworkInfo networkInfo[] = connectivityManager.getAllNetworkInfo();
//
//            if (null != networkInfo) {
//                for (NetworkInfo info : networkInfo) {
//                    if (info.getState() == NetworkInfo.State.CONNECTED) {
//                        return true;
//                    }
//                }
//            }
//        }
//        Toast.makeText(context, "网络连接失败", Toast.LENGTH_SHORT).show();
//        return false;
//    }
//
//    public JSONObject getResponseForGet(String strUrl, String params) {
//        if (isConnnected()) {
//            HttpGet httpRequest = new HttpGet(strUrl + "?" + params);
//            return getResponse(httpRequest);
//        }
//        return null;
//    }
//
//    private JSONObject getResponse(HttpUriRequest request) {
//        try {
//            BasicHttpParams httpParameters = new BasicHttpParams();
//            HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
//            HttpConnectionParams.setSoTimeout(httpParameters, 15000);
//            HttpConnectionParams.setTcpNoDelay(httpParameters, true);
//            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
//            HttpResponse httpResponse = httpClient.execute(request);
//            int statusCode = httpResponse.getStatusLine().getStatusCode();
//            Log.i("11", "getResponse: " + statusCode);
//            if (HttpStatus.SC_OK == statusCode) {
//                String result = EntityUtils.toString(httpResponse.getEntity());
//                return new JSONObject(result);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //通过list获取
//    public JSONObject getResponseForPostByList(String strUrl, List<NameValuePair> params) {
//        if (isConnnected()) {
//            HttpPost request = new HttpPost(strUrl);
//            request.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//            if (params != null) {
//                try {
//                    request.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            return getResponse(request);
//        }
//        return null;
//    }
//
//    //通过json获取
//    public JSONObject getResponseForPostByJson(String url, JSONObject param) {
//        if (isConnnected()) {
//            HttpPost httppost = new HttpPost(url);
//            //添加http头信息
//            httppost.addHeader("Content-Type", "application/json");
//            httppost.addHeader("User-Agent", "imgfornote");
//            try {
//                httppost.setEntity(new StringEntity(param.toString()));
//                return getResponse(httppost);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//}
