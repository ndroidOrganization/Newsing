package com.newsing.db;

/**
 * Created by Qzhu on 2017/7/27.
 */

public class NewingEntity extends AliApiDataItem{
    private int id;
    private String author;  //key of user

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NewingEntity(){}

    public NewingEntity(AliApiDataItem parent){
        this();
        setUniquekey(parent.getUniquekey());
        setTitle(parent.getTitle());
        setDate(parent.getDate());
        setAuthor_name(parent.getAuthor_name());
        setUrl(parent.getUrl());
        setThumbnail_pic_s(parent.getThumbnail_pic_s());
        setThumbnail_pic_s02(parent.getThumbnail_pic_s02());
        setThumbnail_pic_s03(parent.getThumbnail_pic_s03());
    }
}
