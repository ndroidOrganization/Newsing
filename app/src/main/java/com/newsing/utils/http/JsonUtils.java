//package com.newsing.utils.http;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONException;
//import org.androidannotations.annotations.EBean;
//import org.json.JSONObject;
//
//import java.util.List;
//
///**
// * Created by Angel on 2016/8/25.
// */
//@EBean
//public class JsonUtils {
//
//    public String getJson(JSONObject o) {
//        return o.toString();
//    }
//
//    public <T> T getObject(String jsonObject, Class<T> t) {
//        try {
//            return JSON.parseObject(jsonObject, t);
//        } catch (JSONException e) {
//            return null;
//        }
//    }
//
//    public <T> List<T> getArray(String jsonArray, Class<T> t) {
//        try {
//            return JSON.parseArray(jsonArray, t);
//        } catch (JSONException e) {
//            return null;
//        }
//    }
//}
