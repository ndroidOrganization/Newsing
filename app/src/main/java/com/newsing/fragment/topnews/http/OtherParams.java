package com.newsing.fragment.topnews.http;

/**
 * Created by Angel on 2016/9/23.
 */
public class OtherParams {

    private String key;
    private String type;

    public OtherParams(String key, String type) {
        this.key = key;
        this.type = type;
    }

    public String getAllParams() {
        return String.format("key=%d&type=%s", key, type);
    }

}