package com.newsing.utils;

import android.support.v4.util.Pair;

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
}
