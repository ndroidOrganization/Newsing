package com.newsing.fragment.beauty.net;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * Created by qzzhu on 16-9-23.
 * network uri request and parsed to useful model
 */
public class NetJsonResponse {
    // = new ArrayList<>();
    private int code = -1;
    private String msg = "";
    private ArrayList<NetJsonResponse.itemBean> newslist = null;

    public NetJsonResponse(){}

    public static NetJsonResponse setUp(String result,int num){
        NetJsonResponse response = null;
        try {
            response = JSON.parseObject(result,NetJsonResponse.class);
        } catch (Exception e) {
            response = new NetJsonResponse();
        }
        return response;
    }

    public ArrayList<NetItemModel> getParsedDats() {
        ArrayList<NetItemModel> parsedDats = new ArrayList<>();
        for(NetJsonResponse.itemBean bean:newslist)
        {
            parsedDats.add(new NetItemModel(bean.getPicUrl(),bean.getTitle()));
        }
        return parsedDats;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<itemBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(ArrayList<itemBean> newslist) {
        this.newslist = newslist;
    }

    /*"title": "夏日邻家少女海边展现笑靥",
            "description": "夏日邻家少女海边展现笑靥...",
            "picUrl": "http://t1.27270.com/uploads/150616/7-150616163120608.jpg",
            "url": "http://www.27270.com/ent/meinvtupian/2015/51822.html"*/

    public static class itemBean{
        private String title;
        private String description;
        private String picUrl;
        private String url;
        private String ctime;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }
    }
}
