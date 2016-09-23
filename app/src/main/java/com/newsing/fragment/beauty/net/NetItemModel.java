package com.newsing.fragment.beauty.net;

/**
 * Created by qzzhu on 16-9-23.
 */

public class NetItemModel {
    private String uri ;
    private String descript ;

    public NetItemModel(){}

    public NetItemModel(String uri, String descript) {
        this.uri = uri;
        this.descript = descript;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
