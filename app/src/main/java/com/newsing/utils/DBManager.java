package com.newsing.utils;

import android.text.TextUtils;

import com.newsing.db.AliApiDataItem;
import com.newsing.db.NewingEntity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Qzhu on 2017/7/27.
 */
public class DBManager {
    public static int Insert(NewingEntity record){
        if(DataSupport.where(AliApiDataItem.WHERE,record.getDate()
                ,record.getAuthor_name(),record.getUrl())
                .find(NewingEntity.class).size()>0){
            return -1;
        }else{
            record.save();
        }
        return record.getId();
    }

    public static int delete(NewingEntity record){
        return record.delete();
    }

    public static List<NewingEntity> Query(String author){
        List<NewingEntity> mDestList = null;
        if(!TextUtils.isEmpty(author))
            mDestList=DataSupport.where("author = ?", author).find(NewingEntity.class);
        else{
            mDestList=DataSupport.findAll(NewingEntity.class);
        }
        return mDestList;
    }
}
