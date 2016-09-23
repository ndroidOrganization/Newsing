package com.newsing.fragment.topnews.http.top;

/**
 * Created by Angel on 2016/9/23.
 */
public class TopParams {

    private String key;
    private String type;

    public TopParams(String key, String type) {
        this.key = key;
        this.type = type;
    }

    public String getTopParams() {
        return String.format("key=%s&type=%s", key, type);
    }

}
