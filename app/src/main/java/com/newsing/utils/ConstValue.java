package com.newsing.utils;

import android.os.Bundle;
import android.support.v4.util.Pair;
import android.util.Log;

import com.newsing.activity.webpage.WebActivity;
import com.newsing.db.AliApiDataItem;

import java.util.List;

/**
 * Created by qzzhu on 16-9-23.
 */

public class ConstValue {

    public static class InterFace_Beauty{
        private final static String uri = "http://apis.baidu.com/txapi/mvtp/meinv";
        private final static String KEY = "10d594143c8db7292b66bcffc4718c7f";
        public static String getBeautyUri(int numbers){
            return uri+"?num="+numbers;
        }

        public static Pair<String,String> getHeaders(){
            return new Pair<>("apikey",KEY);
        }
    }

    public static class ALIAPI{
        /*Header: {"Date":"Sat, 22 Jul 2017 02:27:14 GMT",
                "Host":"toutiao-ali.juheapi.com",
                "Accept":"application/json",
                "X-Ca-Timestamp":"1500690434174",
                "X-Ca-Version":"1",
                "gateway_channel":"http",
                "User-Agent":"Apache-HttpClient/4.1.2 (java 1.6)",
                "headerName":"consoleClientHeaderName",
                "X-Ca-Key":"24554370","X-Ca-Stage":"RELEASE",
                "Content-Type":"application/json; charset=utf-8",
                "X-Ca-Signature-Headers":"X-Ca-Timestamp,X-Ca-Version,X-Ca-Key,X-Ca-Stage",
                "X-Ca-Signature":"kCk9bdfHIu+4b7671JJPHvb/G9j/phk9odNgErkAVcA="}
                24554370
                2c0a31647ab079d0524a8e8448d74d98
                */

        public final static String uri = "http://toutiao-ali.juheapi.com/toutiao/index?type=";
        public static final String ALIAUTHORY = "Authorization";
        public static final String ALIAPPCODE = "APPCODE "+"3611a42e47ef4d6d80e6023ce6b40b00";

        public static final String TOPNEWS = "top";
        public static final String SOCIETY = "shehui";
        public static final String CHINA = "guonei";
        public static final String ABROAD = "guoji";
        public static final String ENTERTAINMENT = "yule";
        public static final String SPORTS = "tiyu";
        public static final String MILITARY = "junshi";
        public static final String TECH = "keji";
        public static final String FINANCE = "caijing";
        public static final String FASHION = "shishang";

    }

    public static class ALIAPIBEAN{
        private String reason;
        private int error_code;
        private ALIAPIBEANRESULT result;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public ALIAPIBEANRESULT getResult() {
            return result;
        }

        public void setResult(ALIAPIBEANRESULT result) {
            this.result = result;
        }
    }

    public static class ALIAPIBEANRESULT{
        private int stat;
        private List<AliApiDataItem> data;

        public int getStat() {
            return stat;
        }

        public void setStat(int stat) {
            this.stat = stat;
        }

        public List<AliApiDataItem> getData() {
            return data;
        }

        public void setData(List<AliApiDataItem> data) {
            this.data = data;
        }
    }
}
