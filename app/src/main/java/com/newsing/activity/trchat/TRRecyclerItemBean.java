package com.newsing.activity.trchat;

/**
 * Created by Qzhu on 2017/7/28.
 */

public class TRRecyclerItemBean {
    private boolean request;
    private String content;

    public TRRecyclerItemBean(boolean request, String content) {
        this.request = request;
        this.content = content;
    }

    public boolean isRequest() {
        return request;
    }

    public String getContent() {
        return content;
    }
}
