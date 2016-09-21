package com.newsing.fragment.beauty;

import android.content.Context;

import com.newsing.utils.FileUtils;

/**
 * Created by qzzhu on 16-9-21.
 * this class will hold item data
 */
class ItemModel {
    private String picName = null;

    ItemModel(String picName){
        this.picName = picName;
    }

    String getPic_path() {
        return picName;
    }


    String getFilePath(Context t){
        return FileUtils.GetBeautyFile(t,picName).getPath();
    }
}
