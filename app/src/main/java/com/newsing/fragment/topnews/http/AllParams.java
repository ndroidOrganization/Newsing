package com.newsing.fragment.topnews.http;

/**
 * Created by Angel on 2016/9/23.
 */
public class AllParams {

    private String key;
    private String type;

    public AllParams(String key, String type) {
        this.key = key;
        this.type = type;
    }

    public String getAllParams() {
        return String.format("key=%d&type=%s", key, type);
    }

}
