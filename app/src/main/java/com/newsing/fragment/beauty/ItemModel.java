package com.newsing.fragment.beauty;

import android.content.Context;

import com.newsing.utils.FileManager;

/**
 * Created by qzzhu on 16-9-21.
 * this class will hold item data
 */
class ItemModel {
    /**
     * the image file path
     */
    private String picpath = null;

    ItemModel(String picpath){
        this.picpath = picpath;
    }

    public String getPicpath() {
        return picpath;
    }

    String getFilePath(Context t){
        return null;//FileManager.GetBeautyFile(t,picpath).getPath();
    }
}
